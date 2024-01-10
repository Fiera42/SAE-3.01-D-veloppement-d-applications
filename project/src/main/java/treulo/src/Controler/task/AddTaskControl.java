package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.FloatStringConverter;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.Objects;

/**
 * Controleur Permetant de créer une nouvelle tache
 */
//Handler d'événement (bouton "ajouter tâche" dans le menu d'ajout de tâche, onAction)
//Créée par : Tom
public class AddTaskControl implements EventHandler <ActionEvent> {
        Treulo model;
        //liste parente
        TaskList tL;

        //Observe les textfield de la scène pour ajouter les informations à la tâche
    TextField nom ;
    TextField description;
    TextField duration;
    ComboBox<TreuloTask> combo;

        public AddTaskControl(Model m, TaskList tL , TextField nom , TextField description ,TextField duree, ComboBox<TreuloTask> combo)
        {
            model= (Treulo)m;

            this.tL= tL;
            this.description=description;
            this.nom=nom;
            this.combo=combo;

            this.duration= duree;
            //uniquement des float
            duration.setTextFormatter(new TextFormatter<>(new FloatStringConverter()));
        }

    @Override
    public void handle(ActionEvent event) {
            //vérification de champ vide
            if (Objects.equals(description.getText(), "") || Objects.equals(nom.getText(), "") || Objects.equals(duration.getText(),"")){}
            else {

                //création objet tâche avec information des textfield
            TreuloTask treutask = new TreuloTask(this.nom.getText(),this.description.getText(),Math.abs(Float.parseFloat(this.duration.getText())));

            //utilise la liste de collaborateur temporaire pour ajouter les collaborateurs
                for(int i=0;i<model.getCollaboratorTempo().size();i++)
                {
                    treutask.addCollaborator(model.getCollaboratorTempo().get(i));
                }
                //utilise la liste des dépendances temporaire pour ajouter les collaborateurs
                for(int i=0;i<model.getDependencieTempo().size();i++)
                {
                    treutask.addDependencie(model.getDependencieTempo().get(i));
                }
                //place la tâche en sous-tâche si cela est demandé, sinon la place par défaut dans la bonne liste
                if (combo.getSelectionModel().getSelectedItem()!=null)
                {
                    combo.getSelectionModel().getSelectedItem().addSubTask(treutask);
                }
                else
                {
                    this.tL.addTask(treutask);
                }
            }
            //nettoie les champs temporaires
            this.model.getDependencieTempo().clear();
            this.model.getCollaboratorTempo().clear();

            //retour en arrière
            this.model.setDisplayMode(model.getDisplayModeOld());
    }
}
