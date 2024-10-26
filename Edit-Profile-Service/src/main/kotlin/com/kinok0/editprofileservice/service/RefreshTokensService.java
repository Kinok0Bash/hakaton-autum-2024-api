package com.kinok0.editprofileservice.service;

import com.kinok0.editprofileservice.entity.RefreshTokenEntity;
import com.kinok0.editprofileservice.repository.RefreshTokensRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefreshTokensService {

    private RefreshTokensRepository refreshTokensRepository;

    public RefreshTokensService(RefreshTokensRepository refreshTokensRepository) {
        this.refreshTokensRepository = refreshTokensRepository;
    }

    public List<RefreshTokenEntity> findAll() {
        return refreshTokensRepository.findAll();
    }
}
