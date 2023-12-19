package treulo.src.view.appview;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.view.Observator;
import treulo.src.view.appview.displayFactory.DisplayFactory;

public class AppView extends ScrollPane implements Observator {

    public AppView() {
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setFitToWidth(true);
        setFitToHeight(true);
        setBackground(Background.fill(Color.TRANSPARENT));
    }

    public void update(Model model) {
        if(!(model instanceof Treulo)) return;

        Treulo treulo = (Treulo)model;

        DisplayFactory factory = DisplayFactory.getDisplayFactory(treulo, treulo.getDisplayMode());

        setContent(factory.createDisplay().getDisplay());
        ((Pane) getContent()).setBackground(Background.fill(Color.AQUAMARINE));
    }
}
