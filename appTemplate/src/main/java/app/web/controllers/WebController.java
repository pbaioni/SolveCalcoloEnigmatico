package app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.persistence.exceptions.EntityNotFoundException;
import app.persistence.model.UserDo;
import app.persistence.repo.UserRepository;
import app.persistence.services.UserService;

@RestController
@RequestMapping("/")
public class WebController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public Iterable<UserDo> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/userName/{userName}")
    public UserDo getUser(@PathVariable String userName) {
        return userService.getUser(userName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDo createUser(@RequestBody UserDo user) {
    	//TODO: use a DTO object in the request body
        UserDo createdUser = userService.create(user);
        return createdUser;
    }

    @PutMapping("/{userName}")
    public UserDo updateUser(@RequestBody UserDo user) {
    	return userService.updateUser(user);
    }
    
    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable String userName) {
    	userService.deleteUser(userName);
    }
	
}
