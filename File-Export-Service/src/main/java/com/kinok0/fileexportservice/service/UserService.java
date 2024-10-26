//package com.kinok0.fileexportservice.service;
//
//import com.kinok0.fileexportservice.entity.Role;
//import com.kinok0.fileexportservice.entity.UserEntity;
//import com.kinok0.fileexportservice.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class UserService {
//
//    private UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<UserEntity> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Transactional
//    public void init() {
//        byte[] bytes = new byte[1024];
//        userRepository.save(new UserEntity("Alice Johnson", "alicej", "password123", Role.ADMIN, "Manager", bytes));
//        userRepository.save(new UserEntity("Bob Smith", "bobsmith", "password456", Role.USER, "Developer", bytes));
//        userRepository.save(new UserEntity("Carol White", "carolw", "password789", Role.USER, "Analyst", bytes));
//        userRepository.save(new UserEntity("David Brown", "davidb", "password321", Role.USER, "Designer", bytes));
//    }
//
//}
