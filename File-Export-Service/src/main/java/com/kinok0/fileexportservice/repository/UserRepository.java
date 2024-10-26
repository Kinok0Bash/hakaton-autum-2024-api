//package com.kinok0.fileexportservice.repository;
//
//import com.kinok0.fileexportservice.entity.RefreshTokenEntity;
//import com.kinok0.fileexportservice.entity.UserEntity;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public interface UserRepository extends CrudRepository<UserEntity, UUID> {
//    UserEntity findByLogin(String login);
//
//    @Override
//    List<UserEntity> findAll();
//
//    @Query("select u.login from UserEntity u")
//    List<String> findAllLogins();
//}