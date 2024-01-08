package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class AddTaskDependencyControl implements EventHandler<ActionEvent> {
    Treulo model;
    ComboBox <TreuloTask> dependency;

    public AddTaskDependencyControl(Model m, ComboBox <TreuloTask> t){
        model=(Treulo) m;
        dependency=t;
    }


    @Override
    public void handle(ActionEvent event) {
        if(dependency.getSelectionModel().getSelectedItem()!=null)
        {model.addDependencyTempo(dependency.getSelectionModel().getSelectedItem());}
    }
}
