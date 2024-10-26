package com.kinok0.fileexportservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "html_name", nullable = false)
    private String htmlName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private UserEntity employee;

    @Column(name="create_date", nullable = false)
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "statement", nullable = false)
    private TaskStatement statement;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    public TaskEntity(String name, String description, Integer priority,
                      UserEntity employee, LocalDateTime createDate,
                      TaskStatement statement) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.employee = employee;
        this.createDate = createDate;
        this.statement = statement;
    }
}
