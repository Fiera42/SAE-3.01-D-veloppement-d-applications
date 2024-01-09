package treulo.src.Controler.file_management;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import treulo.src.model.Treulo;

public class FileControlDispatcher implements EventHandler<ActionEvent> {
    private Treulo model;

    public FileControlDispatcher(Treulo model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof MenuButton)) return;

        MenuButton menu = (MenuButton) actionEvent.getSource();

    }
}
