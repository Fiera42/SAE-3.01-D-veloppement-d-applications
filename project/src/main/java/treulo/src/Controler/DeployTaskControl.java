package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

//Controlleur chargé de mettre à jour le déploiement des tâches dans la vue liste
//Handler d'événement (bouton de pliage/dépliage des tâches, onAction)
//Créée par : Tom
public class DeployTaskControl implements EventHandler<ActionEvent> {

    Treulo model;
    TreuloTask treuloTask;

    Button state;


    public DeployTaskControl(Model m,TreuloTask t)
    {
    this.model=(Treulo) m;
    this.treuloTask=t;
    }

    public void handle(ActionEvent event) {
        if (!treuloTask.getDeploy())
        {
            treuloTask.setDeploy(true);
        }
        else
        {
            treuloTask.setDeploy(false);
        }
    }
}
