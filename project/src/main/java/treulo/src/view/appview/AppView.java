package treulo.src.view.appview;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.view.Observator;
import treulo.src.view.appview.displayFactory.DisplayFactory;

import java.io.Serializable;

//Observateur du modèle, permet d'afficher la partie tâche de l'application
//Créée par : Adrien
public class AppView extends ScrollPane implements Observator, Serializable {

    public AppView() {
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setFitToWidth(true);
        setFitToHeight(true);
        setBackground(Background.fill(Color.TRANSPARENT));
    }

    public void update(Model model) {
        if(!(model instanceof Treulo)) return;

        Treulo treulo = (Treulo)model;

        //Récupèration du bon afficheur en fonction du mode d'afficahge du modèle
        DisplayFactory factory = DisplayFactory.getDisplayFactory(treulo, treulo.getDisplayMode());

        //Modification de la vue pour refléter les changements dans le modèle
        setContent(factory.createDisplay().getDisplay());

        // #glam
        ((Pane) getContent()).setBackground(Background.fill(Color.AQUAMARINE));
    }
}
