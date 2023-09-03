package com.nyor.ja.awesomeapp.service

import com.nyor.ja.awesomeapp.model.BaseEntity
import jakarta.persistence.EntityNotFoundException
import org.hibernate.service.spi.ServiceException
import org.springframework.dao.DataIntegrityViolationException
import java.io.Serializable

interface BaseService<E : BaseEntity> : Serializable {

    @Throws(ServiceException::class)
    fun create(entity: E): E

    @Throws(ServiceException::class)
    fun update(id: Long, entity: E): E

    @Throws(EntityNotFoundException::class)
    fun findById(id: Long): E

    @Throws(ServiceException::class)
    fun findAll(): List<E>

    @Throws(EntityNotFoundException::class, DataIntegrityViolationException::class)
    fun deleteById(id: Long)
}