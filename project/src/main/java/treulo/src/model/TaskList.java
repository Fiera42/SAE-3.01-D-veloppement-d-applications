package treulo.src.model;

import treulo.src.view.Observator;

import java.io.Serializable;
import java.security.IdentityScope;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TaskList implements Model, Observator, Iterable<TreuloTask>, Serializable {

    private String name ;
    private boolean isArchived ;
    private LinkedList<TreuloTask> tasks ;
    private ArrayList<Observator> observators ;
    private Treulo parentApp;
    private static List<TaskList> allLists = new LinkedList<TaskList>();
    private int id;
    private static int maxId;

    private boolean deploy=false;


    public TaskList(String name) {
        this.name = name;
        this.isArchived = false;
        this.tasks = new LinkedList();
        this.observators = new ArrayList<>();
        allLists.add(this);
        this.id = ++maxId;
        deploy = true;
    }

    public void addTask (TreuloTask task){
        this.tasks.add(task);
        task.addObservator(this);
        task.setParentList(this);
        this.updateObservator();
    }

    public void addTask (TreuloTask task, int index){
        this.tasks.add(index, task);
        task.addObservator(this);
        task.setParentList(this);
        this.updateObservator();
    }

    public void deleteTask (TreuloTask task){
        this.tasks.remove(task);
        task.deleteObservator(this);
        task.setParentList(null);
        this.updateObservator();
    }

    public void destroy() {
        LinkedList<TreuloTask> temp = new LinkedList<>(tasks);
        for(TreuloTask task : temp) {
            task.destroy();
        }
        parentApp.removeTaskList(this);
        allLists.remove(this);
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

    public Treulo getParentApp() {
        return parentApp;
    }

    public void setParentApp(Treulo parentApp) {
        this.parentApp = parentApp;
    }

    public static TaskList getListById(int id) {
        for(TaskList list : allLists) {
            if(list.getId() == id) return list;
        }

        return null;
    }

    public static List<TaskList> getAllLists() {
        return allLists;
    }

    public static void setAllLists(List<TaskList> allLists) {
        TaskList.allLists = allLists;
    }

    public int getId() {
        return id;
    }

    public static int getMaxId() {
        return maxId;
    }

    public void setDeploy(boolean deploy) {
        this.deploy = deploy;
        this.updateObservator();
    }

    public boolean getdeploy()
    {
        return this.deploy;
    }
}
