package org.example.Controllers;

import org.example.Services.CatService;
import org.example.Services.UserService;
import org.example.dto.CatDto;
import org.example.dto.OwnerDto;
import org.example.entities.AppUser;
import org.example.entities.Cat;
import org.example.entities.Owner;
import org.example.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;
    private final UserService userService;

    @Autowired
    public CatController(CatService catService, UserService userService) {
        this.catService = catService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('OWNER')")
    public List<CatDto> getAllCats() {
        OwnerDto owner = getCurrentOwner();
        if(owner == null){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            AppUser appUser = userService.getAppUser(username);
            if(appUser.getRole() == Role.ADMIN)
                return catService.getAllCats();
            else return new ArrayList<>();
        }
        List<CatDto> cats = catService.getAllCats();

        List<CatDto> filteredCats = cats.stream()
                .filter(cat -> isOwnerOfCat(cat, owner))
                .collect(Collectors.toList());

        return filteredCats;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('OWNER')")
    public CatDto getCatById(@PathVariable int id) {
        CatDto cat = catService.getCatById(id);

        OwnerDto owner = getCurrentOwner();
        if (!isOwnerOfCat(cat, owner)) {
            throw new AccessDeniedException("You are not the owner of this cat");
        }

        return cat;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('OWNER')")
    public void addCat(@RequestBody CatDto cat) {
        OwnerDto owner = getCurrentOwner();

        if (!(cat.owner_id == owner.id)) {
            throw new AccessDeniedException("You can only create cats for yourself");
        }

        catService.addCat(cat);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('OWNER')")
    public void updateCat(@PathVariable int id, @RequestBody CatDto cat) {
        OwnerDto owner = getCurrentOwner();

        if (!isOwnerOfCat(cat, owner)) {
            throw new AccessDeniedException("You are not the owner of this cat");
        }

        cat.id = id;
        catService.addCat(cat);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('OWNER')")
    public void deleteCat(@PathVariable int id) {
        OwnerDto owner = getCurrentOwner();

        if(owner == null){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            AppUser appUser = userService.getAppUser(username);
            if(appUser.getRole() == Role.ADMIN)
                catService.deleteCat(id);
            return;
        }
        if (!isOwnerOfCat(catService.getCatById(id), owner)) {
            throw new AccessDeniedException("You are not the owner of this cat");
        }
        catService.deleteCat(id);
    }

    @GetMapping("color/{color}")
    public List<CatDto> filterByColor(@PathVariable String color){
        OwnerDto owner = getCurrentOwner();

        List<CatDto> cats = catService.getAllCats();

        List<CatDto> filteredCats = cats.stream()
                .filter(cat -> isOwnerOfCat(cat, owner) && cat.color.equals(color))
                .collect(Collectors.toList());

        return filteredCats;
    }

    @GetMapping("breed/{breed}")
    public List<CatDto> filterByBreed(@PathVariable String breed){
        OwnerDto owner = getCurrentOwner();

        List<CatDto> cats = catService.getAllCats();

        List<CatDto> filteredCats = cats.stream()
                .filter(cat -> isOwnerOfCat(cat, owner) && cat.breed == breed)
                .collect(Collectors.toList());

        return filteredCats;
    }
    private OwnerDto getCurrentOwner() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = userService.getAppUser(username);
        Owner owner = appUser.getOwner();
        if(owner == null)
            return null;
        return new OwnerDto(owner);
    }
    private boolean isOwnerOfCat(CatDto cat, OwnerDto owner) {
        return cat.owner_id == owner.id;
    }
}
