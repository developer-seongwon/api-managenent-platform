package org.sw.sample.api.user

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import org.sw.sample.api.user.entity.QUserEntity
import org.sw.sample.api.user.entity.UserEntity

@Repository
class UserRepository(
    private val manager: EntityManager
) {

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

    fun getUser(filter: UserEntity): UserEntity? {
        return JPAQueryFactory(this.manager)
            .select(QUserEntity.userEntity)
            .from(QUserEntity.userEntity)
            .where(QUserEntity.userEntity.id.eq(filter.id))
            .fetchOne();
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

    @Transactional
    fun <T> update(callback: () -> T): T {
        return callback.invoke();
    }

}
