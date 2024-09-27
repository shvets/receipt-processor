package com.example.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PointsCalculator {
    fun calculate(retailer: Retailer): Int {
        var result = 0

        result += pointsFromRetailerName(retailer)

        result += pointsFromTotal(retailer)

        result += pointsFromPairs(retailer)

        result += pointsFromPairDescriptions(retailer)

        result += pointsFromPurchaseDate(retailer)

        result += pointsFromPurchaseTime(retailer)

        return result
    }

    // One point for every alphanumeric character in the retailer name.

    private fun pointsFromRetailerName(retailer: Retailer) =
        retailer.retailer.value.toCharArray().count { it.isLetter() }

    private fun pointsFromTotal(retailer: Retailer): Int {
        var result = 0

        val total = retailer.total.toDouble()

        if (total > 0) {
            // 50 points if the total is a round dollar amount with no cents.

            if (total % 1 == 0.0) {
                result += 50
            }

            // 25 points if the total is a multiple of 0.25.

            if (total % 0.25 == 0.0) {
                result += 25
            }
        }

        return result
    }

    private fun pointsFromPairs(retailer: Retailer): Int {
        // rule 4
        // 5 points for every two items on the receipt.

        val pairsCount = retailer.items.count() / 2

        return pairsCount * 5
    }

    private fun pointsFromPairDescriptions(retailer: Retailer): Int {
        var result = 0

        // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.

        for (item in retailer.items) {

            if (item.shortDescription.trim().length % 3 == 0) {
                result += Math.ceil((item.price.toDouble() * 0.2)).toInt()
            }
        }

        return result
    }

    private fun pointsFromPurchaseDate(retailer: Retailer): Int {
        var result = 0

        // 6 points if the day in the purchase date is odd.

        val format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(retailer.purchaseDate, format1)

        if (date.dayOfMonth % 2 != 0) {
            result += 6
        }

        return result
    }

    private fun pointsFromPurchaseTime(retailer: Retailer): Int {
        var result = 0

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.

        val format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val time = LocalDateTime.parse(retailer.purchaseDate + " " + retailer.purchaseTime, format2)

        if (time.hour >= 14 && time.minute > 0 && time.hour < 16) {
            result += 10
        }

        return result
    }

}