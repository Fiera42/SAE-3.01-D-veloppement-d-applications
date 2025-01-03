package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.CreateTaskDisplay;
import treulo.src.view.appview.display.Display;

//Fabrique de créateur de tâche
//Créée par : Tom
public class CreateTaskDisplayFactory extends DisplayFactory{
    private Treulo model;

    public CreateTaskDisplayFactory(Treulo t){ model=t;}

    @Override
    public Display createDisplay() {
        return new CreateTaskDisplay(model,model.getTasks());
    }
}
