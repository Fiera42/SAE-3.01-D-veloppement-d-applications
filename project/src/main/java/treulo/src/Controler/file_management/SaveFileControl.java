package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import treulo.src.model.Treulo;

public class SaveFileControl implements EventHandler<ActionEvent> {
    private Treulo model;

    public SaveFileControl(Treulo model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof MenuItem)) return;

        MenuItem menu = (MenuItem) actionEvent.getSource();
        if(model.getFilename() == null || model.getPath() == null || model.getFilename().isEmpty() || model.getPath().isEmpty()) {
            new SaveAsControl(model).handle(actionEvent);
        }
        else model.saveAsFile();
    }
}
