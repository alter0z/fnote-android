package com.fiberlance.fnote.main.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    fun getTimeAgo(date: String): CharSequence {
        val parsedDate = ZonedDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val timeInMillis = parsedDate.toInstant().toEpochMilli()
        val now = System.currentTimeMillis()

        return DateUtils.getRelativeTimeSpanString(timeInMillis, now, DateUtils.MINUTE_IN_MILLIS)
    }
}