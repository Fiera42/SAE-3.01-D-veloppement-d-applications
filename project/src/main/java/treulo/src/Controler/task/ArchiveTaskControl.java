package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

//Controlleur chargé de mettre à jour l'archivage d'une tâche
//Handler d'événement (checkbox d'archivage, onAction)
//Créée par : Adrien
public class ArchiveTaskControl implements EventHandler<ActionEvent> {
    private Treulo model;
    private TreuloTask treuloTask;

    public ArchiveTaskControl(Treulo model, TreuloTask treuloTask) {
        this.model = model;
        this.treuloTask = treuloTask;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof CheckBox)) return;

        CheckBox checkBox = (CheckBox) source;
        treuloTask.setArchive(checkBox.isSelected());
    }
}
