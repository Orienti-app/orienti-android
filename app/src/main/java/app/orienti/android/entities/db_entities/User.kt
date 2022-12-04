package app.orienti.android.entities.db_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: UUID,
    val name: String?
    )