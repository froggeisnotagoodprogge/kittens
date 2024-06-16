package org.example.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import org.example.dto.OwnerDto;

@Entity
@Table(name = "Owner")
public class Owner {
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private AppUser appUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String Name;

    @Column(name = "birthdate")
    private Date birthdate;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Cat> cats;

    public Owner() {
    }

    public Owner(int id, String name) {
        this.id = id;
        Name = name;
    }

    public Owner(int id, String name, Date birthdate) {
        this.id = id;
        Name = name;
        this.birthdate = birthdate;
        this.cats = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
    public OwnerDto toDto()
    {
        return new OwnerDto(this);
    }
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}