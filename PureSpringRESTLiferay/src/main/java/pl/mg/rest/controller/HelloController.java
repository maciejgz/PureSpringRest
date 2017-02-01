package pl.mg.rest.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.mg.rest.model.User;

@RestController
public class HelloController {

    // @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}", produces = "application/json; charset=UTF-8")
    @GetMapping(value = "/hello/{name}", produces = "application/json")
    public ResponseEntity<Object> getHello(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        user.setAge(new Random().nextInt(50));
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

}
