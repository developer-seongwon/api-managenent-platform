package org.sw.sample.api.user.service


import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.sw.sample.api.CommonException
import org.sw.sample.api.user.node.Account
import org.sw.sample.api.user.repository.UserRepository
import org.sw.sample.api.user.repository.entity.UserEntity

@Service
class UserService(
    val repository: UserRepository
) {

    fun readUsers(): List<Account> {
        return this.repository.findUserInRepository().map { convertTo(it) }
    }

    fun readUsers(account: Account?): List<Account> {
        return account?.let { it ->
            this.repository.readUsersByFilterInRepository(convertTo(it))
                .map { convertTo(it) }
        } ?: readUsers()
    }

    //    fun readUser(account: Account): UserResult<Account> {
//        return this.repository.readUserByIdInRepository(id = account.id!!)
//            ?.let { it -> convertTo(it) }
//            ?.let { it -> UserResult.Ok(it) }
//            ?: UserResult.BadRequest("Not Found User", "not found user: ${account.id}")
//    }
//
//
    fun createUser(account: Account): Account {
        return runCatching { this.repository.createUserInRepository(convertTo(account)) }
            .map { convertTo(it) }
            .getOrElse { cause ->
                when (cause) {
                    is DataIntegrityViolationException -> throw CommonException.conflict(cause) {
                        title = "Conflict User"
                        detail = "conflict user id: ${account.id}"
                    }

                    else -> throw CommonException.internalServerError(cause) {
                        title = "Internal Server Error"
                    }
                }
            }
    }


    fun convertTo(user: UserEntity): Account {
        return Account(id = user.id, name = user.name, age = user.age!!)
    }

    fun convertTo(account: Account?): UserEntity {
        return account?.let { it ->
            UserEntity(id = it.id!!, name = it.name!!, age = it.age!!)
        } ?: UserEntity()
    }
}