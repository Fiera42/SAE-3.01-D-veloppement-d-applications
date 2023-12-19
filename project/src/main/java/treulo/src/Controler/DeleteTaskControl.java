package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

public class DeleteTaskControl implements EventHandler<ActionEvent> {

    private Model model;
    private TreuloTask treuloTask;

    public DeleteTaskControl(Model model, TreuloTask treuloTask) {
        this.model = model;
        this.treuloTask = treuloTask;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(treuloTask.getParentList() != null) treuloTask.getParentList().deleteTask(treuloTask);
        if(treuloTask.getParentTask() != null) treuloTask.getParentTask().deleteSubTask(treuloTask);
    }
}
