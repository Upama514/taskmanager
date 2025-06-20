package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.security.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtUtil jwtUtil;

    //  Extract user ID from JWT
    private String getUserIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.extractAllClaims(token);
            return claims.getSubject(); // username is stored as subject
        }
        return null;
    }

    //  Create Task (automatically assigns userId)
    @PostMapping
    public Task createTask(@RequestBody Task task, HttpServletRequest request) {
        String userId = getUserIdFromToken(request);
        task.setUserId(userId);
        return taskRepository.save(task);
    }

    //  Get all tasks for the logged-in user
    @GetMapping
    public List<Task> getAllTasks(HttpServletRequest request) {
        String userId = getUserIdFromToken(request);
        return taskRepository.findByUserId(userId);
    }

    //  Get a single task (optional: can add ownership check)
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskRepository.findById(id).orElse(null);
    }

    //  Update Task (optional: add userId check)
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        }
        return null;
    }

    //  Delete Task (optional: add userId check)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
    }
}
