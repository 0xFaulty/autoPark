package com.defaulty.autopark.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auto_personnel")
public class AutoPersonnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "father_name")
    private String father_name;

    @OneToMany(mappedBy = "autoPersonnel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Auto> autos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public Set<Auto> getAutos() {
        return autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + first_name + " " + last_name + " " + father_name;
    }

}
