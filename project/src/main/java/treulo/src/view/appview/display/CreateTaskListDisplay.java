package treulo.src.view.appview.display;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

public class CreateTaskListDisplay implements Display {

    Model model ;

    public CreateTaskListDisplay(Model m) {
        this.model = m ;
    }

    @Override
    public Node getDisplay() {
        VBox vbox = new VBox() ;

        return vbox;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {


        return null;
    }
}
