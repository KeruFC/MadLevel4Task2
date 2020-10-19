package com.example.madlevel4task2

import androidx.room.*


@Entity(tableName = "match_table")
data class Game(
    @ColumnInfo(name = "player_move")
    var playerMove: Int,

    @ColumnInfo(name = "computer_move")
    var computerMove: Int,

    @ColumnInfo(name = "result")
    var result: String,

    @ColumnInfo(name = "date")
    var matchDate: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)