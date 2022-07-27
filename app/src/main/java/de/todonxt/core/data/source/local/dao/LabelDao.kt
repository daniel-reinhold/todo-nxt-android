package de.todonxt.core.data.source.local.dao

import androidx.room.*
import de.todonxt.core.data.source.local.entities.LabelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LabelDao {

    @Query("SELECT * from labels")
    fun getLabels(): Flow<List<LabelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdateLabel(label: LabelEntity): Long

    @Delete
    suspend fun deleteLabel(label: LabelEntity): Int

}