package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ShoppingCartTest {

    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setUp() {
        cart = ShoppingCart()
    }

    @Test
    fun `Add multiple products, total price sum is correct`() {
        // GIVEN
        val product = Product(1, "Product", 10.0)
        cart.addProduct(product, 3)

        // ACTION
        val totalCost = cart.getTotalCost()

        // ASSERT
        assertThat(totalCost).isEqualTo(30.0)
    }

    @Test
    fun `Add product with negative quantity, throw exception`() {
        // GIVEN
        val product = Product(1, "Product", 10.0)

        // ASSERT
        assertFailure {
            cart.addProduct(product, -1)
        }
    }
}