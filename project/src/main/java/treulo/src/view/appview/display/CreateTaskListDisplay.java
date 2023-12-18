package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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

        HBox hb = new HBox();
        Label ta = new Label("Nom de la liste : ");
        TextField tf = new TextField("Tabouret");
        hb.getChildren().addAll(ta , tf);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {


        return null;
    }
}
