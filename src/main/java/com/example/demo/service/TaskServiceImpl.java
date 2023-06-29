package com.example.demo.service;

import com.example.demo.Repository.TaskRepository;
import com.example.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Override
    public List<Task> getCurrentUserTasks() {
        return taskRepository.findByUserId(userService.getCurrentUser().getId())
//                        .orElseThrow(()-> new EntityNotFoundException("Not Found"))
                        ;
    }
}
