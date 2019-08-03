package com.ucsdextandroid2.android2final.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ucsdextandroid2.android2final.RecentPlayerData

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: RecentPlayerData)

    @Delete
    fun deleteNote(note: RecentPlayerData)


    @Query("SELECT * FROM RecentPlayers ORDER BY datetime DESC")
    fun getAllNotesLiveData(): LiveData<List<RecentPlayerData>>

}