package org.sw.api.sample.api.user.repository

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Repository
import org.sw.api.sample.api.user.node.Account
import org.sw.api.sample.api.user.repository.dao.UserDao
import org.sw.api.sample.api.user.repository.entity.UserEntity

@Repository
class UserRepository(
    val userDao: UserDao,
    val container: MutableList<UserEntity>
) {

    fun findUserInRepository(): List<UserEntity> {
        return this.userDao.findAll();
    }

    fun readUserByFilterInRepository(filter: Account?): List<UserEntity> {
        return filter?.let { inner ->
            this.container.filter { user ->
                (inner.name?.contains(user.name) ?: true) &&
                        (inner.age?.equals(user.age) ?: true) &&
                        (inner.id?.contains(user.id) ?: true)
            }
        } ?: this.container
    }

    fun readUserByIdInRepository(id: String): UserEntity? {
        return this.container.find { it.id == id }
    }

    fun createUserInRepository(user: UserEntity): UserEntity {
        return this.userDao.save(user)
    }


}
