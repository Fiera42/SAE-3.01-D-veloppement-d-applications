package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

//Controlleur chargé de mettre à jour l'affichage des archives par le modèle
//Handler d'événement (checkbox d'archive, onAction)
//Créée par : Adrien
public class ToggleArchiveControl implements EventHandler<ActionEvent> {
    Treulo model;

    public ToggleArchiveControl(Treulo model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof CheckBox)) return;

        CheckBox checkBox = (CheckBox) source;
        model.setDisplayArchive(checkBox.isSelected());
    }
}
