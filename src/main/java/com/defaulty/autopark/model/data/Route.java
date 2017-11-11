package com.defaulty.autopark.model.data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "route_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Route> route_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Route> getRoutes() {
        return route_id;
    }

    public void setRoutes(Set<Route> routes) {
        this.route_id = routes;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }

}
