package treulo.src.view.appview.display;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import treulo.src.Controler.AddTaskCollaboratorControl;
import treulo.src.Controler.AddTaskControl;
import treulo.src.Controler.AddTaskDependencyControl;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;

import static treulo.src.model.TreuloTask.getAlltasks;

public class CreateTaskDisplay implements Display{

    Treulo model;
    LinkedList <TaskList> taskLists;

    public CreateTaskDisplay(Model m,LinkedList<TaskList> taskLists) {
        this.model = (Treulo) m;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        VBox HboxMain =new VBox();

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
        bCenter3.setOnAction(new AddTaskCollaboratorControl(model,tFCenter3));
        bCenter3.setGraphic(new Circle());
        hbCenter3.getChildren().addAll(lBCenter3,tFCenter3,bCenter3);

        HBox hbCenter4 = new HBox();
        Label lBCenter4 = new Label("Dependance ");
        TextField tFCenter4= new TextField();
        Button bCenter4 = new Button("+");
        bCenter4.setOnAction(new AddTaskDependencyControl(model,tFCenter4));
        bCenter4.setGraphic(new Circle());
        hbCenter4.getChildren().addAll(lBCenter4,tFCenter4,bCenter4);

        VBoxCenter2.getChildren().addAll(hbCenter3,hbCenter4);
        gP.add(VBoxCenter2,1,2);

        VBox VBoxCenter3 = new VBox();

        Label lBCenter5 =new Label("est une sous tache de :");
        ComboBox <TreuloTask> combo= new ComboBox <TreuloTask>();
        combo.getItems().add(null);
        for (int i=0;i<getAlltasks().size();i++)
        {
        combo.getItems().add(getAlltasks().get(i));
        }

        VBoxCenter3.getChildren().addAll(lBCenter5,combo);

        gP.add(VBoxCenter3,4,2);
        gP.setAlignment(Pos.CENTER);
        bP.setCenter(gP);



        //le Bas

        VBox HBBottom = new VBox();
        Button bBottom = new Button("ajouter");
        bBottom.setOnAction(new AddTaskControl(model,model.getEditedTaskList(),tFCenter1,tFCenter2,combo));
        HBBottom.setAlignment(Pos.TOP_CENTER);
        HBBottom.getChildren().add(bBottom);

        bP.setBottom(HBBottom);

        HboxMain.getChildren().add(bP);
        HboxMain.setAlignment(Pos.TOP_CENTER);
        return HboxMain;
    }

    // cette methode est inutile pour cette classe
    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        return null;
    }
}
