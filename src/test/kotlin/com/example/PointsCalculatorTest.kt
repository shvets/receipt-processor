package com.example

import com.example.model.*
import kotlin.test.*

class PointsCalculatorTest {
    val subject = PointsCalculator()

    @Test
    fun `We have letters in retailer name`()  {
        val retailer = Retailer(null, RetailerName("Target"), "2022-01-02", "13:01", emptyList(), "3.74")

        val points = subject.calculate(retailer)

        assertEquals(6, points)
    }

    @Test
    fun `We have total as multiple of 1 dollar`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", emptyList(), "10.0")

        val points = subject.calculate(retailer)

        assertEquals(50 + 25, points)
    }

    @Test
    fun `We have total as multiple of quarter of dollar`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", emptyList(), "3.75")

        val points = subject.calculate(retailer)

        assertEquals(25, points)
    }

    @Test
    fun `We have pairs in items`() {
        val items = listOf(
            Product("", "0"),
            Product("", "0"),
            Product("", "0"),
            Product("", "0"),
            Product("", "0")
        )

        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", items, "3.74")

        val points = subject.calculate(retailer)

        assertEquals(10, points)
    }

    @Test
    fun `We have multiple of 3 in item description`() {
        val items = listOf(
            Product("Mountain Dew 12PK", "6.49"),
            Product("Emils Cheese Pizza", "12.25"),
            Product("Knorr Creamy Chicken", "1.26"),
            Product("Doritos Nacho Cheese", "3.35"),
            Product("   Klarbrunn 12-PK 12 FL OZ  ", "12.00")
        )

        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", items, "3.74")

        val points = subject.calculate(retailer)

        assertEquals(10 + 6, points)
    }

    @Test
    fun `We have odd day in the purchase date`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-01", "13:01", emptyList(), "3.74")

        val points = subject.calculate(retailer)

        assertEquals(6, points)
    }

    @Test
    fun `We have even day in the purchase date`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", emptyList(), "3.74")

        val points = subject.calculate(retailer)

        assertEquals(0, points)
    }

    @Test
    fun `We have time of purchase is after 2-00pm and before 4-00pm`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "15:15", emptyList(), "3.74")

        val points = subject.calculate(retailer)

        assertEquals(10, points)
    }

    @Test
    fun `We have time of purchase is before 2-00pm`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", emptyList(), "3.74")

        val points = subject.calculate(retailer)

        assertEquals(0, points)
    }

    @Test
    fun `We have time of purchase is after 4-00pm`() {
        val retailer = Retailer(null, RetailerName("123"), "2022-01-02", "13:01", emptyList(), "3.74")

        val points = subject.calculate(retailer)

        assertEquals(0, points)
    }

    @Test
    fun `We test Target retailer points`() {
        val items = listOf(
            Product("Mountain Dew 12PK", "6.49"),
            Product("Emils Cheese Pizza", "12.25"),
            Product("Knorr Creamy Chicken", "1.26"),
            Product("Doritos Nacho Cheese", "3.35"),
            Product("   Klarbrunn 12-PK 12 FL OZ  ", "12.00")
        )


        val retailer = Retailer(null, RetailerName("Target"), "2022-01-01", "13:01", items, "35.35")

        val points = subject.calculate(retailer)

        assertEquals(28, points)
    }

    @Test
    fun `We test M&M Corner Market retailer points`() {
        val items = listOf(
            Product("Gatorade", "2.25"),
            Product("Gatorade", "2.25"),
            Product("Gatorade", "2.25"),
            Product("Gatorade", "2.25")
        )

        val retailer = Retailer(null, RetailerName("M&M Corner Market"), "2022-03-20", "14:33", items, "9.00")

        val points = subject.calculate(retailer)

        assertEquals(109, points)
    }
}