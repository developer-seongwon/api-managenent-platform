package org.sw.api.sample.api.user.service

import org.springframework.stereotype.Service
import org.sw.api.sample.api.user.repository.UserRepository
import org.sw.api.sample.api.user.node.Account
import org.sw.api.sample.api.user.repository.entity.UserEntity

@Service
class UserService(
    val repository: UserRepository
) {

    fun readUsers(): UserResult<List<Account>> {
        return this.repository.readUserInRepository()
            .map { convertTo(it) }
            .let { it -> UserResult.Ok(it) }
    }

    fun readUsers(account: Account?): UserResult<List<Account>> {
        return this.repository.readUserByFilterInRepository(filter = account)
            .map { convertTo(it) }
            .let { it -> UserResult.Ok(it) }
    }

    fun readUser(account: Account): UserResult<Account> {
        return this.repository.readUserByIdInRepository(id = account.id!!)
            ?.let { it -> convertTo(it) }
            ?.let { it -> UserResult.Ok(it) }
            ?: UserResult.BadRequest("Not Found User", "not found user: ${account.id}")
    }


    fun createUser(account: Account): UserResult<Map<String, Any>> {
        return this.repository.createUserInRepository(convertTo(account))
            .let { it ->
                when (it) {
                    0 -> return UserResult.Conflict("Conflict User", "conflict user: ${account.id}")
                    1 -> return UserResult.Ok(mapOf())
                    else -> UserResult.InternalServerError()
                }
            }
    }


    fun convertTo(user: UserEntity): Account {
        return Account(id = user.id, name = user.name, age = user.age!!)
    }

    fun convertTo(account: Account): UserEntity {
        return UserEntity(id = account.id!!, name = account.name!!, age = account.age!!)
    }
}