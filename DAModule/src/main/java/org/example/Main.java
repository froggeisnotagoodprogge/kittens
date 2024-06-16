package org.example;

import org.example.DataAccessLayer.CatDAO;
import org.example.DataAccessLayer.CatDAOHibernate;
import org.example.DataAccessLayer.OwnerDAO;
import org.example.DataAccessLayer.OwnerDAOHibernate;
import org.example.entities.Cat;
import org.example.entities.Owner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

//        OwnerDAO ownerDAO = new OwnerDAOHibernate();
//        CatDAO catDAO = new CatDAO();

//        Owner owner1 = new Owner();
//        owner1.setName("Sanek");
//        owner1.setBirthdate(new Date(1995,03,01));
//
//        Owner owner2 = new Owner();
//        owner2.setName("Jhenek");
//        owner2.setBirthdate(new Date(2004,03,25));
//
//        // Create cats
//        Cat cat1 = new Cat();
//        cat1.setName("Kassy");
//        cat1.setBirthdate(new Date());
//        cat1.setBreed("Persian");
//        cat1.setColor("White");
//        cat1.setOwner(owner1);
//
//        Cat cat2 = new Cat();
//        cat2.setName("Tom");
//        cat2.setBirthdate(new Date());
//        cat2.setBreed("Siamese");
//        cat2.setColor("Brown");
//        cat2.setOwner(owner2);
//        List<Cat> friends = new ArrayList<>();
//        friends.add(cat2);
//        cat1.setFriends(friends);
//        friends.clear();
//        friends.add(cat1);
//        cat2.setFriends(friends);
//
//        ownerDAO.create(owner1);
//        ownerDAO.create(owner2);
//        catDAO.create(cat1);
//        catDAO.create(cat2);
//
//        List<Cat> cats = catDAO.getAll();
//        System.out.println("All Cats:");
//        for (Cat cat : cats) {
//            System.out.println(cat.getName() + " - " + cat.getOwner().getName());
//
//        }
//
//        // Fetch all owners
//        List<Owner> owners = ownerDAO.getAll();
//        System.out.println("All Owners:");
//        for (Owner owner : owners) {
//            System.out.println(owner.getName());
//        }
    }
}