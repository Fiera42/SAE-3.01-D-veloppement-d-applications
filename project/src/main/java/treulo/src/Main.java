package treulo.src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

        public void start(Stage stage) {
            BorderPane borderP = new BorderPane();


            //Le Top

            Button buttonFile= new Button("File");
            Button buttonGantt= new Button("GANTT");
            Button buttonListe= new Button("Liste");
            Button buttontableau= new Button("Tableau");
            Button ajoutTache= new Button("AjouterTache");

            BorderPane bpTop = new BorderPane();
            //topLeft
            HBox hbLTB = new HBox();
            hbLTB.getChildren().addAll(buttonGantt,buttonListe,buttontableau);
            VBox vbLT = new VBox();
            vbLT.getChildren().addAll(buttonFile,hbLTB);

            bpTop.setLeft(vbLT);
            //top top
            Text txtT= new Text("Organisateur de tâches");
            bpTop.setTop(txtT);
            //top Right
            HBox hbTR =new HBox();
            CheckBox archive = new CheckBox();
            Label txtTR = new Label("Afficher les archives");
            hbTR.getChildren().addAll(txtTR,archive);
            bpTop.setRight(hbTR);

            //top center
            Text titre = new Text("Projet 1");
            bpTop.setCenter(titre);

            borderP.setTop(bpTop);

            /*
            borderP.setBottom();
            */
            Scene scene = new Scene(borderP, 350, 350);
            stage.setScene(scene);
            stage.setTitle("Treulo");
            stage.show();
    }
}
