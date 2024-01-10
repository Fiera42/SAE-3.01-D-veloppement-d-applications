package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.Controler.task.DetailTaskControl;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

//Controlleur chargé de mettre à jour le mode d'affichage
//Handler d'événement (Bouton d'affichage, onAction)
//Créée par : Doryann
public class ChangeDisplayControl implements EventHandler<ActionEvent> {
    Treulo model;
    String nameButton ;

    public ChangeDisplayControl (Model m , String nb)
    {
        model= (Treulo)m;
        this.nameButton = nb ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.model.setDisplayMode(nameButton);
        //Pour éviter un vilain bug
        DetailTaskControl.setBoolean(true);
        //on vide les variables temporaires au cas ou
        this.model.getCollaboratorTempo().clear();
        this.model.getDependencieTempo().clear();
    }
}
