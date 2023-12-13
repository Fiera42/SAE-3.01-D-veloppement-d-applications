package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;

public class TreuloTask implements Model{
    private String name , descritpion;
    private boolean isArchive;
    private ArrayList<String> collaborators ;
    private ArrayList<TreuloTask> dependencies ;
    private ArrayList<TreuloTask> subtasks ;

    public TreuloTask (){

    }

    public TreuloTask (String n){

    }

    public TreuloTask (String s , String n){

    }

    public void addDependencie(TreuloTask task){

    }

    public void deleteDependencie(TreuloTask task){

    }

    public ArrayList<TreuloTask> getDependencies(){
        return this.subtasks ;
    }

    public void addSubTask(TreuloTask task){

    }

    public void deleteSubTask(TreuloTask task){

    }

    public ArrayList<TreuloTask> getSubtasks(){
        return this.subtasks ;
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

    public String getName() {
        return name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public boolean isArchive() {
        return isArchive;
    }
}
