package com.kinok0.profileservice.repository

import com.kinok0.profileservice.entity.CommentEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CommentRepository : CrudRepository<CommentEntity, UUID> {
}