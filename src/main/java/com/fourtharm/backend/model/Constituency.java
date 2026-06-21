package com.fourtharm.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "constituencies")
public class Constituency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    @Column(nullable = false)
    private boolean pinned = false;

    @Column
    private Integer pinOrder;

    public Constituency() {
    }

    public Constituency(
            String name,
            County county
    ) {
        this.name = name;
        this.county = county;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public County getCounty() {
        return county;
    }

    public boolean isPinned() {
        return pinned;
    }

    public Integer getPinOrder() {
        return pinOrder;
    }

    @JsonProperty("countyId")
    public Long getCountyId() {
        return county != null
                ? county.getId()
                : null;
    }

    @JsonProperty("countyName")
    public String getCountyName() {
        return county != null
                ? county.getName()
                : null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public void setPinOrder(Integer pinOrder) {
        this.pinOrder = pinOrder;
    }
}