package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.DetailTaskDisplay;
import treulo.src.view.appview.display.Display;
import treulo.src.view.appview.display.ListDisplay;

//Fabrique d'afficheur de tâche en détail
//Créée par : Doryann
public class DetailTaskDisplayFactory extends DisplayFactory{
    private Treulo model;
    public DetailTaskDisplayFactory(Treulo model) {
        this.model = model;
    }
    @Override
    public Display createDisplay() {
        return new DetailTaskDisplay(model);
    }
}
