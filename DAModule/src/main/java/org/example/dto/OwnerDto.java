package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.entities.AppUser;
import org.example.entities.Cat;
import org.example.entities.Owner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OwnerDto {
    public int id;
    public String name;
    public Date birthdate;
    public List<Integer> cats_id;
    public OwnerDto(Owner owner) {

        id = owner.getId();
        name = owner.getName();
        birthdate = owner.getBirthdate();
        cats_id = new ArrayList<>();
        for(Cat cat : owner.getCats())
        {
            cats_id.add(cat.getId());
        }
    }
}
