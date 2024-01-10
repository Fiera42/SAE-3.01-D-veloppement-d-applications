package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.converter.FloatStringConverter;
import treulo.src.Controler.BackControler;
import treulo.src.Controler.task.*;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.net.SocketOption;
import java.util.ArrayList;

import static treulo.src.model.TreuloTask.getAlltasks;

//Classe gérant l'affichage en détail d'une tache
//Classe originale : Doryann
public class DetailTaskDisplay implements Display {
    Treulo model;
    TreuloTask task;

    public DetailTaskDisplay(Model m) {
        this.model = (Treulo) m;
        this.task = this.model.getTache();
    }

    @Override
    public Node getDisplay() {
        // Création de la structure principale de l'interface utilisateur
        VBox VboxMain =new VBox();

        // Création d'un panneau de bord avec des zones définies (Top, Center)
        BorderPane bP = new BorderPane();

        // Zone de retour
        VBox VBoxTop =new VBox();
        HBox hbBack =new HBox();
        Button bBack = new Button("Retour");
        hbBack.getChildren().add(bBack);

        // Zone du titre
        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        Label lBTop = new Label("Affichage tache");
        lBTop.setFont(new Font(30));
        hbTitle.getChildren().add(lBTop);

        // Ajout des sections Top à VBoxTop
        VBoxTop.getChildren().addAll(hbBack,hbTitle);

        // Ajout de VBoxTop à la section Top du BorderPane
        bP.setTop(VBoxTop);

        // Section Center de l'interface
        GridPane gP = new GridPane();
        VBox vBoxCenter= new VBox();

        // Détails de la tâche : nom, description, durée, archivage
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

        CheckBox archivage = new CheckBox("Archivé");
        archivage.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        archivage.setSelected(task.isArchived());
        archivage.setOnAction(new ArchiveTaskControl(model, task));

        // Section supplémentaire du Center pour les dépendances
        vBoxCenter.getChildren().addAll(hbCenter1,hbCenter2,hDuree, archivage);
        vBoxCenter.setAlignment(Pos.CENTER);

        gP.add(vBoxCenter,2,1);

        // Section supplémentaire du Center pour les dépendances
        VBox VBoxCenter2 = new VBox(10);

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
            b.setOnAction(new DeleteDependencyControle(model,tt , task));
            hb.getChildren().addAll(l , b);
            test.getChildren().add(hb);
        }

        ArrayList<TreuloTask> al = this.task.getDependencies();
        for (TreuloTask tt : al){
            HBox hb = new HBox();
            Label l = new Label(tt.getName()+"\n");
            Button b = new Button("-");
            b.setOnAction(new DeleteDependencyControle(model,tt , task));
            hb.getChildren().addAll(l , b);
            test.getChildren().add(hb);
        }

        test.setBorder(Border.stroke(Color.BLACK));
        test.setBackground(Background.fill(Color.BISQUE));
        test.setMaxWidth(Double.MAX_VALUE);

        //comboBox
        ComboBox <TreuloTask> combo= new ComboBox <TreuloTask>();
        combo.getItems().add(null);
        for (TreuloTask tt : getAlltasks())
        {
            if (!this.task.getName().equals(tt.getName()) && !this.task.getDependencies().contains(tt) && !this.model.getDependencieTempo().contains(tt)) {
                combo.getItems().add(tt);
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
            b.setOnAction(new DeleteCollaboratorControle(model , s , task));
            hb.getChildren().addAll(l,b);
            vb.getChildren().add(hb);
        }

        vb.setBorder(Border.stroke(Color.BLACK));
        vb.setBackground(Background.fill(Color.BISQUE));
        vb.setMaxWidth(Double.MAX_VALUE);
        ArrayList<String> arrayString =  this.task.getCollaborators();


        for(String s : arrayString){
            HBox hb = new HBox();
            Label l = new Label(s);
            Button b = new Button("-");
            b.setOnAction(new DeleteCollaboratorControle(model , s , task));
            hb.getChildren().addAll(l,b);
            vb.getChildren().add(hb);
        }


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
            if(getAlltasks().get(i).getId() == task.getId()) continue;
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

        // Action associée au bouton de retour
        bBack.setOnAction(new EditTaskDetailControl(model,model.getEditedTaskList(),tFCenter1,tFCenter2,combo , task , tFDuree));

        // Ajout du BorderPane à la VBox principale
        VboxMain.getChildren().add(bP);
        VboxMain.setAlignment(Pos.TOP_CENTER);

        // Retourne la VBox principale en tant que Node
        return VboxMain;
    }


    // cette methode est inutile pour cette classe
    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        return null;
    }
}
