package fastcampus.aos.part3.part3_chapter5

import fastcampus.aos.part3.part3_chapter5.model.ImageListResponse
import fastcampus.aos.part3.part3_chapter5.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {

    @Headers("Authorization: KakaoAK a0dda720719d1a77f7271e39f20d88e4")
    @GET("image")
    fun searchImage(
        @Query("query") query: String
    ): Observable<ImageListResponse>

    @Headers("Authorization: KakaoAK a0dda720719d1a77f7271e39f20d88e4")
    fun searchVideo(
        @Query("query") query: String
    ): Observable<VideoListResponse>
}