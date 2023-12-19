package treulo.src.Controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

public class EditTaskListControl implements EventHandler<ActionEvent>, ChangeListener<Boolean> {

    Treulo model;
    TaskList tL;
    TextField tF;

    public EditTaskListControl(Model m,TaskList tl , TextField tf)
    {
        model=(Treulo) m;
        tL=tl;
        tF = tf ;
    }

    @Override
    public void handle(ActionEvent event) {
        this.tL.setName(tF.getText());

    }
    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        if (!t1){
            this.tL.setName(tF.getText());
        }
    }
}
