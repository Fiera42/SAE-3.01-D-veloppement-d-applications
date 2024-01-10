package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;
import treulo.src.model.Model;

//Classe chargée de géré l'arrêt de l'action de drag and drop
//Handler d'événement (OnDragEnd)
//Créée par : Adrien
public class EndDragControl implements EventHandler<DragEvent> {

    Model model;
    Node task;

    public EndDragControl(Model m, Node task) {
        this.task = task;
        model=m;
    }

    //La tâche qui était drag est de nouveau visible, inutile avec MVC, mais utilisée lors du développement
    @Override
    public void handle(DragEvent event) {
        task.setVisible(true);
    }
}
