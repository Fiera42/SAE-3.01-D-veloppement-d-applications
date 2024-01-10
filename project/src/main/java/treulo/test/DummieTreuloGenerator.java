package treulo.test;

import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class DummieTreuloGenerator {
    public static Treulo generateDummie() {
        Treulo treulo = new Treulo();
        TreuloTask task1 = new TreuloTask("tache 1", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",10);
        TreuloTask task2 = new TreuloTask("liste avec un très grand nom sur plusieurs lignes", "Lorem Ipsum Lorem Ipsum Lpsum Lorem Ipsum ",20);
        TreuloTask task3 = new TreuloTask("tache 3", "",30);
        TreuloTask task4 = new TreuloTask("tache 4", "Lorem Ipsum Lorem Ipsum Lorsum Lorem Ipsum Lorem Ipsum ",40);
        TreuloTask task5 = new TreuloTask("tache 5", "Lorem Ipsorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum ",10);
        TreuloTask task6 = new TreuloTask("tache 6", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",20);
        TreuloTask task7 = new TreuloTask("tache 7", "ye",15);

        task1.addDependencie(task2);
        task1.addDependencie(task3);
        task3.addDependencie(task4);

        task2.addSubTask(task5);
        task3.addSubTask(task6);
        task5.addSubTask(task7);

        TaskList list1 = new TaskList("liste 1");
        TaskList list2 = new TaskList("liste avec un très grand nom sur plusieurs lignes");

        list1.addTask(task1);
        list1.addTask(task2);
        list2.addTask(task3);
        list2.addTask(task4);

        treulo.addTaskList(list1);
        treulo.addTaskList(list2);

        treulo.setFilename("test_filename.txt");
        treulo.setPath("test_filename.txt");

        return treulo;
    }
}
