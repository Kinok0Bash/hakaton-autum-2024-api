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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id", nullable = false)
    private UserEntity employee;

    @Column(name="create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "statement", nullable = false)
    private TaskStatement statementl;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    public TaskEntity(String name, String description,
                      UserEntity employee, LocalDateTime createDate,
                      TaskStatement statementl) {
        this.name = name;
        this.description = description;
        this.employee = employee;
        this.createDate = createDate;
        this.statementl = statementl;
    }
}
