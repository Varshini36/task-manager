package com.example.taskmanger;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return repo.findAll();
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return repo.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = repo.findById(id).orElseThrow();
        task.setName(updatedTask.getName());
        task.setCompleted(updatedTask.isCompleted());
        return repo.save(task);
    }
}