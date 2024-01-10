package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import treulo.src.model.Treulo;

import java.io.File;

//Classe chargée d'ouvrir un fichier treulo
//Handler d'événement (bouton d'ouverture de fichier, onAction)
//Créée par : Adrien
public class OpenFileControl implements EventHandler<ActionEvent> {
    private Treulo model;

    private static Stage stage;

    public OpenFileControl(Treulo model) {
        this.model = model;
    }

    public OpenFileControl(Treulo model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //L'utilisateur choisis son fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir...");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            //le modèle l'ouvre à partir du chemin
            model.openFile(file.getPath());
        }
    }
}
