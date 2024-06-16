package org.example.Services;

import org.example.DataAccessLayer.CatDAO;
import org.example.DataAccessLayer.OwnerDAO;
import org.example.dto.CatDto;
import org.example.dto.OwnerDto;
import org.example.entities.Cat;
import org.example.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatService {
    private final CatDAO catDAO;
    private final OwnerDAO ownerDAO;

    @Autowired
    public CatService(CatDAO catDAO, OwnerDAO ownerDAO) {
        this.catDAO = catDAO;
        this.ownerDAO = ownerDAO;
    }

    public void addCat(CatDto dto) {
        catDAO.save(toEntity(dto));
    }

    public void updateCat(CatDto dto) {
        catDAO.save(toEntity(dto));
    }

    public void deleteCat(int id) {
        catDAO.deleteById(id);
    }
    
    public CatDto getCatById(int catId) {
        return new CatDto(catDAO.findById(catId).orElse(null));
    }

    public List<CatDto> getAllCats() {
        List<Cat> cats = catDAO.findAll();
        List<CatDto> catDtos = new ArrayList<>();
        for(Cat cat : cats){
            catDtos.add(new CatDto(cat));
        }
        return catDtos;
    }
    
    public OwnerDto getOwner(int catId) {
        return new OwnerDto(catDAO.findById(catId).get().getOwner());
    }

    public List<CatDto> getFriends(int catId){
        List<Cat> cats = catDAO.findById(catId).orElse(null).getFriends();
        List<CatDto> catDtos = new ArrayList<>();
        for(Cat cat : cats){
            catDtos.add(new CatDto(cat));
        }
        return catDtos;
    }

    public List<CatDto> filterByColor(String color){
        List<CatDto> all = getAllCats();
        List<CatDto> answ = new ArrayList<>();

        for(CatDto cat : all)
        {
            if(cat.color == color)
                answ.add(cat);
        }
        return answ;
    }

    public List<CatDto> filterByBreed(String breed){
        List<CatDto> all = getAllCats();
        List<CatDto> answ = new ArrayList<>();

        for(CatDto cat : all)
        {
            if(cat.breed == breed)
                answ.add(cat);
        }
        return answ;
    }

    private Cat toEntity(CatDto dto)
    {
        Cat cat = new Cat(dto.id, dto.name);
        cat.setOwner(ownerDAO.findById(dto.owner_id).orElse(null));
        cat.setBirthdate(dto.birthdate);
        cat.setColor(dto.color);
        cat.setBreed(dto.breed);
        List<Cat> friends = new ArrayList<>();
        for(int id : dto.friends_id)
        {
            friends.add(catDAO.findById(id).orElse(null));
        }
        cat.setFriends(friends);
        return cat;
    }
}
