package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserByName(String name){
        return repository.findById(name).orElse(null);
    }

    public User getUserBySalary(double salary){
        return repository.findBySalary(salary);
    }

    public List<User> getUsersByParams(double min, double max, String order, int limit, int offset) {
//        System.out.println("order: " + order);
        return repository.getUsersByParams(min, max, order, limit, offset);
    }

    public List<User> getUsersByParamsNoLimit(double min, double max, String order, int offset) {
//        System.out.println("order: " + order);
        try{

            return repository.getUsersByParamsNoLimit(min, max, order, offset);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }

    }

//    public User getUsersBySalary(double minsalary, double maxsalary){
//        return repository.findBySalary;
//    }

    public User updateUser(User user) {
        User userToUpdate = repository.findById(user.getName()).orElse(null);
        user.setSalary(user.getSalary());
        return repository.save(userToUpdate);
    }
}
