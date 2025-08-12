package org.sw.api.sample.api.user.repository

import org.springframework.stereotype.Repository
import org.sw.api.sample.api.user.node.Account
import org.sw.api.sample.api.user.repository.entity.UserEntity

@Repository
class UserRepository(
    val container: MutableList<UserEntity> = mutableListOf()
) {

    fun readUserInRepository(): List<UserEntity> {
        return this.container
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

    fun createUserInRepository(user: UserEntity): Int {
        return this.container.find { it.id == user.id }?.let { 0 } ?: this.container.add(user).let { 1 }
    }


}
