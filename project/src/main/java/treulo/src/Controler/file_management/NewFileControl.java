package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import treulo.src.model.Treulo;

//Classe chargée de créé un nouveau fichier treulo en appelant newFile() sur le modèle
//Handler d'événement (bouton de nouveau fichier, onAction)
//Créée par : Adrien
public class NewFileControl implements EventHandler<ActionEvent> {
    private Treulo model;

    public NewFileControl(Treulo model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        model.newFile();
    }
}
