package org.sw.api.user.repository.entity

import com.sun.tools.attach.AgentLoadException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "t_user")
data class UserEntity(

    @Id
    @Column(name = "id", unique = true)
    val id: String,

    @Column(name = "name")  // UNIQUE 제약 추가!
    val name: String,

    @Column(name = "age")
    val age: Int?
){
    constructor(): this("", "", null)
}
