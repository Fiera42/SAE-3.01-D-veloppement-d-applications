package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.CreateTaskListDisplay;
import treulo.src.view.appview.display.Display;
import treulo.src.view.appview.display.TextDisplay;

//Fabrique de créateur de liste de tâche
//Créée par : Doryann
public class CreateTaskListDisplayFactory extends DisplayFactory {

    private Treulo model;
    public CreateTaskListDisplayFactory(Treulo model) {
        this.model = model;
    }
    @Override
    public Display createDisplay() {
        return new CreateTaskListDisplay(model);
    }
}
