package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import treulo.src.model.Treulo;

public class NewFileControl implements EventHandler<ActionEvent> {
    private Treulo model;

    public NewFileControl(Treulo model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof MenuItem)) return;

        MenuItem menu = (MenuItem) actionEvent.getSource();
        model.newFile();
    }
}
