package com.buyansell.models

data class Order(
    val id: String,
    val productList: List<Product>,
    val totalAmount: Double,
    val orderDate: Long,
    var status: OrderStatus = OrderStatus.PENDING
)

enum class OrderStatus {
    PENDING,
    APPROVED,
    REJECTED
}
