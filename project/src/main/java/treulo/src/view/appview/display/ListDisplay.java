package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import treulo.src.Controler.*;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;

public class ListDisplay implements Display {

    private LinkedList<TaskList> taskLists;
    private Treulo model;

    private static final int COLUMN_WIDTH = 250;


    public ListDisplay(Treulo treulo, LinkedList<TaskList> taskLists) {
        this.model = treulo;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        VBox hBox = new VBox(20);
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

        System.out.println("List");

        return hBox;
    }

    public Node getTaskListDisplay(TaskList taskList) {
        VBox vb = new VBox();
        HBox hBoxHead = new HBox(10);
        hBoxHead.setOnMouseEntered(new EditedTaskListControl(model,taskList));
        hBoxHead.setPadding(new Insets(10));
        //hBoxHead.setMinHeight(COLUMN_WIDTH);
        hBoxHead.setMaxHeight(COLUMN_WIDTH);
        hBoxHead.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        vb.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(10), new Insets(0))));
        vb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(0,2,0,0))));



        VBox titre = new VBox(10);
        hBoxHead.getChildren().add(titre);

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
        hBoxHead.getChildren().add(archive);

        VBox vBoxTask = new VBox();
        vBoxTask.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0,0,0,2))));
        vBoxTask.setMargin(vBoxTask , new Insets(10,0,0,25));
        for(TreuloTask task : taskList) {
            vBoxTask.getChildren().add(getTaskDisplay(task, new HBox()));
        }



        Button button = new Button("Nouvelle tâche");
        button.setOnAction(new AddTaskMenuControl(model));
        VBox buttonBox = new VBox(button);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        hBoxHead.getChildren().add(buttonBox);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskListControl(model, taskList));
        hBoxHead.getChildren().add(delete);
        delete.setAlignment(Pos.TOP_RIGHT );

        vb.getChildren().addAll(hBoxHead , vBoxTask);


        return vb;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {

        VBox vb = new VBox();
        VBox vBox = new VBox(10);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5), new Insets(0))));


        HBox name = new HBox(10);
        vb.getChildren().add(name);
        TextField nameText = new TextField(task.getName());
        name.getChildren().add(nameText);
        HBox.setHgrow(nameText, Priority.ALWAYS);
        vBox.setMargin( vBox , new Insets(0,10,10,50));
        vb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskControl(model, task));
        vb.getChildren().add(delete);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(task.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        vb.getChildren().add(archive);


        TextArea description = new TextArea(task.getDescription());
        description.setWrapText(true);
        HBox.setHgrow(description, Priority.ALWAYS);
        vb.getChildren().add(description);

        vBox.getChildren().add(vb);

        //Event de Modification
        nameText.setOnKeyPressed(new EditTreuloTaskControl(model, task , nameText , description));
        description.setOnKeyPressed(new EditTreuloTaskControl(model, task , nameText , description));

        VBox vBoxSubTask = new VBox();


        if (!task.getSubtasks().isEmpty()) {
            vBoxSubTask.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 0, 2))));
            //vBoxSubTask.setPadding(new Insets(50));
            vBoxSubTask.setMargin(vBoxSubTask , new Insets(0,0,0,50));

        }
        for(TreuloTask child : task.getSubtasks()) {
            vBoxSubTask.getChildren().add(getTaskDisplay(child, vBox));
        }
        vBox.getChildren().addAll(vBoxSubTask);

        return vBox;
    }
}
