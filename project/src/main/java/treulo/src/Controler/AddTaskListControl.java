package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class AddTaskListControl implements EventHandler<ActionEvent> {

    Treulo model;

    public AddTaskListControl(Model m)
    {
        model=(Treulo) m;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        this.model.setDisplayMode("Tableau");

    }
}
