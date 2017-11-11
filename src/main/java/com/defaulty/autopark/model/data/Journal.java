package com.defaulty.autopark.model.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_out")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_out;

    @Column(name = "time_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_in;

    @Transient
    private String time_out_str;

    @Transient
    private String time_in_str;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto auto_id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route_id;

    @Transient
    private String auto_str;

    @Transient
    private String route_str;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime_out() {
        return time_out;
    }

    public void setTime_out(Date time_out) {
        this.time_out = time_out;
    }

    public Date getTime_in() {
        return time_in;
    }

    public void setTime_in(Date time_in) {
        this.time_in = time_in;
    }

    public Auto getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(Auto auto_id) {
        this.auto_id = auto_id;
    }

    public Route getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Route route_id) {
        this.route_id = route_id;
    }

    public String getAuto_str() {
        return auto_str;
    }

    public void setAuto_str(String auto_str) {
        this.auto_str = auto_str;
    }

    public String getRoute_str() {
        return route_str;
    }

    public void setRoute_str(String route_str) {
        this.route_str = route_str;
    }

    public String getTime_out_str() {
        return time_out_str;
    }

    public void setTime_out_str(String time_out_str) {
        this.time_out_str = time_out_str;
    }

    public String getTime_in_str() {
        return time_in_str;
    }

    public void setTime_in_str(String time_in_str) {
        this.time_in_str = time_in_str;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", time_out=" + time_out +
                ", time_in=" + time_in +
                ", auto_id=" + auto_id.getId() +
                ", route_id=" + route_id +
                '}';
    }

}
