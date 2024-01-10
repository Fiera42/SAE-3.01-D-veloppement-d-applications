package treulo.src.Controler.to_image;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import treulo.src.model.Treulo;
import treulo.src.view.appview.AppView;

import java.io.File;

public class ExportAsImageControl implements EventHandler<ActionEvent> {
    private Treulo model;

    private static Stage stage;
    private Scene scene;

    public ExportAsImageControl(Treulo model) {
        this.model = model;
    }

    public ExportAsImageControl(Treulo model, Stage stage, Scene scene) {
        this.model = model;
        this.stage = stage;
        this.scene = scene;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof MenuItem)) return;

        MenuItem menu = (MenuItem) actionEvent.getSource();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter en tant qu'image...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            FXImaging imager = new FXImaging();
            imager.sceneToImage(scene, file, scene.getWidth(), scene.getHeight());
        }
    }
}
