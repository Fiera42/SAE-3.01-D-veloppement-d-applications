package treulo.src.model;

import treulo.src.view.Observator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class TreuloTask implements Model{
    private String name , description;
    private boolean isArchive;
    private ArrayList<String> collaborators ;
    private ArrayList<TreuloTask> dependencies ;
    private ArrayList<TreuloTask> subtasks ;

    private ArrayList<Observator> observators ;
    private static List<TreuloTask> alltasks ;


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
        return this.name;
    }

    public String getDescritpion() {
        return this.description;
    }

    public boolean isArchive() {
        return this.isArchive;
    }
}
