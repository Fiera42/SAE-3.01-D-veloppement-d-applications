package treulo.src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import treulo.src.Controler.ChangeDisplayControl;
import treulo.src.Controler.ToggleArchiveControl;
import treulo.src.Controler.file_management.*;
import treulo.src.Controler.to_image.ExportAsImageControl;
import treulo.src.model.Treulo;
import treulo.src.view.FilenameObservor;
import treulo.src.view.appview.AppView;
import treulo.test.DummieTreuloGenerator;


public class Main extends Application {

    private static final String applicationName = "Treulo";
    private static final int appWidth = 854;
    private static final int appHeight = 480;

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        //nom, largeur et hauteur
        stage.setTitle(applicationName);

        BorderPane scene = new BorderPane();
        scene.setBackground(Background.fill(Color.AQUAMARINE));

        Treulo model = DummieTreuloGenerator.generateDummie();
        model.setDisplayMode("Tableau");

        //-------------------- BARRE DU HAUT
        BorderPane nav = new BorderPane();
        nav.setBackground(new Background(new BackgroundFill(Color.BISQUE, new CornerRadii(10), new Insets(0))));;
        nav.setPadding(new Insets(10));
        BorderPane.setMargin(nav, new Insets(10));
        nav.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        scene.setTop(nav);

        //-----titre
        Label title = new Label("Treulo");
        title.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        nav.setCenter(title);

        //-----boutons gauche
        MenuButton buttonFile= new MenuButton("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau...");
        nouveau.setOnAction(new NewFileControl(model));
        MenuItem ouvrir = new MenuItem("Ouvrir...");
        ouvrir.setOnAction(new OpenFileControl(model, stage));
        MenuItem enregistrer = new MenuItem("Enregistrer...");
        enregistrer.setOnAction(new SaveFileControl(model));
        MenuItem enregistrer_sous = new MenuItem("Enregistrer sous...");
        enregistrer_sous.setOnAction(new SaveAsControl(model, stage));
        MenuItem exporter_img = new MenuItem("Exporter en image...");
        //Handler plus bas
        buttonFile.getItems().addAll(nouveau, ouvrir, enregistrer, enregistrer_sous, exporter_img);

        FilenameObservor filenameObservor = new FilenameObservor(model.getFilename());
        model.addObservator(filenameObservor);
        HBox file_box = new HBox(buttonFile, filenameObservor);
        file_box.setSpacing(10);
        file_box.setAlignment(Pos.CENTER_LEFT);
        buttonFile.setOnAction(new FileControlDispatcher(model));

        //permet d'avoir des boutons normaux avec des propriétés de radio button
        ToggleGroup displayMode = new ToggleGroup();

        RadioButton buttonGantt = new RadioButton("GANTT");
        buttonGantt.getStyleClass().remove("radio-button");
        buttonGantt.getStyleClass().add("toggle-button");
        buttonGantt.setToggleGroup(displayMode);
        buttonGantt.setOnAction(new ChangeDisplayControl(model ,buttonGantt.getText()));

        RadioButton buttonListe = new RadioButton("Liste");
        buttonListe.setOnAction(new ChangeDisplayControl(model ,buttonListe.getText()));
        buttonListe.getStyleClass().remove("radio-button");
        buttonListe.getStyleClass().add("toggle-button");
        buttonListe.setToggleGroup(displayMode);

        RadioButton buttontableau = new RadioButton("Tableau");
        buttontableau.getStyleClass().remove("radio-button");
        buttontableau.getStyleClass().add("toggle-button");
        buttontableau.setToggleGroup(displayMode);
        buttontableau.setOnAction(new ChangeDisplayControl(model ,buttontableau.getText()));

        HBox displayModeButtons = new HBox(buttontableau, buttonListe, buttonGantt);
        displayModeButtons.setSpacing(10);
        VBox leftButtons = new VBox(file_box, displayModeButtons);
        leftButtons.setSpacing(5);
        nav.setLeft(leftButtons);

        //-----boutons droite (archive)
        CheckBox archive = new CheckBox();
        Label archiveText = new Label("Afficher les archives : ");
        archiveText.setFont(new Font(17));
        HBox archiveBox = new HBox(archiveText, archive);
        archiveBox.setAlignment(Pos.CENTER);
        nav.setRight(archiveBox);

        archive.setOnAction(new ToggleArchiveControl(model));

        //-------------------- AFFICHAGE TACHE

        AppView appView = new AppView();
        appView.update(model);
        scene.setCenter(appView);
        model.addObservator(appView);

        //--------------------
        Scene stageScene = new Scene(scene, appWidth, appHeight);
        exporter_img.setOnAction(new ExportAsImageControl(model, stage, stageScene));
        stageScene.getStylesheets().add("treuloStyle.css");
        stage.setScene(stageScene);
        stage.setMaximized(true);
        stage.show();
    }
}
