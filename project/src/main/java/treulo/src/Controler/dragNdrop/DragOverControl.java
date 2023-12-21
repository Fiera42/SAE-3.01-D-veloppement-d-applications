package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

public class DragOverControl implements EventHandler<DragEvent> {

    private Model model;
    private TaskList taskList;
    private TreuloTask treuloTask;

    public DragOverControl(Model m, TaskList taskList) {
        this.taskList = taskList;
        model=m;
    }

    public DragOverControl(Model m, TreuloTask treuloTask) {
        this.treuloTask = treuloTask;
        model=m;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard dragboard = event.getDragboard();

        if(dragboard.hasString()) {
            String[] info = dragboard.getString().split(" ");

            if (treuloTask != null) {
                taskHandle(event, info);
            } else {
                taskListHandle(event, info);
            }
        }
        event.consume();
    }

    public void taskHandle(DragEvent event, String[] info) {
        switch (info[0]) {
            case "task" :
            case "list" :
                event.acceptTransferModes(TransferMode.ANY);
                break;
            default:
        }
    }

    public void taskListHandle(DragEvent event, String[] info) {
        switch (info[0]) {
            case "task" :
            case "list" :
                event.acceptTransferModes(TransferMode.ANY);
                break;
            default:
        }
    }
}
