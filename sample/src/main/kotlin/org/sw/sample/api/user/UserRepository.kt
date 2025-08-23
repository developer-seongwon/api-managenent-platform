package org.sw.sample.api.user

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import org.sw.sample.api.user.dao.UserDao
import org.sw.sample.api.user.repository.entity.QUserEntity
import org.sw.sample.api.user.entity.UserEntity

@Repository
class UserRepository(
    private val manager: EntityManager,
    private val userDao: UserDao
) {

//    fun findUserInRepository(): List<UserEntity> {
//        return this.userDao.findAll();
//    }
//
//    fun readUsersByFilterInRepository(filter: UserEntity): List<UserEntity> {
//        val builder = BooleanBuilder();
//
//        filter.id?.let { id -> builder.and(QUserEntity.userEntity.name.contains(id))}
//        filter.name?.let { name -> builder.and(QUserEntity.userEntity.name.eq(name)) }
//        filter.age?.let { age -> builder.and(QUserEntity.userEntity.age.eq(age)) }
//
//        return JPAQueryFactory(this.manager)
//            .select(QUserEntity.userEntity)
//            .from(QUserEntity.userEntity)
//            .where(builder)
//            .fetch();
//    }

    fun getUsers(filter: UserEntity): List<UserEntity> {
        val builder = BooleanBuilder();

        filter.id?.let { id -> builder.and(QUserEntity.userEntity.id.eq(id)) }
        filter.name?.let { name -> builder.and(QUserEntity.userEntity.name.contains(name)) }
        filter.age?.let { age -> builder.and(QUserEntity.userEntity.age.eq(age)) }

        return JPAQueryFactory(this.manager)
            .select(QUserEntity.userEntity)
            .from(QUserEntity.userEntity)
            .where(builder)
            .fetch();
    }

    @Transactional
    fun createUser(user: UserEntity): UserEntity {
        this.manager.persist(user);
        return user;
    }

    @Transactional
    fun deleteUser(user: UserEntity): Long {
        return JPAQueryFactory(this.manager)
            .delete(QUserEntity.userEntity)
            .where(QUserEntity.userEntity.id.eq(user.id))
            .execute();
    }


}
