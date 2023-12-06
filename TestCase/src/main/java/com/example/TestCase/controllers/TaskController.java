package com.example.TestCase.controllers;

import com.example.TestCase.entyties.Task;
import com.example.TestCase.entyties.enums.TaskStatus;
import com.example.TestCase.entyties.exceptions.TaskIsNotExistException;
import com.example.TestCase.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @GetMapping("/all_task")
    public List<Task> getTasksOfUser(Long userId){
        return taskService.getAllTasks(userId);
    }
    @PostMapping("/create_task")
    public Task createTask(@RequestBody Task newTask){
        return taskService.createNewTask(newTask);
    }
    @DeleteMapping("/delete_task")
    public String deleteTask(Long taskId){
        taskService.deleteTask(taskId);
        return String.format("Задача с номером %s удалена.",taskId);
    }
    @PatchMapping("/change_task")
    public String changeTask(Task taskForChange){
        taskService.updateTask(taskForChange);
        return String.format("Задача с номером %s изменена.",taskForChange.getId());
    }
    @GetMapping("/view_task")
    public Task viewTask(Long userId){
            return taskService.getTask(userId);
    }
    @PatchMapping("/change_status_of_task")
    public String changeStatusOfTask(Long taskId,TaskStatus status){
        taskService.setTaskStatus(taskId,status);
        return String.format("Статус задачи %s изменена на %s.",taskId,status);
    }
    @PatchMapping("/setExecutor")
    public String setExecutor(Long taskId,Long userId){
        taskService.setExecutor(taskId,userId);
        return String.format("Назначен исполнитель задаче %s.",taskId);
    }

}
