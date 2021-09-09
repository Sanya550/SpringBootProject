package overonix.overo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import overonix.overo.entity.User;
import overonix.overo.exception.NotAllowsCoordinateException;
import overonix.overo.repository.UserRepository;
import overonix.overo.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/receiveUserByAddress")
    public User receiveUserByAddress(@PathVariable String address) {
        return userService.getByAddress(address);
    }

    @GetMapping("/receiveUserByCoordinate")
    public List<User> receiveUserByCoordinate(@PathVariable double latitude, double longitude) throws NotAllowsCoordinateException {
        return userService.getByCoordinate(latitude, longitude);
    }

    @GetMapping(value = "/findUserById/{id}")
    public ResponseEntity<User> findByIdentif(@PathVariable("id") long id) {
        User user = userService.findByIdUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/createUser")
    public void create(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping(value = "/updateUser")
    public void update(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping(value = "/deleteUser")
    public void delete(@RequestBody User user) {
        userService.deleteUser(user);
    }
}