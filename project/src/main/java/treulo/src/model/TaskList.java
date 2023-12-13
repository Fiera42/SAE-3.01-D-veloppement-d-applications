package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;

public class TaskList implements Model{

    private String name ;
    public boolean isArchived ;
    private ArrayList<TreuloTask> tasks ;
    private ArrayList<Observator> observators ;

    public void addTask (TreuloTask task){
        this.tasks.add(task);
    }

    public void deleteTask (TreuloTask task){
        this.tasks.remove(task);
    }

    public boolean isEmpty(){
        return this.name.isEmpty();
    }
    @Override
    public void addObservator(Observator o) {
        this.observators.add(o);
    }

    @Override
    public void deleteObservator(Observator o) {
        this.observators.remove(o);
    }

    @Override
    public void updateObservator() {
        for (Observator o: this.observators) {
            o.update(this);
        }
    }
}
