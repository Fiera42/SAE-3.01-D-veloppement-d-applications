package treulo.src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import treulo.src.model.Treulo;
import treulo.src.view.appview.AppView;
import treulo.test.DummieTreuloGenerator;
import treulo.test.Oui;

import java.io.File;
import java.io.IOException;


public class Main extends Application {

    private static final String applicationName = "Treulo";
    private static final int appWidth = 768;
    private static final int appHeight = 432;

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        //nom, largeur et hauteur
        stage.setTitle(applicationName);

        BorderPane scene = new BorderPane();

        Treulo model = Oui.generateDummie();

        //-------------------- BARRE DU HAUT
        BorderPane nav = new BorderPane();
        nav.setPadding(new Insets(10));
        nav.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        scene.setTop(nav);

        //-----titre
        Label title = new Label("Treulo");
        title.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        nav.setCenter(title);

        //-----boutons gauche
        Button buttonFile= new Button("File");

        //permet d'avoir des boutons normaux avec des propriétés de radio button
        ToggleGroup displayMode = new ToggleGroup();

        RadioButton buttonGantt = new RadioButton("GANTT");
        buttonGantt.getStyleClass().remove("radio-button");
        buttonGantt.getStyleClass().add("toggle-button");
        buttonGantt.setToggleGroup(displayMode);

        RadioButton buttonListe = new RadioButton("Liste");
        buttonListe.getStyleClass().remove("radio-button");
        buttonListe.getStyleClass().add("toggle-button");
        buttonListe.setToggleGroup(displayMode);

        RadioButton buttontableau = new RadioButton("Tableau");
        buttontableau.getStyleClass().remove("radio-button");
        buttontableau.getStyleClass().add("toggle-button");
        buttontableau.setToggleGroup(displayMode);

        HBox displayModeButtons = new HBox(buttontableau, buttonListe, buttonGantt);
        displayModeButtons.setSpacing(10);
        VBox leftButtons = new VBox(buttonFile, displayModeButtons);
        leftButtons.setSpacing(5);
        nav.setLeft(leftButtons);

        //-----boutons droite (archive)
        CheckBox archive = new CheckBox();
        Label archiveText = new Label("Afficher les archives : ");
        HBox archiveBox = new HBox(archiveText, archive);
        archiveBox.setAlignment(Pos.CENTER);
        nav.setRight(archiveBox);

        //-------------------- AFFICHAGE TACHE

        AppView appView = new AppView();
        appView.update(model);
        scene.setCenter(appView);
        model.addObservator(appView);

        //--------------------
        Scene stageScene = new Scene(scene, appWidth, appHeight);
        stageScene.getStylesheets().add("treuloStyle.css");
        stage.setScene(stageScene);
        stage.show();
    }
}
