package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.converter.FloatStringConverter;
import treulo.src.Controler.BackControler;
import treulo.src.Controler.task.AddTaskCollaboratorControl;
import treulo.src.Controler.task.AddTaskDependencyControl;
import treulo.src.Controler.task.EditTaskDetailControl;
import treulo.src.Controler.task.SetParentTaskControl;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;

import static treulo.src.model.TreuloTask.getAlltasks;

public class DetailTaskDisplay implements Display {
    Treulo model;
    TreuloTask task;

    public DetailTaskDisplay(Model m) {
        this.model = (Treulo) m;
        this.task = this.model.getTache();
    }

    @Override
    public Node getDisplay() {
        VBox VboxMain =new VBox();

        BorderPane bP = new BorderPane();
        //Top

        VBox VBoxTop =new VBox();
        HBox hbBack =new HBox();
        Button bBack = new Button("Retour");

        hbBack.getChildren().add(bBack);
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        Label lBTop = new Label("Affichage tache");

        hbTitle.getChildren().add(lBTop);
        VBoxTop.getChildren().addAll(hbBack,hbTitle);

        bP.setTop(VBoxTop);
        //Center
        GridPane gP = new GridPane();
        VBox vBoxCenter= new VBox();

        HBox hbCenter1 = new HBox();
        Label lBCenter1 = new Label("Nom de la tache ");
        TextField tFCenter1= new TextField(this.task.getName());
        hbCenter1.getChildren().addAll(lBCenter1,tFCenter1);
        HBox hbCenter2 = new HBox();
        Label lBCenter2 = new Label("Description         ");
        TextField tFCenter2= new TextField(this.task.getDescription());
        hbCenter2.getChildren().addAll(lBCenter2,tFCenter2);

        HBox hDuree = new HBox();
        Label lDuree = new Label("Duree (en Heure)");

        TextField tFDuree = new TextField();
        tFDuree.setTextFormatter(new TextFormatter<>(new FloatStringConverter()));
        tFDuree.setText(String.valueOf(this.task.getDuration()));
        hDuree.getChildren().addAll(lDuree,tFDuree);
        vBoxCenter.getChildren().addAll(hbCenter1,hbCenter2,hDuree);

        gP.add(vBoxCenter,2,1);


        VBox VBoxCenter2 = new VBox();

        HBox hbCenter4 = new HBox();
        Label lBCenter4 = new Label("Dependance ");

        String sas = "Dépendance :"+"\n";
        for (TreuloTask tt : this.model.getDependencieTempo()){
            sas+= tt.getName() + "\n";
        }

        Label label = new Label(sas);

        ArrayList<TreuloTask> al = this.task.getDependencies();
        for (TreuloTask tt : al){
            label.setText(label.getText()+"\n"+tt.getName());
        }

        label.setBorder(Border.stroke(Color.BLACK));
        label.setBackground(Background.fill(Color.BISQUE));
        label.setMaxWidth(Double.MAX_VALUE);

        //comboBox
        ComboBox <TreuloTask> combo= new ComboBox <TreuloTask>();
        combo.getItems().add(null);
        for (int i=0;i<getAlltasks().size();i++)
        {
            combo.getItems().add(getAlltasks().get(i));
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
        for (int i = 0;i<model.getCollaboratorTempo().size();i++)
        {
            collab+="  "+model.getCollaboratorTempo().get(i)+"\n";
        }
        Label lbCenter = new Label(collab);
        lbCenter.setBorder(Border.stroke(Color.BLACK));
        lbCenter.setBackground(Background.fill(Color.BISQUE));
        lbCenter.setMaxWidth(Double.MAX_VALUE);
        ArrayList<String> arrayString =  this.task.getCollaborators();
        for(String s : arrayString){
            lbCenter.setText(lbCenter.getText()+"\n"+s);
        }

        hbCenter3.getChildren().addAll(lBCenter3,tFCenter3,bCenter3);


        VBoxCenter2.getChildren().addAll(hbCenter4,label,hbCenter3 , lbCenter);
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

        combo2.setValue(this.task.getParentTask());

        Button set = new Button("Set");
        set.setOnAction(new SetParentTaskControl(this.model , combo2 , task));

        HBox hb = new HBox();
        hb.getChildren().addAll(combo2 , set);

        VBoxCenter3.getChildren().addAll(lBCenter5,hb);

        gP.add(VBoxCenter3,4,2);
        gP.setAlignment(Pos.CENTER);
        bP.setCenter(gP);

        bBack.setOnAction(new EditTaskDetailControl(model,model.getEditedTaskList(),tFCenter1,tFCenter2,combo , task , tFDuree));

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