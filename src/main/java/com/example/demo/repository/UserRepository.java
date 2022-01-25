package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findBySalary(double salary);

    @Query(value = "Select * FROM User u WHERE u.salary >= :min AND u.salary <=:max " +
            "ORDER BY CASE WHEN :order = 'salary' THEN salary END ASC, " +
            "CASE WHEN :order = 'name' THEN name END ASC " +
            "LIMIT :limit OFFSET :offset ", nativeQuery = true)
    List<User> getUsersByParams(@Param("min") double min,
                                @Param("max") double max,
                                @Param("order") String ordering,
                                @Param("limit") int limit,
                                @Param("offset") int offset);

//    ORDER BY CASE WHEN :sort_param = 'name ASC'  THEN activation_name END ASC
//         , CASE WHEN :sort_param = 'name DESC' THEN activation_name END DESC
    @Query(value = "Select * FROM User u WHERE u.salary >= :min AND u.salary <=:max " +
            "ORDER BY CASE WHEN :order = 'salary' THEN salary END ASC, " +
            "CASE WHEN :order = 'name' THEN name END ASC " +
            "LIMIT 18446744073709551615 OFFSET :offset ", nativeQuery = true)
    List<User> getUsersByParamsNoLimit(@Param("min") double min, @Param("max") double max, @Param("order") String ordering, @Param("offset") int offset);

}
