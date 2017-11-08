package com.defaulty.autopark.model.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_out")
    @Temporal(TemporalType.TIME)
    private Date time_out;

    @Column(name = "time_in")
    @Temporal(TemporalType.TIME)
    private Date time_in;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto auto_id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route_id;

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
