package com.example.ratemate.data.api

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getHistoricalDates(): List<String> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val today = LocalDate.now()
    return listOf(
        today.minusDays(1).format(formatter),
        today.minusWeeks(1).format(formatter),
        today.minusMonths(1).format(formatter),
        today.minusYears(1).format(formatter)
    )
}

fun getOneDayPriorDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().minusDays(1).format(formatter)
}

fun getOneWeekPriorDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().minusWeeks(1).format(formatter)
}

fun getOneMonthPriorDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().minusMonths(1).format(formatter)
}

fun getOneYearPriorDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().minusYears(1).format(formatter)
}
