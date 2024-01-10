package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import treulo.src.Controler.task.ArchiveTaskControl;
import treulo.src.Controler.task.DetailTaskControl;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;

/**
*Cette classe permet d'afficher le GANTT
 */
public class GanttDisplay implements Display{

    Treulo model;

    LinkedList<TaskList> taskList;

    /**
     * Constructeur du display permetant d'avoir le modèle et la liste des taches
     * @param t Le modéle
     * @param list La liste de toute les taches
     */
    public GanttDisplay (Treulo t ,  LinkedList<TaskList> list)
    {
        model =t;
        taskList=list;

    }

    /**
     * Permet de générer l'affichage du GANTT
     * @return  L'affichage qui sera récupérer
     */
    @Override
    public Node getDisplay() {
        GridPane gridPane = new GridPane();

        VBox vBoxTasks = new VBox();

        vBoxTasks.setBorder(Border.stroke(Color.BLACK));
        vBoxTasks.setPadding(new Insets(50));
        vBoxTasks.setBackground(Background.fill(Color.WHITE));
        gridPane.add(vBoxTasks,1,1,1,Integer.MAX_VALUE);
        int y=0;
        for (int i=0;i<TreuloTask.getAlltasks().size();i++){


                    if (TreuloTask.getAlltasks().get(i).isIndependent())
                    {
                        y = displayDependence(gridPane,TreuloTask.getAlltasks().get(i),0,i+y);
                    }
            }


        return gridPane;

    }

    /**
     * Permet de créer l'affichage des taches sous forme de boutton
     * @param task Tache donné afin de créer son affichage
     * @param parentNode La Node du parent
     * @return l'affichage créer
     */
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

    /**
     * Permet de créer l'affichage des rectangles
     * @param task  la tache êrmetant de définir la taille du rectangle
     * @param x la position sur l'axe x
     * @return l'affichage créer
     */
    public Node getRectangleDisplay(TreuloTask task ,double x) {


        Pane SP =new Pane();
        Rectangle r = new Rectangle(task.getDuration()*10,20,Color.WHITESMOKE);
        r.setX(x);

        Label lab = new Label(task.getName()+" Dure ' "+task.getDuration()+" ' heures",r);
        lab.setLayoutX(r.getX());
        lab.setLayoutY(r.getY());
        lab.setOnMouseClicked(new DetailTaskControl(model,task));

        Line larriere = new Line(r.getX(), r.getY(), r.getX(), r.getY() + r.getHeight());
            larriere.setFill(Color.BLACK);


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

    /**
     * Permet d'afficher toute les tache dépendant d'une tache de façcon récursife
     * @param parentNode La Node du parent a qui on va donner l'affichage
     * @param task la tache que l'on observe
     * @param posx position x de la tache
     * @param posy position y de la tache
     * @return le nombre de tache afficher
     */
    public int displayDependence (Node parentNode,TreuloTask task,double posx,int posy)
    {
        GridPane grid =(GridPane) parentNode;
        if (task.getDependencies().size()>0){
            grid.add(getTaskDisplay(task,new HBox()),1,posy+1);
            grid.add(getRectangleDisplay(task,posx),2,posy+1);
        int i=0;
        while (i<task.getDependencies().size())
        {
            posy=posy+displayDependence(grid,task.getDependencies().get(i),posx+task.getDuration()*10,posy+1+i)+1+i;
            i++;
        }
        return posy;
        }
        else
        {
            grid.add(getTaskDisplay(task,new HBox()),1,posy+1);
            grid.add(getRectangleDisplay(task,posx),2,posy+1);
            return posy+1;
        }
    }
}
