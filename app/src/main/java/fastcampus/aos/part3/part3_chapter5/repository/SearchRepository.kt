package fastcampus.aos.part3.part3_chapter5.repository

import fastcampus.aos.part3.part3_chapter5.model.ListItem
import io.reactivex.rxjava3.core.Observable

interface SearchRepository {

    fun search(query: String): Observable<List<ListItem>>
}