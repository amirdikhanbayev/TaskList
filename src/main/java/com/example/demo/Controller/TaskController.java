package com.example.demo.Controller;

import com.example.demo.Repository.TaskRepository;
import com.example.demo.model.Task;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public Task create(@RequestBody Task task) {
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

    @DeleteMapping("/deleteTask")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
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

}

