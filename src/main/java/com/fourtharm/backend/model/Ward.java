package com.fourtharm.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "wards")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "constituency_id", nullable = false)
    private Constituency constituency;

    @Column(nullable = false)
    private boolean pinned = false;

    @Column
    private Integer pinOrder;

    public Ward() {
    }

    public Ward(
            String name,
            Constituency constituency
    ) {
        this.name = name;
        this.constituency = constituency;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public boolean isPinned() {
        return pinned;
    }

    public Integer getPinOrder() {
        return pinOrder;
    }

    @JsonProperty("constituencyId")
    public Long getConstituencyId() {

        return constituency != null
                ? constituency.getId()
                : null;
    }

    @JsonProperty("constituencyName")
    public String getConstituencyName() {

        return constituency != null
                ? constituency.getName()
                : null;
    }

    @JsonProperty("countyId")
    public Long getCountyId() {

        return constituency != null &&
                constituency.getCounty() != null
                ? constituency.getCounty().getId()
                : null;
    }

    @JsonProperty("countyName")
    public String getCountyName() {

        return constituency != null &&
                constituency.getCounty() != null
                ? constituency.getCounty().getName()
                : null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConstituency(
            Constituency constituency
    ) {
        this.constituency = constituency;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public void setPinOrder(Integer pinOrder) {
        this.pinOrder = pinOrder;
    }
}