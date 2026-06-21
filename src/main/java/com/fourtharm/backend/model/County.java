package com.fourtharm.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "counties")
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean pinned = false;

    @Column
    private Integer pinOrder;

    public County() {
    }

    public County(String name) {
        this.name = name;
    }

    public County(
            String name,
            boolean pinned,
            Integer pinOrder
    ) {
        this.name = name;
        this.pinned = pinned;
        this.pinOrder = pinOrder;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPinned() {
        return pinned;
    }

    public Integer getPinOrder() {
        return pinOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public void setPinOrder(Integer pinOrder) {
        this.pinOrder = pinOrder;
    }
}