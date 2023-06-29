package com.example.demo.Controller;

import com.example.demo.Repository.TaskRepository;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Task create(@RequestBody Task task) {
        task.setUserId(userService.getCurrentUser().getId());
        return taskRepository.save(task);
    }

    @GetMapping("/all")
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/take/{id}")
    public Task takeId(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    ;

    @PutMapping("/change")
    public Task change(@RequestBody Task task) throws EntityNotFoundException {
        Task old = taskRepository.findById(task.getId()).orElse(new Task());
        old.setId(task.getId());
        Optional.ofNullable(task.getDate()).ifPresent(old::setDate);
        Optional.ofNullable(task.getDescription()).ifPresent(old::setDescription);
        Optional.ofNullable(task.isDone()).ifPresent(old::setDone);
        return taskRepository.save(old);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id) {
        taskRepository.MakeAsDone(id);
    }

    @PatchMapping("/task/{id}")
    public void patchMethod(@PathVariable Long id, @RequestBody Task task) {
        if (task.isDone()) {
            taskRepository.MakeAsDone(id);
        }
    }

    @PatchMapping("/tasks/{id}:mark-as-false")
    public void patchMethod2(@PathVariable Long id) {
        taskRepository.MakeAsFalse(id);
    }

    @GetMapping("/my")
    public List<Task> getMyTasks(){
        return taskService.getCurrentUserTasks();
    }

}

