package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.DeskDisplay;
import treulo.src.view.appview.display.Display;
import treulo.src.view.appview.display.TextDisplay;

//Fabrique d'afficheur bureau
//Créée par : Adrien
public class DeskDisplayFactory extends DisplayFactory {

    private Treulo model;
    public DeskDisplayFactory(Treulo model) {
        this.model = model;
    }
    @Override
    public Display createDisplay() {
        return new DeskDisplay(model, model.getTasks());
    }
}
