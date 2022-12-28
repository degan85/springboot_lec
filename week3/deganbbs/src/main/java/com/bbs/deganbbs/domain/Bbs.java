package com.bbs.deganbbs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bbs {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String memo;
    private String username;
}
