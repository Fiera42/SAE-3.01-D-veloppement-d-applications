package treulo.src.Controler.tasklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class AddTaskListMenuControl implements EventHandler<ActionEvent> {

    Treulo model;

    public AddTaskListMenuControl(Model m)
    {
        model= (Treulo) m;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        this.model.setDisplayMode("Nouvelle Liste");
    }
}
