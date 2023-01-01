package com.bbs.deganbbs.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String lastname;
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
}
