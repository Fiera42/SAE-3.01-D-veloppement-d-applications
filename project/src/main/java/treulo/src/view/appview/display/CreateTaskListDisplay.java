package treulo.src.view.appview.display;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import treulo.src.Controler.tasklist.AddTaskListControl;
import treulo.src.Controler.BackControler;
import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

public class CreateTaskListDisplay implements Display {

    Model model ;

    public CreateTaskListDisplay(Model m) {
        this.model = m ;
    }

    @Override
    public Node getDisplay() {

        VBox vb = new VBox();
        HBox hb = new HBox();
        Label ta = new Label("Nom de la liste : ");
        ta.setFont(Font.font(25));
        TextField tf = new TextField("Tabouret");
        hb.getChildren().addAll(ta, tf);
        hb.setAlignment(Pos.CENTER);

        Button b = new Button("Ajouter la Liste");
        b.setOnAction(new AddTaskListControl(this.model , tf));
        b.setFont(Font.font(25));

        Button bBack = new Button("Retour");
        bBack.setOnAction(new BackControler(model));

        vb.getChildren().addAll(hb, b , bBack);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);


        return vb;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {


        return null;
    }
}
