package fastcampus.aos.part3.part3_chapter5.model

import java.util.Date

sealed class ListItem {
    abstract val thumbnailUrl: String
    abstract val dateTime: Date
    abstract var isFavorite: Boolean
}