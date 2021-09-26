package app.orienti.android.entities.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ControlPoint(
    @PrimaryKey
    val id: UUID,
    val code: Int,
    val name: String,
    val updatedAt: Date
)