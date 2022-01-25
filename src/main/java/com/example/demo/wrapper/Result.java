package com.example.demo.wrapper;

import java.util.List;
import com.example.demo.entity.User;
import lombok.Data;

@Data
public class Result {
    private List<User> results;

    public Result(List<User> results) {
        this.results = results;
    }
}
