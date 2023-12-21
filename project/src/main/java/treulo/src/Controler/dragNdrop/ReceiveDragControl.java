package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;

public class ReceiveDragControl implements EventHandler<DragEvent> {

    private Model model;
    private TaskList taskList;
    private TreuloTask treuloTask;

    public ReceiveDragControl(Model m, TaskList taskList) {
        this.taskList = taskList;
        model=m;
    }

    public ReceiveDragControl(Model m, TreuloTask treuloTask) {
        this.treuloTask = treuloTask;
        model=m;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        if(dragboard.hasString()) {
            String[] info = dragboard.getString().split(" ");

            if (treuloTask != null) {
                success = taskHandle(dragboard, info);
            } else {
                success = taskListHandle(dragboard, info);
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }

    public boolean taskHandle(Dragboard dragboard, String[] info) {
        switch (info[0]) {
            case "task" :
                TreuloTask draggedTask = TreuloTask.getTaskById(Integer.valueOf(info[1]));
                if(draggedTask == null) return false;

                TreuloTask parentTask = draggedTask.getParentTask();
                TaskList parentList = draggedTask.getParentList();
                if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                if(parentList != null) parentList.deleteTask(draggedTask);

                treuloTask.addSubTask(draggedTask);
                break;
            case "list" :
                TaskList draggedList = TaskList.getListById(Integer.valueOf(info[1]));
                if(draggedList == null) return false;

                TaskList myList = treuloTask.getParentList();

                int listPos = myList.getParentApp().getTasks().indexOf(myList);
                int draggedPos = draggedList.getParentApp().getTasks().indexOf(draggedList);

                if(draggedList.getParentApp() == null) return false;

                if(listPos > draggedPos) switchList(myList.getParentApp(), myList, draggedList);
                else switchList(myList.getParentApp(), draggedList, myList);
            default:
                return false;
        }
        return true;
    }

    public boolean taskListHandle(Dragboard dragboard, String[] info) {
        switch (info[0]) {
            case "task" :
                TreuloTask draggedTask = TreuloTask.getTaskById(Integer.valueOf(info[1]));
                if(draggedTask == null) return false;

                TreuloTask parentTask = draggedTask.getParentTask();
                TaskList parentList = draggedTask.getParentList();
                if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                if(parentList != null) parentList.deleteTask(draggedTask);

                taskList.addTask(draggedTask);
                break;
            case "list" :
                TaskList draggedList = TaskList.getListById(Integer.valueOf(info[1]));
                if(draggedList == null) return false;

                int listPos = taskList.getParentApp().getTasks().indexOf(taskList);
                int draggedPos = draggedList.getParentApp().getTasks().indexOf(draggedList);

                if(draggedList.getParentApp() == null) return false;

                if(listPos > draggedPos) switchList(taskList.getParentApp(), taskList, draggedList);
                else switchList(taskList.getParentApp(), draggedList, taskList);
                break;
            default:
                return false;
        }
        return true;
    }

    public void switchList(Treulo model, TaskList highList, TaskList lowList) {
        LinkedList list = model.getTasks();

        int highPos = list.indexOf(highList);
        list.remove(highList);
        list.add(list.indexOf(lowList), highList);
        list.remove(lowList);
        list.add(highPos, lowList);

        model.setTasks(list);

        //System.out.println("switched list id " + highList.getId() + " with list id " + lowList.getId());
    }
}
