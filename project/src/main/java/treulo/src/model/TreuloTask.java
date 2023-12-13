package treulo.src.model;

import treulo.src.view.Observator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class TreuloTask implements Model{
    private String name , description;
    private boolean isArchive;

    private float duration ;
    private ArrayList<String> collaborators ;
    private ArrayList<TreuloTask> dependencies ;
    private ArrayList<TreuloTask> subtasks ;

    private ArrayList<Observator> observators ;
    private static List<TreuloTask> alltasks ;



    public TreuloTask (){
        this.name = "" ;
        this.description = "" ;
        this.duration = 0 ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new ArrayList<TreuloTask>() ;
        this.observators = new ArrayList<Observator>();
        alltasks.add(this) ;
    }

    public TreuloTask (String n){
        this.name = n ;
        this.description = "" ;
        this.duration = 0 ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new ArrayList<TreuloTask>() ;
        this.observators = new ArrayList<Observator>();
        alltasks = new ArrayList<TreuloTask>() ;
    }

    public TreuloTask (String d , String n){
        this.name = n ;
        this.description = d ;
        this.duration = 0 ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new ArrayList<TreuloTask>() ;
        this.observators = new ArrayList<Observator>();
        alltasks = new ArrayList<TreuloTask>() ;
    }

    public TreuloTask (String d , String n , float f){
        this.name = n ;
        this.description = d ;
        this.duration = f ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new ArrayList<TreuloTask>() ;
        this.observators = new ArrayList<Observator>();
        alltasks = new ArrayList<TreuloTask>() ;
    }


    public void addDependencie(TreuloTask task){
        this.dependencies.add(task);
    }

    public void deleteDependencie(TreuloTask task){
        this.dependencies.remove(task);
    }

    public ArrayList<TreuloTask> getDependencies(){
        return this.subtasks ;
    }

    public void addSubTask(TreuloTask task){
        this.subtasks.add(task);
    }

    public void deleteSubTask(TreuloTask task){
        this.subtasks.remove(task);
    }

    public ArrayList<TreuloTask> getSubtasks(){
        return this.subtasks ;
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

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isArchive() {
        return this.isArchive;
    }

    public List<TreuloTask> getAlltasks(){
        return alltasks;
    }
}
