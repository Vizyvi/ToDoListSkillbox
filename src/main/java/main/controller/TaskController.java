package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import main.model.Task;
import main.model.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getTaskList() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping
    public int addTask(@RequestBody Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @DeleteMapping
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @GetMapping("/{id}")
    public Task getTaskId(@PathVariable("id") int id) {
            Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.isPresent() ? optionalTask.get() : null;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
        } else {
            System.out.println("Задача под таким номером не была найдена");
        }
    }

    @PutMapping("/{id}")
    public void correctTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task.setId(optionalTask.get().getId());
            taskRepository.save(task);
        } else {
            System.out.println("Задача под таким номером не была найдена");
        }
    }
}

