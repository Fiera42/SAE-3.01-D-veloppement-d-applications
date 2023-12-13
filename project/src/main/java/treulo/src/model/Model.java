package treulo.src.model;

import treulo.src.view.Observator;

public interface Model {

    public void addObservator(Observator o);
    public void deleteObservator(Observator o);
    public void updateObservator();
}
