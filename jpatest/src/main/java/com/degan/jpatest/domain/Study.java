package com.degan.jpatest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Study {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 관계의 주인은 Study
    @ManyToOne
    private Account owner;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getOwner() {
        return this.owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

}
