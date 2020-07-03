package com.springtraining.hibernate.task;

import com.springtraining.hibernate.tasklist.TaskList;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @NotNull
    @Column(name="ID")
    @GeneratedValue
    private int id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED")
    @NotNull
    @EqualsAndHashCode.Exclude
    private Date created;

    @Column(name = "DURATION")
    private int duration;

    @JoinColumn(name = "FINANCIAL_DETAILS_ID")
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Setter
    private TaskFinancialDetails taskFinancialDetails;

    @JoinColumn(name = "TASKLIST_ID")
    @ManyToOne
    @Setter(AccessLevel.PUBLIC)
    private TaskList taskList;

    public Task(String description, int duration) {
        this.description = description;
        this.created = new Date();
        this.duration = duration;
    }
}
