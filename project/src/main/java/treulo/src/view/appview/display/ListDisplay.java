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
import treulo.src.Controler.task.ArchiveTaskControl;
import treulo.src.Controler.task.DeleteTaskControl;
import treulo.src.Controler.task.DetailTaskControl;
import treulo.src.Controler.task.EditTreuloTaskControl;
import treulo.src.Controler.tasklist.*;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;
import java.util.LinkedList;


//Afficheur sous forme de liste
//Classe originale : Doryann et Adrien
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
        VBox vBox = new VBox(25);
        vBox.setPadding(new Insets(10));

        //Récupèration de l'affichage pour chaque liste
        for(TaskList taskList : taskLists) {
            if(model.getDisplayArchive() || !taskList.isArchived()) {
                vBox.getChildren().add(getTaskListDisplay(taskList));

                //Ajout d'une ligne entre chaque liste
                HBox line = new HBox();
                line.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 2, 0))));
                vBox.setMargin(line, new Insets(0, 200, 0, 200));
                vBox.getChildren().add(line);
            }
        }
        //Pas de ligne entre la dernière liste et le bouton d'ajout de liste
        if(vBox.getChildren().size() > 0) vBox.getChildren().remove(vBox.getChildren().size() -1);

        //Bouton d'ajout de liste
        Button button = new Button("Nouvelle liste");
        button.setOnAction(new AddTaskListMenuControl(model));
        button.setPrefWidth(400);
        button.setFont(new Font(32));
        button.setAlignment(Pos.CENTER);
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        button.setOnAction(new AddTaskListMenuControl(model));
        vBox.getChildren().add(buttonBox);

        return vBox;
    }

    //Méthode pour obtenir l'affichage d'une liste
    public Node getTaskListDisplay(TaskList taskList) {
        //Affichage des informations de la tâche
        VBox vb = new VBox(10);
        HBox hBoxHead = new HBox(10);
        hBoxHead.setOnMouseEntered(new EditedTaskListControl(model,taskList));
        hBoxHead.setPadding(new Insets(10));
        hBoxHead.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));

        if(taskList.isArchived()) {
            hBoxHead.setBackground(new Background(new BackgroundFill(Color.THISTLE, new CornerRadii(10), new Insets(0))));
        }
        else hBoxHead.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(10), new Insets(0))));

        vb.setOnDragDetected(new StartDragControl(model, taskList));
        vb.setOnDragDone(new EndDragControl(model, vb));

        ArrayList<VBox> tasks = new ArrayList<>();
        ReceiveDragControl receiveControl = new ReceiveDragControl(model, taskList, tasks);
        vb.setOnDragDropped(receiveControl);

        vb.setOnDragOver(new DragOverControl(model, taskList));

        TextField listName = new TextField(taskList.getName());
        EditTaskListControl editionControl = new EditTaskListControl(model, taskList, listName);

        hBoxHead.getChildren().add(listName);
        HBox.setHgrow(listName, Priority.ALWAYS);

        listName.setOnAction(editionControl);
        listName.focusedProperty().addListener(editionControl);

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(taskList.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        archive.setOnAction(new ArchiveTaskListControl(model, taskList));
        hBoxHead.getChildren().add(archive);

        VBox vBoxTask = new VBox(10);
        vBoxTask.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0,0,0,2))));
        //vBoxTask.setMargin(vBoxTask , new Insets(10,0,0,25));
        vBoxTask.setPadding(new Insets(10,0,0,0));

        //Gestion du déroulement de la liste
        if (taskList.getdeploy() && !taskList.getTasks().isEmpty()) {
            //Récupèration de l'affichage des tâches
            for(TreuloTask task : taskList) {
                if(model.getDisplayArchive() || !task.isArchived()) {
                    Node taskDisplay = getTaskDisplay(task, new VBox());
                    vBoxTask.getChildren().add(taskDisplay);
                    tasks.add((VBox)taskDisplay);
                }

            }

            Button deploy = new Button("▲");
            deploy.setOnAction(new DeployListControl(model,taskList));
            hBoxHead.getChildren().add(deploy);

        }

        else
        {
            if(taskList.getTasks().size() > 0) {
                Button deploy = new Button("▼");
                deploy.setOnAction(new DeployListControl(model,taskList));
                hBoxHead.getChildren().add(deploy);
            }

        }

        //Bouton de nouvelle tâche
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

        if(task.isArchived()) {
            vb.setBackground(new Background(new BackgroundFill(Color.THISTLE, new CornerRadii(5), new Insets(0))));
        }
        else vb.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5), new Insets(0))));

        vBox.setOnDragDetected(new StartDragControl(model, task));
        vBox.setOnDragDone(new EndDragControl(model, vBox));

        ArrayList<VBox> tasks = new ArrayList<>();
        ReceiveDragControl receiveControl = new ReceiveDragControl(model, task, tasks);
        vBox.setOnDragDropped(receiveControl);

        vBox.setOnDragOver(new DragOverControl(model, task));

        vBox.setOnMouseClicked(new DetailTaskControl(model , task));

        HBox name = new HBox(10);
        vb.getChildren().add(name);
        TextField nameText = new TextField(task.getName());
        name.getChildren().add(nameText);
        HBox.setHgrow(nameText, Priority.ALWAYS);
        vBox.setMargin( vBox , new Insets(0,0,0,50));
        vb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        CheckBox archive = new CheckBox("Archiver");
        archive.setSelected(task.isArchived());
        archive.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        archive.setOnAction(new ArchiveTaskControl(model, task));
        name.getChildren().add(archive);

        Button delete = new Button("X");
        delete.setOnAction(new DeleteTaskControl(model, task));
        name.getChildren().add(delete);


        TextArea description = new TextArea(task.getDescription());
        int i = task.getDescription().length() / 36 ;
        description.setPrefRowCount(i+1);
        description.setWrapText(true);
        //HBox.setHgrow(description, Priority.ALWAYS);
        vb.getChildren().add(description);

        vBox.getChildren().add(vb);

        //Event de Modification
        nameText.setOnKeyPressed(new EditTreuloTaskControl(model, task , nameText , description));
        description.setOnKeyPressed(new EditTreuloTaskControl(model, task , nameText , description));

        if (task.getDeploy() && !task.getSubtasks().isEmpty()) {
            VBox vBoxSubTask = new VBox(10);

            Button deploy = new Button("▲");
            deploy.setOnAction(new DeployTaskControl(model,task));
            name.getChildren().add(deploy);
            vBoxSubTask.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 0, 2))));
            //vBoxSubTask.setPadding(new Insets(50));
            vBoxSubTask.setPadding(new Insets(10,0,0,10));
            vBoxSubTask.setMargin(vBoxSubTask, new Insets(0, 0, 0, 50));

            for (TreuloTask child : task.getSubtasks()) {
                if(model.getDisplayArchive() || !child.isArchived()) {
                    Node taskDisplay = getTaskDisplay(child, vBox);
                    vBoxSubTask.getChildren().add(taskDisplay);
                    tasks.add((VBox)taskDisplay);
                }
            }

            vBox.getChildren().addAll(vBoxSubTask);
        }
        else
        {
            if(task.getSubtasks().size() > 0) {
                Button deploy = new Button("▼");
                deploy.setOnAction(new DeployTaskControl(model,task));
                name.getChildren().add(deploy);
            }
        }


        return vBox;
    }
}
