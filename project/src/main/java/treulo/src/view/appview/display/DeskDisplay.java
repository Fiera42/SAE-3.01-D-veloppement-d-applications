package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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


    public DeskDisplay(Treulo treulo, LinkedList<TaskList> taskLists) {
        this.model = treulo;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10));

        for(TaskList taskList : taskLists) {
            hBox.getChildren().add(getTaskListDisplay(taskList));
        }

        Button button = new Button("Nouvelle liste");
        button.setOnAction(new AddTaskListMenuControl(model));
        HBox buttonBox = new HBox(button);
        buttonBox.setPrefWidth(300);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        button.setOnAction(new AddTaskListMenuControl(model));
        hBox.getChildren().add(buttonBox);

        return hBox;
    }

    public Node getTaskListDisplay(TaskList taskList) {
        VBox vBox = new VBox(10);
        vBox.setOnMouseEntered(new EditedTaskListControl(model,taskList));
        vBox.setPadding(new Insets(10));
        vBox.setPrefWidth(300);
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));

        HBox titre = new HBox(10);
        vBox.getChildren().add(titre);

        TextField listName = new TextField(taskList.getName());
        //listName.setOnAction(new EditTaskListControl(model, taskList));
        titre.getChildren().add(listName);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(taskList.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        titre.getChildren().add(archive);

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
        VBox vBox = new VBox();

        Insets insets = parent.getPadding();
        vBox.setPadding(new Insets(insets.getTop(), insets.getRight(), insets.getBottom(), insets.getLeft() + 5));

        vBox.getChildren().add(new Label("-" + task.getName() + " (" + task.getDescription() + ")"));
        for(TreuloTask child : task.getSubtasks()) {
            vBox.getChildren().add(getTaskDisplay(child, vBox));
        }

        return vBox;
    }
}
