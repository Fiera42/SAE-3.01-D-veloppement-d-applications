package treulo.src.view.appview.display;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import treulo.src.Controler.*;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;

public class DeskDisplay implements Display {

    private LinkedList<TaskList> taskLists;
    private Treulo model;

    private static final int COLUMN_WIDTH = 250;


    public DeskDisplay(Treulo treulo, LinkedList<TaskList> taskLists) {
        this.model = treulo;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(10));

        for(TaskList taskList : taskLists) {
            hBox.getChildren().add(getTaskListDisplay(taskList));
        }

        Button button = new Button("Nouvelle liste");
        button.setOnAction(new AddTaskListMenuControl(model));
        HBox buttonBox = new HBox(button);
        buttonBox.setMinWidth(COLUMN_WIDTH);
        buttonBox.setMaxWidth(COLUMN_WIDTH);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        button.setOnAction(new AddTaskListMenuControl(model));
        hBox.getChildren().add(buttonBox);

        return hBox;
    }

    public Node getTaskListDisplay(TaskList taskList) {
        VBox vBox = new VBox(10);
        vBox.setOnMouseEntered(new EditedTaskListControl(model,taskList));
        vBox.setPadding(new Insets(10));
        vBox.setMinWidth(COLUMN_WIDTH);
        vBox.setMaxWidth(COLUMN_WIDTH);
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));

        HBox titre = new HBox(10);
        vBox.getChildren().add(titre);

        TextField listName = new TextField(taskList.getName());
        EditTaskListControl editionControl = new EditTaskListControl(model, taskList, listName);

        listName.setOnAction(editionControl);
        listName.focusedProperty().addListener(editionControl);

        titre.getChildren().add(listName);
        HBox.setHgrow(listName, Priority.ALWAYS);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskListControl(model, taskList));
        titre.getChildren().add(delete);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(taskList.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        vBox.getChildren().add(archive);

        for(TreuloTask task : taskList) {
            vBox.getChildren().add(getTaskDisplay(task, new VBox()));
        }


        Button button = new Button("Nouvelle tâche");
        button.setOnAction(new AddTaskMenuControl(model));
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttonBox);

        return vBox;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        VBox parent = (VBox)parentNode;

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        HBox name = new HBox(10);
        vBox.getChildren().add(name);
        TextField nameText = new TextField(task.getName());
        //listName.setOnAction(new EditTaskControl(model, task));
        name.getChildren().add(nameText);
        HBox.setHgrow(nameText, Priority.ALWAYS);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskControl(model, task));
        name.getChildren().add(delete);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(task.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        vBox.getChildren().add(archive);

        TextArea description = new TextArea(task.getDescription());
        description.setWrapText(true);
        HBox.setHgrow(description, Priority.ALWAYS);
        vBox.getChildren().add(description);

        for(TreuloTask child : task.getSubtasks()) {
            vBox.getChildren().add(getTaskDisplay(child, vBox));
        }

        return vBox;
    }
}
