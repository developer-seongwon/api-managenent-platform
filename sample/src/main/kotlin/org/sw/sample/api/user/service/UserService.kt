package org.sw.sample.api.user.service

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.sw.sample.api.CommonException
import org.sw.sample.api.user.repository.UserRepository
import org.sw.sample.api.user.repository.entity.UserEntity
import org.sw.sample.node.Account

@Service
class UserService(
    val repository: UserRepository
) {

    fun readUsers(account: Account? = null): List<Account> {
        return (account?.let { convertTo(it) } ?: UserEntity())
            .let { this.repository.getUsers(it) }
            .map { convertTo(it) }
    }

    fun readUser(account: Account): Account {
        val users = account.let { convertTo(it) }
            .let { this.repository.getUsers(it) }
        when (users.size) {
            0 -> throw CommonException.Companion.notFound() {
                title = "Not Found User"
                detail = "not found user id: ${account.id}"
            }
            1 -> return convertTo(users[0])
            else -> throw CommonException.Companion.conflict() {
                title = "Conflict User"
                detail = "conflict user id: ${account.id}"
            }
        }
    }

    fun createUser(account: Account): Account {
        return runCatching { this.repository.createUser(convertTo(account)) }
            .map { convertTo(it) }
            .getOrElse { cause ->
                when (cause) {
                    is DataIntegrityViolationException -> throw CommonException.Companion.conflict(cause) {
                        title = "Conflict User"
                        detail = "conflict user id: ${account.id}"
                    }

                    else -> throw CommonException.Companion.internalServerError(cause) {
                        title = "Internal Server Error"
                    }
                }
            }
    }

    fun deleteUser(account: Account): Account {
        val users = convertTo(account).let { this.repository.getUsers(it) }.toMutableList()
        if (users.isEmpty()) {
            throw CommonException.Companion.notFound{
                title = "Not Found User"
                detail = "not found user id: ${account.id}"
            }
        }
        val user = users.removeAt(0);
        if (users.isNotEmpty()) {
            println("어..? 데이터 이상함,, 확인 필요")
        }
        return this.repository.deleteUser(user)
            .let {
                when (it) {
                    0L -> throw CommonException.Companion.notFound() {
                        title = "Not Found User"
                        detail = "not found user id: ${account.id}"
                    }
                    else -> convertTo(user)
                }
            }
    }

    fun convertTo(user: UserEntity): Account {
        return Account(id = user.id, name = user.name, age = user.age)
    }

    fun convertTo(account: Account?): UserEntity {
        return account?.let { it ->
            UserEntity(id = it.id, name = it.name, age = it.age)
        } ?: UserEntity()
    }
}