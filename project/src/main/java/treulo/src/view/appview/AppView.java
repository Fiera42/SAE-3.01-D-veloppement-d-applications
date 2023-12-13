package treulo.src.view.appview;

import javafx.scene.control.ScrollPane;
import treulo.src.view.Observator;

public class AppView extends ScrollPane implements Observator {
    public void update(Model model) {
        if(!(model instanceof Treulo)) return;


    }
}
