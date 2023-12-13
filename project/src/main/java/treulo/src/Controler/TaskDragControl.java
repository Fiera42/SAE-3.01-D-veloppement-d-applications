package treulo.src.Controler;

import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

public class TaskDragControl {

    Model model;

    TaskList tl;

    TreuloTask tt;

    public TaskDragControl(Model m,TaskList taskList,TreuloTask treuloTask)
    {
        model=m;
        tl=taskList;
        tt=treuloTask;
    }
}
