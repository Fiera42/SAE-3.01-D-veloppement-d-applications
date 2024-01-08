package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.VBox;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;
import java.util.LinkedList;

public class ReceiveDragControl implements EventHandler<DragEvent> {

    //App model
    private Model model;
    //liste de tâche cible, null si il y a déjà une tâche
    private TaskList taskList;
    //tâches physique de la liste de tâche, null si non liste
    private ArrayList<VBox> tasks;
    //tâche cible, null si il y a déjà une liste de tâche
    private TreuloTask treuloTask;

    public ReceiveDragControl(Model m, TaskList taskList, ArrayList<VBox> tasks) {
        this.taskList = taskList;
        this.tasks = tasks;
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

        //Handle différent en fonction du type de cible
        if(dragboard.hasString()) {
            String[] info = dragboard.getString().split(" ");

            if (treuloTask != null) {
                success = taskHandle(event, info);
            } else {
                success = taskListHandle(event, info);
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }

    //Handle si la cible est une tâche
    public boolean taskHandle(DragEvent event, String[] info) {

        //Handler différent en fonction de l'objet déplacé
        switch (info[0]) {
            //Cible tâche, objet tâche
            case "task" :
                TreuloTask draggedTask = TreuloTask.getTaskById(Integer.valueOf(info[1]));
                if(draggedTask == null) return false;

                TreuloTask parentTask = draggedTask.getParentTask();
                TaskList parentList = draggedTask.getParentList();
                if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                if(parentList != null) parentList.deleteTask(draggedTask);

                treuloTask.addSubTask(draggedTask);
                break;

            //Cible tâche, objet liste
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

    //Handle si la cible est une liste de tâche
    public boolean taskListHandle(DragEvent event, String[] info) {

        //Handler différent en fonction de l'objet déplacé
        switch (info[0]) {
            //Cible liste, objet tâche
            case "task" :
                TreuloTask draggedTask = TreuloTask.getTaskById(Integer.valueOf(info[1]));
                if(draggedTask == null) return false;

                TreuloTask parentTask = draggedTask.getParentTask();
                TaskList parentList = draggedTask.getParentList();
                if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                if(parentList != null) parentList.deleteTask(draggedTask);

                //Récupération de l'emplacement de drop
                if(tasks.size() > 0){
                    System.out.println(tasks.size());
                    double dropY = event.getY();
                    VBox physicalList = (VBox) event.getGestureTarget();
                    double currentY = 44;
                    int taskIndex = 0;

                    while(currentY < dropY && taskIndex < taskList.getTasks().size()) {
                        currentY += tasks.get(taskIndex++).getHeight() + physicalList.getSpacing();
                    }
                    taskList.addTask(draggedTask, taskIndex);
                }
                else taskList.addTask(draggedTask);

                //System.out.println(physicalList.getHeight() / event.getY() + "/" + taskList.getTasks().size());
                break;
            //Cible liste, objet liste
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
