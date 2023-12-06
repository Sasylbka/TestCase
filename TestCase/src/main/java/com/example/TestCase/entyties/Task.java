package com.example.TestCase.entyties;

import com.example.TestCase.entyties.enums.TaskPriority;
import com.example.TestCase.entyties.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @JoinColumn(name = "task_status_id")
    private TaskStatus status;
    @JoinColumn(name = "task_priority_id")
    private TaskPriority priority;
    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;
    @OneToOne
    @JoinColumn(name = "executor_id")
    private User executor;
}
