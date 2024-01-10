package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import treulo.src.model.Treulo;

//Classe utilisée pour sauvegardé le fichier courant
//Handler d'événement (bouton d'enregistrement sous, onAction)
//Créée par : Adrien
public class SaveFileControl implements EventHandler<ActionEvent> {
    private Treulo model;

    public SaveFileControl(Treulo model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //Si le fichier n'existe pas, on demande à l'utilisateur où est-ce qu'il veut le sauvegarder
        if(model.getFilename() == null || model.getPath() == null || model.getFilename().isEmpty() || model.getPath().isEmpty()) {
            new SaveAsControl(model).handle(actionEvent);
        }
        else model.saveAsFile();
    }
}
