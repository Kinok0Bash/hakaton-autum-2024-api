package com.kinok0.editprofileservice.repository;

import com.kinok0.editprofileservice.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RefreshTokensRepository extends CrudRepository<RefreshTokenEntity, UUID> {
    RefreshTokenEntity findRefreshTokensEntityByToken(String token);

    @Override
    List<RefreshTokenEntity> findAll();
}
