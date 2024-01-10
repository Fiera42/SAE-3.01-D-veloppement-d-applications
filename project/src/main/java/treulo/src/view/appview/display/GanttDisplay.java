package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import treulo.src.Controler.DeployTaskControl;
import treulo.src.Controler.ToggleArchiveControl;
import treulo.src.Controler.task.ArchiveTaskControl;
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
        int y=0;
        for (int i=0;i<TreuloTask.getAlltasks().size();i++){


                    if (TreuloTask.getAlltasks().get(i).isIndependent())
                    {
                        System.out.println("posy"+y);
                        y = displayDependence(gridPane,TreuloTask.getAlltasks().get(i),0,i+y);
                        System.out.println("posy"+y);
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
        CheckBox CB = new CheckBox();
        CB.setOnAction(new ArchiveTaskControl(model,task));

        hb.getChildren().addAll(bDetail,CB);
        return hb;
    }

    public Node getRectangleDisplay(TreuloTask task ,double x,String type) {


        Pane SP =new Pane();
        Rectangle r = new Rectangle(task.getDuration()*10,20,Color.WHITESMOKE);
        r.setX(x);

        Label lab = new Label(task.getName(),r);
        lab.setLayoutX(r.getX());
        lab.setLayoutY(r.getY());
        lab.setOnMouseClicked(new DetailTaskControl(model,task));

        Line larriere;
        if (type.equals("normal"))
        {
             larriere = new Line(r.getX(), r.getY(), r.getX(), r.getY() + r.getHeight() + 20);
            larriere.setFill(Color.BLACK);
        }
        else
        {
             larriere = new Line(r.getX(), r.getY(), r.getX(), r.getY() + r.getHeight());
            larriere.setFill(Color.BLACK);
        }

        if (task.getDependencies().size()!=0) {
            Line l = new Line(r.getX() + r.getWidth(), r.getY() + r.getHeight(), r.getX() + r.getWidth(), r.getY() + r.getHeight() + 20);
            l.setFill(Color.BLACK);
            SP.getChildren().addAll(r,lab,larriere,l);
        }
        else
        {
            SP.getChildren().addAll(r,lab,larriere);
        }
        return SP;
    }
    public int displayDependence (Node parentNode,TreuloTask task,double posx,int posy)
    {
        GridPane grid =(GridPane) parentNode;
        System.out.println(task.getDependencies().size());
        if (task.getDependencies().size()>0){
            grid.add(getTaskDisplay(task,new HBox()),1,posy+1);
            grid.add(getRectangleDisplay(task,posx,"fin"),2,posy+1);
        int i=0;
        while (i<task.getDependencies().size())
        {
            System.out.println("posy avant "+posy);
            posy=posy+displayDependence(grid,task.getDependencies().get(i),posx+task.getDuration()*10,posy+1+i)+1+i;
            System.out.println("posy après"+posy);
            i++;
        }
        return posy;
        }
        else
        {
            grid.add(getTaskDisplay(task,new HBox()),1,posy+1);
            grid.add(getRectangleDisplay(task,posx,"fin"),2,posy+1);
            return posy+1;
        }
    }
}
