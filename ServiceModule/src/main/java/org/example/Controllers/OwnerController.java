package org.example.Controllers;

import org.example.Services.OwnerService;
import org.example.Services.UserService;
import org.example.dto.OwnerDto;
import org.example.entities.AppUser;
import org.example.entities.Owner;
import org.example.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final UserService userService;


    @Autowired
    public OwnerController(OwnerService ownerService, UserService userService) {
        this.ownerService = ownerService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public OwnerDto getOwnerById(@PathVariable int id) {
        return new OwnerDto(ownerService.getOwnerById(id));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PutMapping("/{id}")
    public void updateOwner(@PathVariable int id, @RequestBody OwnerDto owner) {
        owner.id = id;

        ownerService.updateOwner(owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable int id) {

        ownerService.deleteOwner(id);
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto,
                                                @RequestParam("username") String username,
                                                @RequestParam("password") String password) {
        if(userService.getAppUser(username) != null) {
            throw new RuntimeException();
        }



        var owner = new Owner(ownerDto.id, ownerDto.name, ownerDto.birthdate);
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(password);
        appUser.setRole(Role.OWNER);
        appUser.setOwner(owner);
        owner.setAppUser(appUser);

        ownerService.addOwner(owner);

        return ownerDto;
    }
}
