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
    private Treulo model;

    //liste de tâche cible, null si il y a déjà une tâche
    private TaskList taskList;

    //tâche cible, null si il y a déjà une liste de tâche
    private TreuloTask treuloTask;

    //affichage physique des tâches dans la liste / des sous tâches de la tâche
    private ArrayList<VBox> tasks;

    public ReceiveDragControl(Treulo model, TaskList taskList, ArrayList<VBox> tasks) {
        this.taskList = taskList;
        this.tasks = tasks;
        this.model=model;
    }

    public ReceiveDragControl(Treulo model, TreuloTask treuloTask, ArrayList<VBox> tasks) {
        this.treuloTask = treuloTask;
        this.model=model;
        this.tasks = tasks;
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

                //Si le déplacement est fait au sein d'une même liste de tâche, on effectue des corrections
                boolean uhh_problem = !((parentTask == null || parentTask != treuloTask) && (parentList == null || parentList != taskList));

                if(!uhh_problem) {
                    if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                    if(parentList != null) parentList.deleteTask(draggedTask);
                }

                //Récupération de l'emplacement de drop
                if(tasks.size() > 0){
                    double dropY = event.getY();
                    VBox physicalList = (VBox) event.getGestureTarget();
                    double currentY = (model.getDisplayMode().equals("Tableau"))?145:177;
                    int taskIndex = 0;

                    while(currentY < dropY && taskIndex < treuloTask.getSubtasks().size()) {
                        currentY += tasks.get(taskIndex++).getHeight() + physicalList.getSpacing();
                    }

                    //Correction de l'index dans le cas d'un déplacement dans une même liste
                    if(!uhh_problem) treuloTask.addSubTask(draggedTask, taskIndex);
                    else {
                        int pb_index = treuloTask.getSubtasks().indexOf(draggedTask);
                        if(taskIndex > pb_index && taskIndex > 0) taskIndex--;
                        if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                        if(parentList != null) parentList.deleteTask(draggedTask);
                        treuloTask.addSubTask(draggedTask, taskIndex);
                    }
                }
                else treuloTask.addSubTask(draggedTask);
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

                //Si le déplacement est fait au sein d'une même liste de tâche, on effectue des corrections
                boolean uhh_problem = !((parentTask == null || parentTask != treuloTask) && (parentList == null || parentList != taskList));

                if(!uhh_problem) {
                    if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                    if(parentList != null) parentList.deleteTask(draggedTask);
                }

                //Récupération de l'emplacement de drop
                if(tasks.size() > 0){
                    double dropY = event.getY();
                    VBox physicalList = (VBox) event.getGestureTarget();
                    double currentY = (model.getDisplayMode().equals("Tableau"))?47:65;
                    int taskIndex = 0;

                    while(currentY < dropY && taskIndex < taskList.getTasks().size()) {
                        currentY += tasks.get(taskIndex++).getHeight() + physicalList.getSpacing();
                    }

                    //Correction de l'index dans le cas d'un déplacement dans une même liste
                    if(!uhh_problem) taskList.addTask(draggedTask, taskIndex);
                    else {
                        int pb_index = taskList.getTasks().indexOf(draggedTask);
                        if(taskIndex > pb_index && taskIndex > 0) taskIndex--;
                        if(parentTask != null) parentTask.deleteSubTask(draggedTask);
                        if(parentList != null) parentList.deleteTask(draggedTask);
                        taskList.addTask(draggedTask, taskIndex);
                    }
                }
                else taskList.addTask(draggedTask);
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
    }
}
