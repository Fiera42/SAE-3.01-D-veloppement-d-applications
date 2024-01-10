package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import treulo.src.model.Treulo;

import java.io.File;

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
        Object source = actionEvent.getSource();
        if(!(source instanceof MenuItem)) return;

        MenuItem menu = (MenuItem) actionEvent.getSource();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir...");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            model.openFile(file.getPath());
        }
    }
}