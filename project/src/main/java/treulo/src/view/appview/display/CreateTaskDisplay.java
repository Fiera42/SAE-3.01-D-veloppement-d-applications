package treulo.src.view.appview.display;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;
import java.util.List;

public class CreateTaskDisplay implements Display{

    LinkedList <TaskList> taskLists;

    public CreateTaskDisplay(LinkedList<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        BorderPane bP = new BorderPane();
        //Top
        HBox hbTOP = new HBox();
        Label lBTop = new Label("Ajouter une tache");
        hbTOP.getChildren().add(lBTop);
        hbTOP.setAlignment(Pos.CENTER);

        bP.setTop(hbTOP);
        //Center
        GridPane gP = new GridPane();
        VBox vBoxCenter= new VBox();

        HBox hbCenter1 = new HBox();
        Label lBCenter1 = new Label("Nom de la tache ");
        TextField tFCenter1= new TextField();
        hbCenter1.getChildren().addAll(lBCenter1,tFCenter1);
        HBox hbCenter2 = new HBox();
        Label lBCenter2 = new Label("Description         ");
        TextField tFCenter2= new TextField();
        hbCenter2.getChildren().addAll(lBCenter2,tFCenter2);

        vBoxCenter.getChildren().addAll(hbCenter1,hbCenter2);

        gP.add(vBoxCenter,2,1);


        VBox VBoxCenter2 = new VBox();

        HBox hbCenter3 = new HBox();
        Label lBCenter3 = new Label("Collaborator ");
        TextField tFCenter3= new TextField();
        Button bCenter3 = new Button("+");
        bCenter3.setGraphic(new Circle());
        hbCenter3.getChildren().addAll(lBCenter3,tFCenter3,bCenter3);

        HBox hbCenter4 = new HBox();
        Label lBCenter4 = new Label("Dependance ");
        TextField tFCenter4= new TextField();
        Button bCenter4 = new Button("+");
        bCenter4.setGraphic(new Circle());
        hbCenter4.getChildren().addAll(lBCenter4,tFCenter4,bCenter4);

        VBoxCenter2.getChildren().addAll(hbCenter3,hbCenter4);
        gP.add(VBoxCenter2,1,2);

        VBox VBoxCenter3 = new VBox();

        Label lBCenter5 =new Label("est une sous tache de :");
        ComboBox <List<TreuloTask>> combo= new ComboBox <List<TreuloTask>>();
        VBoxCenter3.getChildren().addAll(lBCenter5,combo);

        gP.add(VBoxCenter3,4,2);
        bP.setCenter(gP);

        //le Bas

        HBox HBBottom = new HBox();
        Button bBottom = new Button("ajouter");
        HBBottom.setAlignment(Pos.BOTTOM_RIGHT);
        HBBottom.getChildren().add(bBottom);

        bP.setBottom(HBBottom);
        bP.setBorder(Border.stroke(Color.BLACK));
        return bP;
    }

    // cette methode est inutile pour cette classe
    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        return null;
    }
}
