package main.service;

import main.model.Task;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Storage {
    private static final List<Task> taskList = new CopyOnWriteArrayList<>();

    public static List<Task> getTaskList() {
        return taskList;
    }

    public static Task getTask(Integer id) throws WrongIdException {
        for(Task t : taskList) {
            if (t.getId() == id){
                return t;
            }
        } throw new WrongIdException();
    }

    public static Integer addTask(Task task) {
        taskList.add(task);
        return task.getId();
    }

    public static void deleteTask(Integer id) throws WrongIdException {
        for(Task t : taskList) {
            if (t.getId() == id){
                taskList.remove(t);
                return;
            }
        } throw new WrongIdException();
    }

    public static void correctTask(Integer id, Task task) throws WrongIdException {
        for(Task t : taskList) {
            if (t.getId() == id){
                changeTask(t,task);
                return;
            }
        } throw new WrongIdException();
    }

    public static void deleteList() {
        taskList.clear();
    }

    private static void changeTask(Task initial, Task replacement) {
        initial.setTaskDescription(replacement.getTaskDescription());
        initial.setTaskName(replacement.getTaskName());
        //initial.setTimeDate(replacement.getTimeDate());
    }
}

