package com.rym.ialbum.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey var id : Int?,
    var albumId: Int?,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?): Serializable {

    constructor() : this(
        null, null, null, null, null
    )
}
