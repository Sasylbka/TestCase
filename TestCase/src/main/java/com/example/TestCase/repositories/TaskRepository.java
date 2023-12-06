package com.example.TestCase.repositories;

import com.example.TestCase.entyties.Task;
import com.example.TestCase.entyties.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findByTitle(String title);

    @Modifying
    @Query(value = "update tasks u set " +
            "u.title = :task.title," +
            "u.description = :task.description," +
            "u.task_status_id = :task.status.id," +
            "u.task_priority_id = :task.priority.id," +
            "u.author_id = :task.author.id," +
            "u.executor_id = :task.executor.id" +
            "where u.id = :task.id;", nativeQuery = true)
    void updateTask(@Param("task") Task task);

    @Modifying
    @Query(value = "Select * from tasks u where u.user_id = :userId", nativeQuery = true)
    List<Task> findAllTasksByUser(@Param("userId") Long userId);

    @Modifying
    @Query(value = "update tasks u set u.status = :status where u.id = :taskId", nativeQuery = true)
    void setStatus(@Param("taskId") Long taskId, @Param("status")TaskStatus status);

    @Modifying
    @Query(value = "update tasks u set u.executor_id = :userId where u.id = :taskId", nativeQuery = true)
    void setExecutor(@Param("taskId") Long taskId, @Param("userId")Long userId);
}
