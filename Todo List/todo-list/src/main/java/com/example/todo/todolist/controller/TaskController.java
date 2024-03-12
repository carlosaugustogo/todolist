package com.example.todo.todolist.controller;

import com.example.todo.todolist.dto.TaskDto;
import com.example.todo.todolist.entity.Task;
import com.example.todo.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {
        taskService.addTask(taskDto.getId(), taskDto.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa adicionada com sucesso");
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<String> completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
        return ResponseEntity.ok("Tarefa completada com sucesso");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Tarefa deletada com sucesso");
    }

    @PostMapping("/undo")
    public ResponseEntity<String> undo() {
        taskService.undo();
        return ResponseEntity.ok("Desfeita com sucessor");
    }

    @PostMapping("/redo")
    public ResponseEntity<String> redo() {
        taskService.redo();
        return ResponseEntity.ok("Refeita com sucesso");
    }
}
