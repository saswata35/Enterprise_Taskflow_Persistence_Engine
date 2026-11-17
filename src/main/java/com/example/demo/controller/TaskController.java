package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.TaskAuditLog;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.TaskAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskAuditRepository auditRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/audit")
    public List<TaskAuditLog> getAuditLogs() {
        return auditRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        auditRepository.save(new TaskAuditLog("INSERT", "Created task: '" + savedTask.getTitle() + "'"));
        return savedTask;
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setCompleted(taskDetails.isCompleted());
            Task updatedTask = taskRepository.save(task);
            
            String status = updatedTask.isCompleted() ? "Completed" : "Pending";
            auditRepository.save(new TaskAuditLog("UPDATE", "Updated task ID " + id + " to [" + status + "]"));
            return updatedTask;
        }
        throw new RuntimeException("Task not found with id " + id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(t -> {
            taskRepository.deleteById(id);
            auditRepository.save(new TaskAuditLog("DELETE", "Deleted task: '" + t.getTitle() + "'"));
        });
    }
}