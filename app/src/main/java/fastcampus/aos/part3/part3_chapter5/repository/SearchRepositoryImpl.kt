package fastcampus.aos.part3.part3_chapter5.repository

import android.net.Uri
import android.util.Log
import fastcampus.aos.part3.part3_chapter5.SearchService
import fastcampus.aos.part3.part3_chapter5.model.ListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {
    override fun search(query: String): Observable<List<ListItem>> {
        val encodedQuery = Uri.encode(query.trim())
        return searchService.searchImage(encodedQuery)
            .zipWith(searchService.searchVideo(encodedQuery)) { imageResult, videoResult ->
                mutableListOf<ListItem>().apply {
                    addAll(imageResult.documents)
                    addAll(videoResult.documents)
                }.sortedBy { it.dateTime }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}