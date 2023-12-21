package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

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
            TreuloTask draggedTask = TreuloTask.getTaskById(Integer.valueOf(dragboard.getString()));
            System.out.println(treuloTask.getName() + " received " + draggedTask.getName());
            if(dragboard != null) success = true;

            draggedTask.getParentTask().deleteSubTask(draggedTask);
            draggedTask.getParentList().deleteTask(draggedTask);

            if(treuloTask != null) {
                treuloTask.addSubTask(draggedTask);
            }

            else {
                taskList.addTask(draggedTask);
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }
}
