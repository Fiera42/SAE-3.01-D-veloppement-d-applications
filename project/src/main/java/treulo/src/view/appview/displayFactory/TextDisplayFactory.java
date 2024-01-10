package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.Display;
import treulo.src.view.appview.display.TextDisplay;

//Fabrique d'afficheur brute en texte
//Créée par : Adrien
public class TextDisplayFactory extends DisplayFactory {

    private Treulo model;
    public TextDisplayFactory(Treulo model) {
        this.model = model;
    }
    @Override
    public Display createDisplay() {
        return new TextDisplay(model.getTasks());
    }
}
