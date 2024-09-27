package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val shortDescription: String,
    val price: String
)

@JvmInline
@Serializable
value class RetailerId(val value: String) {
    init {
        require(value.isNotBlank()) { "Retailer Id cannot be blank" }
        require(value.length == 36) { "Retailer Id must be 36 characters" }
    }
}

@JvmInline
@Serializable
value class RetailerName(val value: String) {
    init {
        require(value.isNotBlank()) { "Retailer Name cannot be blank" }
    }
}

@Serializable
data class Retailer(
    val id: RetailerId? = null,
    val retailer: RetailerName,
    val purchaseDate: String,
    val purchaseTime: String,
    val items: List<Product>,
    val total: String
)
