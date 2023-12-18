package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TaskList implements Model, Observator, Iterable<TreuloTask> {

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
        task.addObservator(this);
        this.updateObservator();
    }

    public void deleteTask (TreuloTask task){
        this.tasks.remove(task);
        task.deleteObservator(this);
        this.updateObservator();
    }

    public boolean isEmpty(){
        return tasks.isEmpty();
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
    public void update(Model model) {
        updateObservator();
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

    public void setName(String name) {
        this.name = name;
        this.updateObservator();
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
        this.updateObservator();
    }
}
