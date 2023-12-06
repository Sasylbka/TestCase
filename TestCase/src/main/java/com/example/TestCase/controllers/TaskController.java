package com.example.TestCase.controllers;

import com.example.TestCase.entyties.Task;
import com.example.TestCase.entyties.enums.TaskStatus;
import com.example.TestCase.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    /*Эндпоинт для получения всех задач пользователя по id пользователя*/
    @GetMapping("/all_task")
    public List<Task> getTasksOfUser(Long userId){
        return taskService.getAllTasks(userId);
    }

    /*Эндпоинт для создания задач*/
    @PostMapping("/create_task")
    public Task createTask(@RequestBody Task newTask){
        return taskService.createNewTask(newTask);
    }

    /*Эндпоинт для удаления задачи по id*/
    @DeleteMapping("/delete_task")
    public String deleteTask(Long taskId){
        taskService.deleteTask(taskId);
        return String.format("Задача с номером %s удалена.",taskId);
    }

    /*Эндпоинт для редактирования задачи*/
    @PatchMapping("/change_task")
    public String changeTask(Task taskForChange){
        taskService.updateTask(taskForChange);
        return String.format("Задача с номером %s изменена.",taskForChange.getId());
    }

    /*Эндпоинт для просмотра задачи по её id*/
    @GetMapping("/view_task")
    public Task viewTask(Long taskId){
        return taskService.getTask(taskId);
    }

    /*Эндпоинт для просмотра задачи по её id*/
    @PatchMapping("/change_status_of_task")
    public String changeStatusOfTask(Long taskId,TaskStatus status){
        taskService.setTaskStatus(taskId,status);
        return String.format("Статус задачи %s изменена на %s.",taskId,status);
    }

    /*Эндпоинт для назначения исполнителя*/
    @PatchMapping("/setExecutor")
    public String setExecutor(Long taskId,Long userId){
        taskService.setExecutor(taskId,userId);
        return String.format("Назначен исполнитель задаче %s.",taskId);
    }

}
