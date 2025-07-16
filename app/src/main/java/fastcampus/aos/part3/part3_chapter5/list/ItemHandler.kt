package fastcampus.aos.part3.part3_chapter5.list

import fastcampus.aos.part3.part3_chapter5.model.ListItem

interface ItemHandler {

    fun onClickFavorite(item: ListItem)
}