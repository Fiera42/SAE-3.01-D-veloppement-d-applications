package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

public class AddTaskListControl implements EventHandler<ActionEvent> {

    Treulo model;
    String name ;

    public AddTaskListControl(Model m , String s)
    {
        model=(Treulo) m;
        this.name = s ;
    }

    public AddTaskListControl(Model m)
    {
        model=(Treulo) m;
        this.name = "" ;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        TaskList tl = new TaskList(this.name , this.model);
        this.model.addTaskList(tl);
        this.model.setDisplayMode("Tableau");

    }
}
