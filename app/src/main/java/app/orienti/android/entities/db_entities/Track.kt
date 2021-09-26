package app.orienti.android.entities.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Track(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val updatedAt: Date
)