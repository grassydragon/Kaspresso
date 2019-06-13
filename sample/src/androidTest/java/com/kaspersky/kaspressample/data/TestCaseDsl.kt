package com.kaspersky.kaspressample.data

import com.kaspersky.kaspressample.dsl.TestCaseDslMarker

@TestCaseDslMarker
class TestCaseDsl {

    val companies: MutableList<Company> = mutableListOf()
    val owners: MutableList<Owner> = mutableListOf()

    fun company(block: Company.() -> Unit) {
        val companyDsl = Company()
        companyDsl.block()
        companies += companyDsl
    }

    fun owner(block: Owner.() -> Unit) {
        val ownerDsl = Owner()
        ownerDsl.block()
        owners += ownerDsl
    }

}