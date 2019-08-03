package com.ucsdextandroid2.android2final

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "RecentPlayers")
data class RecentPlayerData(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "kills") var kills: Int,
    @ColumnInfo(name = "assists") var assists: Int,
    @ColumnInfo(name = "wins") var wins: Int,
    @ColumnInfo(name = "losses") var losses: Int,
    @PrimaryKey @ColumnInfo(name = "datetime") val datetime: Long = System.currentTimeMillis()
): Parcelable