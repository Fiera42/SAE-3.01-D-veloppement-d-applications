package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import treulo.src.Controler.dragNdrop.DragOverControl;
import treulo.src.Controler.dragNdrop.EndDragControl;
import treulo.src.Controler.dragNdrop.ReceiveDragControl;
import treulo.src.Controler.dragNdrop.StartDragControl;
import treulo.src.Controler.task.AddTaskMenuControl;
import treulo.src.Controler.task.DeleteTaskControl;
import treulo.src.Controler.task.DetailTaskControl;
import treulo.src.Controler.task.EditTreuloTaskControl;
import treulo.src.Controler.tasklist.AddTaskListMenuControl;
import treulo.src.Controler.tasklist.DeleteTaskListControl;
import treulo.src.Controler.tasklist.EditTaskListControl;
import treulo.src.Controler.tasklist.EditedTaskListControl;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;
import java.util.LinkedList;


//afficheur bureau
public class DeskDisplay implements Display {

    private LinkedList<TaskList> taskLists;
    private Treulo model;

    private static final int COLUMN_WIDTH = 350;


    public DeskDisplay(Treulo treulo, LinkedList<TaskList> taskLists) {
        this.model = treulo;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        //System.out.println("--------------------------------------------");
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(10));

        //Pour chaque liste de tâche, crée une colonne
        for(TaskList taskList : taskLists) {
            hBox.getChildren().add(getTaskListDisplay(taskList));
        }

        //Bouton de création de tâche
        Button button = new Button("Nouvelle liste");
        button.setOnAction(new AddTaskListMenuControl(model));
        button.setPrefWidth(COLUMN_WIDTH);
        button.setAlignment(Pos.CENTER);
        HBox buttonBox = new HBox(button);
        buttonBox.setMinWidth(COLUMN_WIDTH);
        buttonBox.setMaxWidth(COLUMN_WIDTH);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        button.setOnAction(new AddTaskListMenuControl(model));
        hBox.getChildren().add(buttonBox);

        return hBox;
    }

    //Méthode pour récupérer l'affichage d'une liste en desk display
    public Node getTaskListDisplay(TaskList taskList) {
        //System.out.println("TASKLIST - Tasks : " + taskList.getTasks().size() + " Observators : " + taskList.getObservators().size());

        //Mise en page de la colonne
        VBox vBox = new VBox(10);
        vBox.setOnMouseEntered(new EditedTaskListControl(model,taskList));
        vBox.setPadding(new Insets(10));
        vBox.setMinWidth(COLUMN_WIDTH);
        vBox.setMaxWidth(COLUMN_WIDTH);
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(10), new Insets(0))));
        vBox.setOnDragDetected(new StartDragControl(model, taskList));
        vBox.setOnDragDone(new EndDragControl(model, vBox));

        ArrayList<VBox> tasks = new ArrayList<>();
        ReceiveDragControl receiveControl = new ReceiveDragControl(model, taskList, tasks);
        vBox.setOnDragDropped(receiveControl);

        vBox.setOnDragOver(new DragOverControl(model, taskList));

        //Affichage des informations relatives à la liste
        HBox titre = new HBox(10);
        vBox.getChildren().add(titre);

        TextField listName = new TextField(taskList.getName());
        EditTaskListControl editionControl = new EditTaskListControl(model, taskList, listName);

        listName.setOnAction(editionControl);
        listName.focusedProperty().addListener(editionControl);

        titre.getChildren().add(listName);
        HBox.setHgrow(listName, Priority.ALWAYS);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(taskList.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        titre.getChildren().add(archive);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskListControl(model, taskList));
        titre.getChildren().add(delete);



        //Pour chaque tâche de la liste, on récupère son affichage
        for(TreuloTask task : taskList) {
            Node taskDisplay = getTaskDisplay(task, new VBox());
            vBox.getChildren().add(taskDisplay);
            tasks.add((VBox)taskDisplay);
        }

        //Bouton d'ajout de nouvelle tâche
        Button button = new Button("Nouvelle tâche");
        button.setOnAction(new AddTaskMenuControl(model));
        button.setAlignment(Pos.CENTER);
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttonBox);

        return vBox;
    }

    //Affichage récursif des tâches
    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        //System.out.println("TASK - SubTasks : " + task.getSubtasks().size() + " Observators : " + task.getObservators().size());

        //Déprécié
        VBox parent = (VBox)parentNode;

        //Affichage des informations de la tâche
        VBox vBox = new VBox(10);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5), new Insets(0))));
        vBox.setPadding(new Insets(10));
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
        vBox.setOnDragDetected(new StartDragControl(model, task));
        vBox.setOnDragDone(new EndDragControl(model, vBox));

        ArrayList<VBox> tasks = new ArrayList<>();
        ReceiveDragControl receiveControl = new ReceiveDragControl(model, task, tasks);
        vBox.setOnDragDropped(receiveControl);

        vBox.setOnDragOver(new DragOverControl(model, task));

        vBox.setOnMouseClicked(new DetailTaskControl(model , task));

        HBox name = new HBox(10);
        vBox.getChildren().add(name);
        TextField nameText = new TextField(task.getName());
        name.getChildren().add(nameText);
        HBox.setHgrow(nameText, Priority.ALWAYS);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(task.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        name.getChildren().add(archive);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskControl(model, task));
        name.getChildren().add(delete);

        TextArea description = new TextArea(task.getDescription());
        description.setMaxHeight(100);
        description.setMinHeight(100);
        description.setWrapText(true);
        HBox.setHgrow(description, Priority.ALWAYS);
        vBox.getChildren().add(description);

        //Event de Modification
        nameText.setOnKeyPressed(new EditTreuloTaskControl(model, task , nameText , description));
        description.setOnKeyPressed(new EditTreuloTaskControl(model, task , nameText , description));

        //Affichage récursif des sous-tâches
        for(TreuloTask child : task.getSubtasks()) {
            Node taskDisplay = getTaskDisplay(child, vBox);
            vBox.getChildren().add(taskDisplay);
            tasks.add((VBox)taskDisplay);
        }

        return vBox;
    }
}
