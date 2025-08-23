package org.sw.sample.api.user.repository

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import org.sw.sample.api.user.node.Account
import org.sw.sample.api.user.repository.dao.UserDao
import org.sw.sample.api.user.repository.entity.UserEntity

@Repository
class UserRepository(
    val manager: EntityManager,
    val userDao: UserDao
) {

    fun findUserInRepository(): List<UserEntity> {
        return this.userDao.findAll();
    }

    fun readUsersByFilterInRepository(filter: UserEntity): List<UserEntity> {
//        val factory = JPAQueryFactory(this.manager);
//        factory.selectFrom(QUserEntity.userEntity)
//
//        val factory = JPAQueryFactory(manager)
//        factory.selectFrom()
//
//        return filter?.let { inner ->
//            this.container.filter { user ->
//                (inner.name?.contains(user.name) ?: true) &&
//                        (inner.age?.equals(user.age) ?: true) &&
//                        (inner.id?.contains(user.id) ?: true)
//            }
//        } ?: this.container
        return emptyList();
    }

    @Transactional
    fun createUserInRepository(user: UserEntity): UserEntity {
        this.manager.persist(user);
        return user;
    }


}
