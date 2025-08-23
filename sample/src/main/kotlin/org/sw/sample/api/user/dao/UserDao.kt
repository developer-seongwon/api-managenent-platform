package org.sw.sample.api.user.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.sw.sample.api.user.entity.UserEntity

@Repository
interface UserDao: JpaRepository<UserEntity, String>{


}