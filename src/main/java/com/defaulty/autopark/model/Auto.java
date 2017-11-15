package com.defaulty.autopark.model;

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

    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private AutoPersonnel autoPersonnel;

    @Transient
    private String autoPersonnelStr;

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

    public AutoPersonnel getAutoPersonnel() {
        return autoPersonnel;
    }

    public void setAutoPersonnel(AutoPersonnel autoPersonnel) {
        this.autoPersonnel = autoPersonnel;
    }

    public String getAutoPersonnelStr() {
        return autoPersonnelStr;
    }

    public void setAutoPersonnelStr(String autoPersonnelStr) {
        this.autoPersonnelStr = autoPersonnelStr;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + num + " " + color + " " + mark;
    }

}
