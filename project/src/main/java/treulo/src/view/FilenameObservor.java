package treulo.src.view;

import javafx.scene.control.Label;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

import java.io.Serializable;

//Observateur de nom de fichier pour le modèle
//Créée car élément d'affichage du programme Main, donc non accessible dans l'appview
//Créée par : Adrien
public class FilenameObservor extends Label implements Observator, Serializable {

    public FilenameObservor() {
    }

    public FilenameObservor(String text) {
        super(text);
    }

    @Override
    public void update(Model model) {
        if(!(model instanceof Treulo)) return;

        Treulo treulo = (Treulo) model;
        if(treulo.getFilename() != null && !treulo.getFilename().equals("")) setText(treulo.getFilename());
        else setText("unnamed");
    }
}
