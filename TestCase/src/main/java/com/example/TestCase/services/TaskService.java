package com.example.TestCase.services;

import com.example.TestCase.entyties.Task;
import com.example.TestCase.entyties.enums.TaskStatus;
import com.example.TestCase.entyties.exceptions.TaskIsNotExistException;
import com.example.TestCase.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
  public Task createNewTask(Task newTask){
      return taskRepository.save(newTask);
  }
    public List<Task> getAllTasks(Long userId){
        return taskRepository.findAllTasksByUser(userId);
    }
    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }
    public void updateTask(Task taskForChange){
      taskRepository.updateTask(taskForChange);
    }
    public Task getTask(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(TaskIsNotExistException::new);
    }
    public void setTaskStatus(Long taskId, TaskStatus status){
        taskRepository.setStatus(taskId,status);
    }
    public void setExecutor(Long taskId, Long userId){
        taskRepository.setExecutor(taskId,userId);
    }


}
