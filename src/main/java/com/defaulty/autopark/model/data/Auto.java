package com.defaulty.autopark.model.data;

import javax.persistence.*;

@Entity
@Table(name = "autos")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private String num;

    @Column(name = "color")
    private String color;

    @Column(name = "mark")
    private String mark;

    @Transient
    private String personnel_str;

    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private AutoPersonnel personnel_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public AutoPersonnel getPersonnel_id() {
        return personnel_id;
    }

    public void setPersonnel_id(AutoPersonnel personnel_id) {
        this.personnel_id = personnel_id;
    }

    public String getPersonnel_str() {
        return personnel_str;
    }

    public void setPersonnel_str(String personnel_str) {
        this.personnel_str = personnel_str;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + num + " " + color + " " + mark;
    }

}
