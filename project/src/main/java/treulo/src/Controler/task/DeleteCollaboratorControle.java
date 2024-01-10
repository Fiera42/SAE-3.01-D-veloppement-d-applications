package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

//Controlleur chargé de retiré un collaborateur assigné à une tâche
//Handler d'événement (bouton de collaborateur, onAction)
//Créée par : Doryann
public class DeleteCollaboratorControle implements EventHandler<ActionEvent> {

    Treulo model;
    TreuloTask treuloTask ;
    String collabo;

    public DeleteCollaboratorControle(Treulo m, String s , TreuloTask task) {
        this.model = m ;
        this.collabo = s ;
        this.treuloTask = task ;

    }

    public DeleteCollaboratorControle(Treulo m, String s) {
        this.model = m ;
        this.collabo = s ;
        this.treuloTask = null ;

    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.model.getCollaboratorTempo().contains(collabo)) {
            this.model.getCollaboratorTempo().remove(collabo);
        }else {
            this.treuloTask.getCollaborators().remove(collabo);
        }
        this.model.updateObservator();
    }
}
