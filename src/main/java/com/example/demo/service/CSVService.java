package com.example.demo.service;

import com.example.demo.exception.CSVFormatException;
import com.example.demo.helper.CSVHelper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    UserRepository repository;

    public void save(MultipartFile file) throws IllegalArgumentException, CSVFormatException{
        try {
            List<User> users = CSVHelper.csvToUsers(file.getInputStream());
            List<User> usersToSave = new ArrayList<>();
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getSalary() <= 0) {
//                    throw new IllegalArgumentException("There is/are user(s) with negative salary");
                    users.get(i).setSalary(0);

                }
                usersToSave.add(users.get(i));
            }

            repository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
