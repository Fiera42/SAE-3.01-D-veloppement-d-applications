package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

/**
 * Controleur permetant de suprimer une tache ainsi que ses sous taches
 */
public class DeleteTaskControl implements EventHandler<ActionEvent> {

    private Model model;
    private TreuloTask treuloTask;

    public DeleteTaskControl(Model model, TreuloTask treuloTask) {
        this.model = model;
        this.treuloTask = treuloTask;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        treuloTask.destroy();
    }
}
