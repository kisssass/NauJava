package ru.kissass.NauJava.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String color;

    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks;

    public Long getId() {
        return tagId;
    }

    public void setId(Long id) {
        this.tagId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color= color;
    }

    public Set<Task> getTasks(){
        return tasks;
    }

    public void setTasks(Set<Task> tasks){
        this.tasks = tasks;
    }
}
