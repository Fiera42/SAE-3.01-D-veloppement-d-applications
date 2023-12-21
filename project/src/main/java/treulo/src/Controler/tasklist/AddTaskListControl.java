package treulo.src.Controler.tasklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

public class AddTaskListControl implements EventHandler<ActionEvent> {

    Treulo model;
    TextField textefield;


    public AddTaskListControl(Model m , TextField tf)
    {
        model=(Treulo) m;
        this.textefield = tf ;
    }

    public AddTaskListControl(Model m)
    {
        model=(Treulo) m;


    }


    @Override
    public void handle(ActionEvent actionEvent) {
        TaskList tl = new TaskList(this.textefield.getText());
        this.model.addTaskList(tl);
        this.model.setDisplayMode(model.getDisplayModeOld());

    }
}
