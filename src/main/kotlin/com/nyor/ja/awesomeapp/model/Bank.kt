package com.nyor.ja.awesomeapp.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "bank")
class Bank : BaseEntity() {

    @Column(name= "account_number", unique = true)
    var accountNumber: String? = ""

    @Column(name= "name")
    var name: String? = null

    @Column(name= "trust")
    var trust: Double? =  null

    @Column(name= "transaction_fee")
    var transactionFee: Int? = null
}

