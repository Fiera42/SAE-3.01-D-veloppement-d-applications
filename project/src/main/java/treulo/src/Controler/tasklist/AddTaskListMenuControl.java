package treulo.src.Controler.tasklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

//Controlleur chargé de mettre à jour l'affichage pour montré le menu d'ajout de liste
//Handler d'événement (bouton de création de liste, onAction)
//Créée par : Adrien
public class AddTaskListMenuControl implements EventHandler<ActionEvent> {

    Treulo model;

    public AddTaskListMenuControl(Model m)
    {
        model= (Treulo) m;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        this.model.setDisplayMode("Nouvelle Liste");
    }
}
