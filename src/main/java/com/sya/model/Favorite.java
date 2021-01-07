package com.sya.model;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Favorite {

    @Id
    @Column(name="favorite_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name ="favorite_name")
    private String name="";

    @Formula("(select count(*) from favorite_has_work a where a.favorite_id = favorite_id)")
    @Column(name ="work_num")
    private Integer workNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @OneToMany(mappedBy = "favorite",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<FavoriteHasWork> favoriteHasWorks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) { this.workNum = workNum; }

    public Set<FavoriteHasWork> getFavoriteHasWorks() {
        return favoriteHasWorks;
    }

    public void setFavoriteHasWorks(Set<FavoriteHasWork> favoriteHasWorks) {
        this.favoriteHasWorks = favoriteHasWorks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
