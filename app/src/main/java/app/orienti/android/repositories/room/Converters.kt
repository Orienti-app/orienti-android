package app.orienti.android.repositories.room

import androidx.room.TypeConverter
import sk.backbone.parent.utils.fromUtcString
import sk.backbone.parent.utils.toUtcString
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object Converters {
    @TypeConverter
    @JvmStatic
    fun toUUID(value: String?): UUID {
        return UUID.fromString(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromUUID(value: UUID): String {
        return value.toString()
    }

    @TypeConverter
    @JvmStatic
    fun toDate(value: String?): Date? {
        return fromUtcString(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromDate(value: Date?): String? {
        return toUtcString(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.setScale(2, RoundingMode.HALF_UP).toString()
    }

    @TypeConverter
    @JvmStatic
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(value) }
    }
}