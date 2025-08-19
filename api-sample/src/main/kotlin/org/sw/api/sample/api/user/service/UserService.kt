package org.sw.api.sample.api.user.service

import org.springframework.cglib.beans.BeanGenerator.addProperties
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.sw.api.sample.api.CommonException
import org.sw.api.sample.api.user.repository.UserRepository
import org.sw.api.sample.api.user.node.Account
import org.sw.api.sample.api.user.repository.entity.UserEntity

import org.springframework.transaction.annotation.Transactional

@Service
@Transactional  // 클래스 레벨에 추가
class UserService(
    val repository: UserRepository
) {

    fun readUsers(): List<Account> {
        val users = this.repository.findUserInRepository()
        println("🔍 Found ${users.size} users in DB")
        users.forEach { println("👤 User: id=${it.id}, name=${it.name}, age=${it.age}") }
        return users.map { convertTo(it) }
    }

    //    fun readUsers(account: Account?): UserResult<List<Account>> {
//        return this.repository.readUserByFilterInRepository(filter = account)
//            .map { convertTo(it) }
//            .let { it -> UserResult.Ok(it) }
//    }
//
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

    fun convertTo(account: Account): UserEntity {
        return UserEntity(id = account.id!!, name = account.name!!, age = account.age!!)
    }
}