package treulo.src.view.appview;

import javafx.scene.control.ScrollPane;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.view.Observator;
import treulo.src.view.appview.displayFactory.DisplayFactory;

public class AppView extends ScrollPane implements Observator {

    public AppView() {
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }

    public void update(Model model) {
        if(!(model instanceof Treulo)) return;

        Treulo treulo = (Treulo)model;

        DisplayFactory factory = DisplayFactory.getDisplayFactory(treulo, treulo.getDisplayMode());

        setContent(factory.createDisplay().getDisplay());

    }
}
