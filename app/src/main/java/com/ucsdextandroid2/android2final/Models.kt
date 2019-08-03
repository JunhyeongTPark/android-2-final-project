package com.ucsdextandroid2.android2final

import com.google.gson.annotations.SerializedName

class ResponseData(
    @SerializedName("data") val data: ResponseAttributes?
)
class ResponseAttributes(
    @SerializedName("attributes") val attributes: Attributes?
)
class Attributes (
    @SerializedName("gameModeStats") val gameModeStats: GameModeStats?
)
class GameModeStats(
    @SerializedName("solo") val solo: PlayerData?
)
