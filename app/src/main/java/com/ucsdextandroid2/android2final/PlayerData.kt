package com.ucsdextandroid2.android2final

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerData(
    @field:SerializedName("kills") var kills: Int,
    @field:SerializedName("assists") var assists: Int,
    @field:SerializedName("wins") var wins: Int,
    @field:SerializedName("losses") var losses: Int
): Parcelable