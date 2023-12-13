package treulo.src.view.appview.display;

import javafx.scene.Node;
import treulo.src.model.TreuloTask;

public interface Display {
    Node getDisplay();
    Node getTaskDisplay(TreuloTask task, Node parentNode);
}
