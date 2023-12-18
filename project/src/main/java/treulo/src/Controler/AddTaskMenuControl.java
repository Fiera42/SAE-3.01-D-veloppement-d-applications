package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;

public class AddTaskMenuControl implements EventHandler<ActionEvent> {
        Model model;

        public AddTaskMenuControl(Model m)
        {
            model=m;
        }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
