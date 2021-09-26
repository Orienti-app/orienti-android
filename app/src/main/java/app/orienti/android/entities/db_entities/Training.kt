package app.orienti.android.entities.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Training (
    @PrimaryKey
    val id: UUID,
    val name: String,
    val createdAt: Date,
    val updatedAt: Date
    )

