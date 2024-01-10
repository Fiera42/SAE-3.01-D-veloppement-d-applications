package treulo.src.view.appview.display;

import javafx.scene.Node;
import treulo.src.model.TreuloTask;

//Interface d'afficheur
//Adrien
public interface Display {
    Node getDisplay();
    //inutile dans l'interface, à été mise là pour assurer un minimum de séparation dans les méthodes
    Node getTaskDisplay(TreuloTask task, Node parentNode);
}
