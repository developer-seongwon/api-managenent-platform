package org.sw.sample.api.user.repository.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.sw.sample.api.user.repository.entity.UserEntity

@Repository
interface UserDao: JpaRepository<UserEntity, String>{

    
}