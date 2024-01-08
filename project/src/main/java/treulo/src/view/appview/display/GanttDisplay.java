package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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
        BorderPane bP = new BorderPane();
        VBox vBoxTasks = new VBox();
        vBoxTasks.setBorder(Border.stroke(Color.BLACK));
        vBoxTasks.setPadding(new Insets(50));
        vBoxTasks.setBackground(Background.fill(Color.WHITE));
        HBox hBoxRectangle= new HBox();

        for (int i=0;i<taskList.size();i++){
        for( TreuloTask task : taskList.get(i).getTasks()) {
           if (task.getDependencies().size()>0 && task.isIndependent())
        {
            vBoxTasks.getChildren().add(getTaskDisplay(task, new VBox()));
            hBoxRectangle.getChildren().add(getRectangleDisplay(task));
           }
        }
        }


        bP.setLeft(vBoxTasks);
        bP.setCenter(hBoxRectangle);
        return bP;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {

        VBox vBox = new VBox(10);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5), new Insets(0))));
        vBox.setPadding(new Insets(10));
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
        HBox name = new HBox(10);
        vBox.getChildren().add(name);
        TextField nameText = new TextField(task.getName());
        name.getChildren().add(nameText);
        HBox.setHgrow(nameText, Priority.ALWAYS);

        return vBox;
    }

    public Node getRectangleDisplay(TreuloTask task) {


        Pane SP =new Pane();
        Rectangle r = new Rectangle(task.getDuration()*10,20,Color.color(1, Math.random(),1));
        Line l = new Line(r.getX()+r.getWidth(),r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+ r.getHeight()+20*task.getDependencies().size());
        l.setFill(Color.BLACK);

        SP.getChildren().addAll(r,l);

        System.out.println("r");
        System.out.println(r.getX());
        System.out.println(r.getY());

        System.out.println("l");
        System.out.println(l.getStartX());
        System.out.println(l.getEndX());
        System.out.println(l.getStartY());
        System.out.println(l.getEndY());
        for (int i=0;i<task.getDependencies().size();i++)
        {
            SP.getChildren().add(getRectangleDisplay(task.getDependencies().get(i),r.getX()+r.getWidth(),r.getY()+ r.getHeight()+20*i));
        }


        return SP;
    }
    public Node getRectangleDisplay(TreuloTask task ,double x, double y) {


        Pane SP =new Pane();
        Rectangle r = new Rectangle(task.getDuration()*10,20,Color.color(1, Math.random(),1));

        r.setX(x);
        r.setY(y);

        System.out.println("r");
        System.out.println(r.getX());
        System.out.println(r.getY());
        Line l = new Line(r.getX()+r.getWidth(),r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+r.getHeight()+20*task.getDependencies().size());
        l.setFill(Color.BLACK);
        System.out.println("l");
        System.out.println(l.getStartX());
        System.out.println(l.getEndX());
        System.out.println(l.getStartY());
        System.out.println(l.getEndY());

        SP.getChildren().addAll(r,l);
        for (int i=0;i<task.getDependencies().size();i++)
        {
            SP.getChildren().add(getRectangleDisplay(task.getDependencies().get(i),r.getX()+r.getWidth(),r.getY()+ r.getHeight()+20*i));
        }


        return SP;
    }
}
