package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

//Controlleur chargé de mettre à jour la tâche parente dans l'écran de tâche en détail
//Handler d'événement (bouton de validation, onAction)
//Créée par : Doryann
public class SetParentTaskControl implements EventHandler<ActionEvent> {

    Treulo model;
    ComboBox cb ;
    TreuloTask tt ;

    public SetParentTaskControl(Treulo model, ComboBox<TreuloTask> combo2 , TreuloTask t) {
        this.model = model ;
        this.cb = combo2 ;
        this.tt = t ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TreuloTask parentTask = this.tt.getParentTask();
        if(parentTask != null) parentTask.deleteSubTask(tt);
        this.tt.setParentTask((TreuloTask) this.cb.getSelectionModel().getSelectedItem());
        ((TreuloTask) this.cb.getSelectionModel().getSelectedItem()).addSubTask(tt);
        this.model.updateObservator();
    }
}
