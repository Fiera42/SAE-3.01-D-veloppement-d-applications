package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

public class DeleteTaskListControl implements EventHandler<ActionEvent> {
    Treulo model;
    TaskList taskList;

    public DeleteTaskListControl(Model m , TaskList tl)
    {
        model= (Treulo) m;
        this.taskList = tl ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        model.removeTaskList(taskList);
    }
}
