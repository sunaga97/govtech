package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.BadParamException;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Locale;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return service.saveUsers(users);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity findAllUsers(@RequestParam(defaultValue = "0")   String min,
                                       @RequestParam(defaultValue = "4000") String max,
                                       @RequestParam(defaultValue = "0") String offset,
                                       @RequestParam(defaultValue = "-1") String limit,
                                       @RequestParam(defaultValue = "null") String order){
        Double minFloat;
        Double maxFloat;
        Integer offset1;
        Integer limit1;
        try {
            minFloat = Double.parseDouble(min);
            maxFloat = Double.parseDouble(max);
            offset1 = Integer.parseInt(offset);
            limit1 = Integer.parseInt(limit);
        }catch (NumberFormatException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Please enter float/integer in the query parameter"));
        }
        System.out.println("Limit: " + limit);
        if(!order.equals("null") && !order.toLowerCase().equals("salary") && !order.toLowerCase().equals("name")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("order has to be salary or name"));
        }

        if(minFloat > maxFloat) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Min cannot be more than max"));
        }

        if(limit1 < 0) {
//            System.out.println("controller: " + order);
            return ResponseEntity.ok(new Result(service.getUsersByParamsNoLimit(minFloat, maxFloat, order.toLowerCase(), offset1)));
        }
        return ResponseEntity.ok(new Result(service.getUsersByParams(minFloat, maxFloat, order.toLowerCase(), limit1, offset1)));
    }

//    @GetMapping("/users")
//    public List<User> findAllUsers(){
//        return service.getUsers();
//    }

//    http://localhost:9191/userByName?name=John
    @GetMapping("/userByName")
    public User findUserByName(@RequestParam String name) {
        return service.getUserByName(name);
    }

    @GetMapping("/userBySalary/{salary}")
    public User findUserBySalary(@PathVariable double salary) {
        return service.getUserBySalary(salary);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }
}
