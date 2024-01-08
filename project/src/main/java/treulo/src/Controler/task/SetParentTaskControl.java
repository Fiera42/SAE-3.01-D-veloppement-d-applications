package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

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
        this.tt.getParentTask().getSubtasks().remove(this.tt);
        this.tt.setParentTask((TreuloTask) this.cb.getSelectionModel().getSelectedItem());
        ((TreuloTask) this.cb.getSelectionModel().getSelectedItem()).getSubtasks().add(this.tt);
        this.model.updateObservator();
    }
}
