package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.Display;

public abstract class DisplayFactory {

    public static DisplayFactory getDisplayFactory(Treulo model, String displayMode) {
        switch (displayMode) {
            default :
                return new TextDisplayFactory(model);
        }
    }
    public abstract Display createDisplay();

}
