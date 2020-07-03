package com.springtraining.hibernate.tasklist;

import com.springtraining.hibernate.task.Task;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "TASKLIST")
public class TaskList {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID")
    private int id;

    @NotNull
    @Column(name="LISTNAME")
    private String listName;

    @NotNull
    @Column(name="DESCRIPTION")
    private String description;

    @OneToMany(
            targetEntity = Task.class,
            mappedBy = "taskList",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PUBLIC)
    private List<Task> tasks = new ArrayList<>();

    public TaskList(String listName, String description) {
        this.listName = listName;
        this.description = description;
    }
}
