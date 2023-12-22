package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

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
