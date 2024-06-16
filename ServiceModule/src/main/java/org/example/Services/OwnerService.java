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
public class OwnerService {
    private final OwnerDAO ownerDAO;
    private final CatDAO catDAO;


    @Autowired
    public OwnerService(OwnerDAO ownerDAO, CatDAO catDAO) {
        this.ownerDAO = ownerDAO;
        this.catDAO = catDAO;
    }

    public void addOwner(Owner dto) {
        ownerDAO.save(dto);
    }

    public void updateOwner(OwnerDto dto) {
        ownerDAO.save(toEntity(dto));
    }


    public void deleteOwner(int id) {
        ownerDAO.deleteById(id);
    }

    public Owner getOwnerById(int ownerId) {
        return ownerDAO.findById(ownerId).orElse(null);
    }

    public List<OwnerDto> getAllOwners() {
        List<Owner> owners = ownerDAO.findAll();
        List<OwnerDto> ownerDtos = new ArrayList<>();
        for(Owner owner : owners){
            ownerDtos.add(new OwnerDto(owner));
        }
        return ownerDtos;
    }

    public List<CatDto> getCatsByOwner(int ownerId) {
        List<Cat> cats = ownerDAO.findById(ownerId).orElse(null).getCats();
        List<CatDto> catDtos = new ArrayList<>();
        for(Cat cat : cats){
            catDtos.add(new CatDto(cat));
        }
        return catDtos;
    }

    private Owner toEntity(OwnerDto dto)
    {
        Owner owner = new Owner(dto.id, dto.name);
        owner.setBirthdate(dto.birthdate);
        List<Cat> cats = new ArrayList<>();
        for(int id : dto.cats_id)
        {
            cats.add(catDAO.findById(id).orElse(null));
        }
        owner.setCats(cats);
        return owner;
    }
}
