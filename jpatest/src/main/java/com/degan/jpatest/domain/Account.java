package com.degan.jpatest.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Account {
    
    //@GeneratedValue 전략은 기본적으로 db에 따라 다름
    // (strategy = ) GenerationType 에서 선택할 수 있음
    @Id
    @GeneratedValue
    private Long id;

    // f12 눌러서 확인
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    // DB에서는 사용 안 함
    // 객체로만 사용
    @Transient
    private String no;

    @Embedded
    private Address address;

    // 관계의 주인은 Study
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    // 컬럼 변경해서 사용
    // @Embedded
    // @AttributeOverrides({
    //     @AttributeOverride(name = "street", column = @Column(name="home_street"))
    // })
    // private Address address;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Study> getStudies() {
        return this.studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }
    

    // add or remove convenience 메소드 만들어서 사용
    public void addStudy(Study study) {
        study.setOwner(this);
        this.getStudies().add(study);
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
