package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import treulo.src.Controler.DeployTaskControl;
import treulo.src.Controler.task.DetailTaskControl;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;
import java.util.Random;

public class GanttDisplay implements Display{

    Treulo model;

    LinkedList<TaskList> taskList;
    public GanttDisplay (Treulo t ,  LinkedList<TaskList> list)
    {
        model =t;
        taskList=list;

    }

    @Override
    public Node getDisplay() {
        GridPane gridPane = new GridPane();

        VBox vBoxTasks = new VBox();

        vBoxTasks.setBorder(Border.stroke(Color.BLACK));
        vBoxTasks.setPadding(new Insets(50));
        vBoxTasks.setBackground(Background.fill(Color.WHITE));
        VBox hBoxRectangle= new VBox();
        gridPane.add(vBoxTasks,1,1,1,50);

        for (int i=0;i<TreuloTask.getAlltasks().size();i++){
                    gridPane.add(getTaskDisplay(TreuloTask.getAlltasks().get(i),new HBox()),1,i+1);
                    if (TreuloTask.getAlltasks().get(i).isIndependent())
                    {
                        gridPane.add(getRectangleDisplay(TreuloTask.getAlltasks().get(i)),2,1+i);
                    }
                else
                {
                    for (int y=0;y<TreuloTask.getAlltasks().size();y++)
                    {
                     if (TreuloTask.getAlltasks().get(y).getDependencies().contains(TreuloTask.getAlltasks().get(i)))
                        {
                            gridPane.add(getRectangleDisplay(TreuloTask.getAlltasks().get(i),TreuloTask.getAlltasks().get(y).getDuration()*10),2,1+i);
                        }
                    }

                }
            }


        return gridPane;

    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {

        HBox  hb =(HBox) parentNode;
        Button bDetail = new Button(task.getName());
        bDetail.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5), new Insets(0))));
        bDetail.setPadding(new Insets(10));
        bDetail.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
        bDetail.setOnMouseClicked(new DetailTaskControl(model,task));

        hb.getChildren().addAll(bDetail);
        return hb;
    }

    public Node getRectangleDisplay(TreuloTask task) {


        Pane SP =new Pane();
        Rectangle r = new Rectangle(task.getDuration()*10,20,Color.WHEAT);

        Line l = new Line(r.getX()+r.getWidth(),r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+ r.getHeight()+20*task.getDependencies().size());
        l.setFill(Color.BLACK);

        Label lab = new Label(task.getName(),r);
        lab.setOnMouseClicked(new DetailTaskControl(model,task));

        SP.getChildren().addAll(r,lab,l);

        return SP;
    }
    public Node getRectangleDisplay(TreuloTask task ,double x) {


        Pane SP =new Pane();
        Rectangle r = new Rectangle(task.getDuration()*10,20,Color.WHITESMOKE);
        r.setX(x);

        Line l = new Line(r.getX()+r.getWidth(),r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+r.getHeight()+20*task.getDependencies().size());
        l.setFill(Color.BLACK);


        Label lab = new Label(task.getName(),r);
        lab.setLayoutX(r.getX());
        lab.setLayoutY(r.getY());

        lab.setOnMouseClicked(new DetailTaskControl(model,task));
        SP.getChildren().addAll(r,lab,l);

        return SP;
    }
}
