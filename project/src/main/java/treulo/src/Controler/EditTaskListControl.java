package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

public class EditTaskListControl implements EventHandler<ActionEvent> {

    Treulo model;
    TaskList tL;

    public EditTaskListControl(Model m,TaskList tl)
    {
        model=(Treulo) m;
        tL=tl;
    }

    @Override
    public void handle(ActionEvent event) {
        model.setEditedTaskList(tL);
    }
}
