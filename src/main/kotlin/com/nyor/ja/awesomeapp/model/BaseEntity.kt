package com.nyor.ja.awesomeapp.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.time.LocalDateTime


@MappedSuperclass
open class BaseEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    var id: Long? = null

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    var createdAt: LocalDateTime? = null

    /*@PrePersist
    protected fun prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now()
    }*/


    @PrePersist
    protected fun prePersist() {
        if (this.createdAt == null) createdAt = LocalDateTime.now()
    }

}