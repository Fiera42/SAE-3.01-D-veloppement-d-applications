package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.Display;
import treulo.src.view.appview.display.ListDisplay;

//Fabrique d'afficheur liste
//Créée par : Doryann
public class ListDisplayFactory extends DisplayFactory {

    private Treulo model;
    public ListDisplayFactory(Treulo model) {
        this.model = model;
    }
    @Override
    public Display createDisplay() {
        return new ListDisplay(model, model.getTasks());
    }
}
