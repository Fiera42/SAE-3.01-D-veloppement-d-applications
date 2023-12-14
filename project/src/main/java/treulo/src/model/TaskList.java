package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TaskList implements Model , Iterable<TreuloTask>{

    private String name ;
    private boolean isArchived ;
    private LinkedList<TreuloTask> tasks ;
    private ArrayList<Observator> observators ;

    public TaskList(String name) {
        this.name = name;
        this.isArchived = false;
        this.tasks = new LinkedList();
        this.observators = new ArrayList<>();
    }

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

    @Override
    public Iterator<TreuloTask> iterator() {
        return this.tasks.iterator();
    }

    public String getName() {
        return name;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public LinkedList<TreuloTask> getTasks() {
        return tasks;
    }

    public ArrayList<Observator> getObservators() {
        return observators;
    }
}
