package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.CreateTaskDisplay;
import treulo.src.view.appview.display.DeleteTaskListDisplay;
import treulo.src.view.appview.display.Display;

public class DeleteTaskListDisplayFactory extends DisplayFactory{
    private Treulo model;

    public DeleteTaskListDisplayFactory(Treulo t){ model=t;}

    @Override
    public Display createDisplay() {
        return new DeleteTaskListDisplay(model);
    }
}
