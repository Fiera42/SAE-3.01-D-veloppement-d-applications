package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class EditTaskDetailControl implements EventHandler<ActionEvent> {
    Treulo model ;
    
    TreuloTask treutask ;
    TaskList tL;
    TextField nom ;
    TextField description;
    TextField Duree;

    ComboBox<TreuloTask> combo;
    public EditTaskDetailControl(Treulo model) {
        this.model = model ;
    }

    public EditTaskDetailControl(Treulo model, TaskList editedTaskList, TextField name, TextField des, ComboBox<TreuloTask> combo , TreuloTask tt , TextField duree) {
        this.model = model ;
        this.tL = editedTaskList;
        this.nom = name ;
        this.description = des;
        this.combo = combo ;
        this.treutask = tt ;
        this.Duree = duree ;
        
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        
        this.treutask.setName(this.nom.getText());
        this.treutask.setDescription(this.description.getText());
        if(!this.Duree.getText().isEmpty()) {
            this.treutask.setDuration(Float.parseFloat(this.Duree.getText()));
        }
        
        for(int i=0;i<model.getCollaboratorTempo().size();i++)
        {
            treutask.addCollaborator(model.getCollaboratorTempo().get(i));
        }
        for(int i=0;i<model.getDependencieTempo().size();i++)
        {
            treutask.addDependencie(model.getDependencieTempo().get(i));
        }

        if (combo.getSelectionModel().getSelectedItem()!=null)
        {
            combo.getSelectionModel().getSelectedItem().addSubTask(treutask);
        }

        
        DetailTaskControl.setBoolean(true);
        this.model.getDependencieTempo().clear();
        this.model.getCollaboratorTempo().clear();
        this.model.setDisplayMode(model.getDisplayModeOld());
    }
}
