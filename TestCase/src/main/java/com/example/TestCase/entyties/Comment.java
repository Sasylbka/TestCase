package com.example.TestCase.entyties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="task_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="comment")
    String comment;
    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
