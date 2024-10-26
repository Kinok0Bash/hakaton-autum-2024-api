package com.kinok0.editprofileservice.service;

import com.kinok0.editprofileservice.dto.request.UserAvatarRequest;
import com.kinok0.editprofileservice.dto.request.UserNameRequest;
import com.kinok0.editprofileservice.dto.request.UserPositionRequest;
import com.kinok0.editprofileservice.dto.response.UserResponse;
import com.kinok0.editprofileservice.entity.UserEntity;
import com.kinok0.editprofileservice.repository.UserRepository;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kinok0.editprofileservice.util.EntityConverterKt;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserResponse changeName(UserNameRequest userNameRequest) {
        val userEntity = userRepository.findById(userNameRequest.getId()).get();

        userEntity.setName(userNameRequest.getName());

        return EntityConverterKt.convertToUserDTO(userRepository.save(userEntity));
    }

    public UserResponse changePosition(UserPositionRequest userPositionRequest){
        val userEntity = userRepository.findById(userPositionRequest.getId()).get();

        userEntity.setPosition(userPositionRequest.getPosition());

        return EntityConverterKt.convertToUserDTO(userRepository.save(userEntity));
    }

    public UserResponse changeAvatar(UserAvatarRequest userAvatarRequest){
        val userEntity = userRepository.findById(userAvatarRequest.getId()).get();

        userEntity.setAvatar(userAvatarRequest.getAvatar());

        return EntityConverterKt.convertToUserDTO(userRepository.save(userEntity));
    }
}
