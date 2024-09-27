package com.example.model

object RetailerRepository {
    private val retailers = mutableListOf<Retailer>()

    fun findById(id: RetailerId) = retailers.find {
        it.id != null && it.id == id
    }

    fun add(retailer: Retailer) {
        retailers.add(retailer)
    }
}