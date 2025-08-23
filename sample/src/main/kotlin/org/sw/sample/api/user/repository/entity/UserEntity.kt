package org.sw.sample.api.user.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "t_user")
data class UserEntity(

    @Id
    @Column(name = "id", unique = true)
    val id: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "age")
    val age: Int? = null
)
