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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import treulo.src.Controler.*;
import treulo.src.Controler.dragNdrop.DragOverControl;
import treulo.src.Controler.dragNdrop.EndDragControl;
import treulo.src.Controler.dragNdrop.ReceiveDragControl;
import treulo.src.Controler.dragNdrop.StartDragControl;
import treulo.src.Controler.task.AddTaskMenuControl;
import treulo.src.Controler.task.DeleteTaskControl;
import treulo.src.Controler.task.EditTreuloTaskControl;
import treulo.src.Controler.tasklist.AddTaskListMenuControl;
import treulo.src.Controler.tasklist.DeleteTaskListControl;
import treulo.src.Controler.tasklist.EditTaskListControl;
import treulo.src.Controler.tasklist.EditedTaskListControl;
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
        VBox hBox = new VBox(25);
        hBox.setPadding(new Insets(10));

        for(TaskList taskList : taskLists) {
            hBox.getChildren().add(getTaskListDisplay(taskList));
            HBox line = new HBox();
            line.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 2, 0))));
            hBox.setMargin(line, new Insets(0, 200, 0, 200));
            hBox.getChildren().add(line);
        }
        if(hBox.getChildren().size() > 0) hBox.getChildren().remove(hBox.getChildren().size() -1);

        Button button = new Button("Nouvelle liste");
        button.setOnAction(new AddTaskListMenuControl(model));
        button.setPrefWidth(400);
        button.setFont(new Font(32));
        button.setAlignment(Pos.CENTER);
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        button.setOnAction(new AddTaskListMenuControl(model));
        hBox.getChildren().add(buttonBox);

        return hBox;
    }

    public Node getTaskListDisplay(TaskList taskList) {
        VBox vb = new VBox();
        HBox hBoxHead = new HBox(10);
        hBoxHead.setOnMouseEntered(new EditedTaskListControl(model,taskList));
        hBoxHead.setPadding(new Insets(10));
        hBoxHead.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        hBoxHead.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(10), new Insets(0))));
        vb.setOnDragDetected(new StartDragControl(model, taskList));
        vb.setOnDragDone(new EndDragControl(model, vb));
        vb.setOnDragDropped(new ReceiveDragControl(model, taskList));
        vb.setOnDragOver(new DragOverControl(model, taskList));


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

        if (taskList.getdeploy()){

        vBoxTask.setMargin(vBoxTask , new Insets(10,0,0,25));
        for(TreuloTask task : taskList) {
            vBoxTask.getChildren().add(getTaskDisplay(task, new HBox()));
        }
            Button deploy = new Button("roulé");
            deploy.setOnAction(new DeployListControl(model,taskList,deploy));
            hBoxHead.getChildren().add(deploy);
        }

        else
        {
            Button deploy = new Button("déroulé");
            deploy.setOnAction(new DeployListControl(model,taskList,deploy));
            hBoxHead.getChildren().add(deploy);
        }



        Button button = new Button("Nouvelle tâche");
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(COLUMN_WIDTH);
        button.setOnAction(new AddTaskMenuControl(model));
        VBox buttonBox = new VBox(button);
        buttonBox.setAlignment(Pos.CENTER);
        vBoxTask.getChildren().add(buttonBox);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskListControl(model, taskList));
        hBoxHead.getChildren().add(delete);
        delete.setAlignment(Pos.TOP_RIGHT );




        vb.getChildren().addAll(hBoxHead , vBoxTask);


        return vb;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {

        VBox vb = new VBox(10);
        vb.setPadding(new Insets(10));
        VBox vBox = new VBox(10);
        vb.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5), new Insets(0))));
        vBox.setOnDragDetected(new StartDragControl(model, task));
        vBox.setOnDragDone(new EndDragControl(model, vBox));
        vBox.setOnDragDropped(new ReceiveDragControl(model, task));
        vBox.setOnDragOver(new DragOverControl(model, task));


        HBox name = new HBox(10);
        vb.getChildren().add(name);
        TextField nameText = new TextField(task.getName());
        name.getChildren().add(nameText);
        HBox.setHgrow(nameText, Priority.ALWAYS);
        vBox.setMargin( vBox , new Insets(0,10,10,50));
        vb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(task.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //archive.setOnAction(new EditTaskListControl(model, taskList));
        name.getChildren().add(archive);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskControl(model, task));
        name.getChildren().add(delete);


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
