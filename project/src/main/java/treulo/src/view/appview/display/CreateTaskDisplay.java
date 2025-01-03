package treulo.src.view.appview.display;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.converter.FloatStringConverter;
import treulo.src.Controler.task.*;
import treulo.src.Controler.BackControler;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;
import java.util.LinkedList;

import static treulo.src.model.TreuloTask.getAlltasks;


//Affichage pour créé une tâche
//Classe originale : Tom
//Modification pour adaptation : Doryann
public class CreateTaskDisplay implements Display{

    Treulo model;
    LinkedList <TaskList> taskLists;

    public CreateTaskDisplay(Model m,LinkedList<TaskList> taskLists) {
        this.model = (Treulo) m;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        VBox VboxMain =new VBox();

        BorderPane bP = new BorderPane();
        //Top

        VBox VBoxTop =new VBox();
        HBox hbBack =new HBox();
        Button bBack = new Button("Retour");
        bBack.setOnAction(new BackControler(model));
        hbBack.getChildren().add(bBack);
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        Label lBTop = new Label("Ajouter une tache");

        hbTitle.getChildren().add(lBTop);
        VBoxTop.getChildren().addAll(hbBack,hbTitle);

        bP.setTop(VBoxTop);
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

        HBox hDuree = new HBox();
        Label lDuree = new Label("Duree (en Heure)");
        TextField tFDuree = new TextField("1");
        tFDuree.setTextFormatter(new TextFormatter<>(new FloatStringConverter()));
        hDuree.getChildren().addAll(lDuree,tFDuree);
        vBoxCenter.getChildren().addAll(hbCenter1,hbCenter2,hDuree);

        gP.add(vBoxCenter,2,1);


        VBox VBoxCenter2 = new VBox();

        HBox hbCenter4 = new HBox();
        Label lBCenter4 = new Label("Dependance ");

        String sas = "Dépendance :"+"\n";
        Label label = new Label(sas);
        VBox test = new VBox();
        test.getChildren().add(label);
        for (TreuloTask tt : this.model.getDependencieTempo()){
            HBox hb = new HBox();
            Label l = new Label (tt.getName() + "\n");
            Button b = new Button("-");
            b.setOnAction(new DeleteDependencyControle(model,tt));
            hb.getChildren().addAll(l , b);
            test.getChildren().add(hb);
        }

        test.setBorder(Border.stroke(Color.BLACK));
        test.setBackground(Background.fill(Color.BISQUE));
        test.setMaxWidth(Double.MAX_VALUE);

        //comboBox
        ComboBox <TreuloTask> combo= new ComboBox <TreuloTask>();
        combo.getItems().add(null);
        for (int i=0;i<getAlltasks().size();i++)
        {

            if ( !this.model.getDependencieTempo().contains(getAlltasks().get(i))) {
                combo.getItems().add(getAlltasks().get(i));
            }
        }

        Button bCenter4 = new Button("+");
        bCenter4.setOnAction(new AddTaskDependencyControl(model,combo));

        hbCenter4.getChildren().addAll(lBCenter4,combo,bCenter4);

        HBox hbCenter3 = new HBox();
        Label lBCenter3 = new Label("Collaborateur");
        TextField tFCenter3= new TextField();


        Button bCenter3 = new Button("+");
        bCenter3.setOnAction(new AddTaskCollaboratorControl(model,tFCenter3));

        String collab = "  Les collaborateurs :      "+"\n";
        Label lbCenter = new Label(collab);
        VBox vb = new VBox();
        vb.getChildren().add(lbCenter);
        for (String s :model.getCollaboratorTempo())
        {
            HBox hb = new HBox();
            Label l = new Label(s);
            Button b = new Button("-");
            b.setOnAction(new DeleteCollaboratorControle(model , s ));
            hb.getChildren().addAll(l,b);
            vb.getChildren().add(hb);
        }

        vb.setBorder(Border.stroke(Color.BLACK));
        vb.setBackground(Background.fill(Color.BISQUE));
        vb.setMaxWidth(Double.MAX_VALUE);
        hbCenter3.getChildren().addAll(lBCenter3,tFCenter3,bCenter3);


        VBoxCenter2.getChildren().addAll(hbCenter4,test,hbCenter3 , vb);
        gP.add(VBoxCenter2,1,2);

        VBox VBoxCenter3 = new VBox();

        Label lBCenter5 =new Label("est une sous tache de :");
        //comboBox
        ComboBox <TreuloTask> combo2= new ComboBox <TreuloTask>();
        combo2.getItems().add(null);
        for (int i=0;i<getAlltasks().size();i++)
        {
        combo2.getItems().add(getAlltasks().get(i));
        }

        VBoxCenter3.getChildren().addAll(lBCenter5,combo2);

        gP.add(VBoxCenter3,4,2);
        gP.setAlignment(Pos.CENTER);
        bP.setCenter(gP);



        //le Bas

        VBox HBBottom = new VBox();
        Button bBottom = new Button("ajouter");
        bBottom.setOnAction(new AddTaskControl(model,model.getEditedTaskList(),tFCenter1,tFCenter2,tFDuree,combo2));
        HBBottom.setAlignment(Pos.TOP_CENTER);
        HBBottom.getChildren().add(bBottom);

        bP.setBottom(HBBottom);

        VboxMain.getChildren().add(bP);
        VboxMain.setAlignment(Pos.TOP_CENTER);
        return VboxMain;
    }

    // cette methode est inutile pour cette classe
    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        return null;
    }
}
