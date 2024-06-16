package org.example.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.entities.Cat;
import org.example.entities.Owner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CatDto {
    public int id;
    public String name;
    public Date birthdate;
    public String breed;
    public String color;
    public int owner_id;
    public List<Integer> friends_id;

    public CatDto(Cat cat) {
        id = cat.getId();
        name = cat.getName();
        birthdate = cat.getBirthdate();
        breed = cat.getBreed();
        color = cat.getColor();
        owner_id = cat.getOwner().getId();
        friends_id = new ArrayList<>();
        for(Cat friendcat : cat.getFriends())
        {
            friends_id.add(friendcat.getId());
        }
    }
}
