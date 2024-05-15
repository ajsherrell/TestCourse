package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ShoppingCartTest {

    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setUp() {
        cart = ShoppingCart()
    }

    // Instead of using JUnit's @Test annotation, we use ParameterizedTest to run the test multiple times.
    @ParameterizedTest
//    @ValueSource(
//        ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
//    )
    @CsvSource(
        "1, 12.0",
        "0, 0.0",
        "3, 3.0",
        "4, 1.0",
        "5, 13.0",
        "6, 16.0",
        "7, 5.0",
        "8, 9.0",
        "9, 2.0",
        "10, 4.0"
    )
    fun `Add multiple products, total price sum is correct`(quantity: Int, expectedPrice: Double) {
        // GIVEN
        val product = Product(1, "Product", expectedPrice)
        cart.addProduct(product, quantity)

        // ACTION
        val totalCost = cart.getTotalCost()

        // ASSERT
        assertThat(totalCost).isEqualTo(quantity * expectedPrice)
    }

    @RepeatedTest(50) // Needed if testing for a flaky test.
    fun `Add product with negative quantity, throw exception`() {
        // GIVEN
        val product = Product(1, "Product", 10.0)

        // ASSERT
        assertFailure {
            cart.addProduct(product, -1)
        }
    }
}