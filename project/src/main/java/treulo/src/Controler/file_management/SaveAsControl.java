package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import treulo.src.model.Treulo;

import java.io.File;
import java.io.IOException;

public class SaveAsControl implements EventHandler<ActionEvent> {
    private Treulo model;

    private static Stage stage;

    public SaveAsControl(Treulo model) {
        this.model = model;
    }

    public SaveAsControl(Treulo model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof MenuItem)) return;

        MenuItem menu = (MenuItem) actionEvent.getSource();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder le fichier sous...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("treulo", "*.treulo"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            model.setFilename(file.getName());
            model.setPath(file.getAbsolutePath());
            model.saveAsFile();
        }
    }
}