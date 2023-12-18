package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;

public class AddTaskListMenuControl implements EventHandler<ActionEvent> {

    Model model;

    public AddTaskListMenuControl(Model m)
    {
        model=m;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
