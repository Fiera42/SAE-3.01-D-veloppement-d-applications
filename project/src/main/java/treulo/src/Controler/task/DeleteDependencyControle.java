package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class DeleteDependencyControle implements EventHandler<ActionEvent> {

    Treulo model;
    TreuloTask treuloTask , depence;

    public DeleteDependencyControle(Treulo m,TreuloTask l , TreuloTask task) {
        this.model = m ;
        this.depence = l ;
        this.treuloTask = task ;

    }

    public DeleteDependencyControle(Treulo m,TreuloTask l) {
        this.model = m ;
        this.depence = l ;
        this.treuloTask = null ;

    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.model.getDependencieTempo().contains(depence)) {
            this.model.getDependencieTempo().remove(depence);
        }else {
            this.treuloTask.getDependencies().remove(depence);
        }
        this.model.updateObservator();
    }
}
