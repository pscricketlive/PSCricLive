package com.pscricketlive.data.local

import androidx.room.TypeConverter
import com.pscricketlive.data.model.DismissalType
import com.pscricketlive.data.model.Wicket

/**
 * Teaches Room how to store a Wicket (a nested object) in a single database column.
 * Enums (ExtraType, MatchStatus, etc.) need no converter — Room 2.3+ handles those
 * automatically. Only custom classes like Wicket need this manual approach.
 */
class Converters {

    // Encodes as "dismissedPlayerId|dismissalType|fielderId" — simple pipe-delimited
    // string, avoids pulling in a JSON library for one small object.
    @TypeConverter
    fun fromWicket(wicket: Wicket?): String? {
        if (wicket == null) return null
        return "${wicket.dismissedPlayerId}|${wicket.dismissalType.name}|${wicket.fielderId ?: ""}"
    }

    @TypeConverter
    fun toWicket(value: String?): Wicket? {
        if (value == null) return null
        val parts = value.split("|")
        return Wicket(
            dismissedPlayerId = parts[0],
            dismissalType = DismissalType.valueOf(parts[1]),
            fielderId = parts[2].ifEmpty { null }
        )
    }
}