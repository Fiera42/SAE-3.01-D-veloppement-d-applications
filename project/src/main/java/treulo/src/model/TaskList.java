package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;

public class TaskList implements Model{

    private String name ;
    public boolean isArchived ;
    private ArrayList<TreuloTask> tasks ;
    private ArrayList<Observator> observators ;

    public void addTask (TreuloTask task){}

    public void deleteTask (TreuloTask task){}

    public boolean isEmpty(){
        return true ;
    }
    @Override
    public void addObservator(Observator o) {

    }

    @Override
    public void deleteObservator(Observator o) {

    }

    @Override
    public void updateObservator() {

    }
}
