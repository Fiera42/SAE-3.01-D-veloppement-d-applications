package treulo.src.model;

import treulo.src.view.Observator;

//Interface de modèle pour MVC
//Créée par : Tom
public interface Model {

    public void addObservator(Observator o);
    public void deleteObservator(Observator o);
    public void updateObservator();
}
