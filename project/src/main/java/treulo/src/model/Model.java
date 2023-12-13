package treulo.src.model;

import treulo.src.observator.Observator;

import java.util.ArrayList;

public interface Model {

    public void addObservator(Observator o);
    public void deleteObservator(Observator o);
    public void updateObservator();
}
