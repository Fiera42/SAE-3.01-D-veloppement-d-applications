package treulo.src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

        public void start(Stage stage) {
            BorderPane borderP = new BorderPane();


            //Le Top

            StackPane stpT = new StackPane();
            Rectangle rtt=new Rectangle(500,100);
            rtt.setFill(Color.WHITE);

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
            HBox hbtt = new HBox(txtT);
            hbtt.setAlignment(Pos.CENTER);
            hbtt.setBorder(Border.stroke(Color.BLACK));
            bpTop.setTop(hbtt);
            //top Right
            HBox hbTR =new HBox();
            CheckBox archive = new CheckBox();
            Label txtTR = new Label("Afficher les archives");
            hbTR.getChildren().addAll(txtTR,archive);
            bpTop.setRight(hbTR);

            //top center
            Text titre = new Text("Projet 1");
            bpTop.setCenter(titre);
            bpTop.setBorder(Border.stroke(Color.BLACK));



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
