package alatoo.edu.kg.kyrgyzmate.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun formatLastSeen(time: LocalDateTime): String {
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val seenDate = time.toLocalDate()

        return when (seenDate) {
            today -> "Today at ${time.format(DateTimeFormatter.ofPattern("HH:mm"))}"
            yesterday -> "Yesterday at ${time.format(DateTimeFormatter.ofPattern("HH:mm"))}"
            else -> "at ${time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}"
        }
    }
}