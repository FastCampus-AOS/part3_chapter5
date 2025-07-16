package fastcampus.aos.part3.part3_chapter5

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fastcampus.aos.part3.part3_chapter5.model.ListItem
import fastcampus.aos.part3.part3_chapter5.repository.SearchRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _listLiveData = MutableLiveData<List<ListItem>>()
    val listLiveData: LiveData<List<ListItem>> get() =  _listLiveData

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private var disposable : CompositeDisposable? = CompositeDisposable()

    fun search(query: String) {

        Log.d("hyunsu SearchVM", "search() 호출됨: $query")
        disposable?.add(searchRepository.search(query)
            .doOnSubscribe { _showLoading.value = true }
            .doOnTerminate { _showLoading.value = false }
            .subscribe({ list ->

                Log.d("hyunsu SearchVM", "결과 개수: ${list.size}")
                _listLiveData.value = list
            }, { error ->
                error.printStackTrace()
                Log.e("hyunsu SearchVM", "에러 발생: ${error.message}")
                _listLiveData.value = emptyList()
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }

    class SearchViewModelFactory(
        private val searchRepository: SearchRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(searchRepository) as T
        }
    }
}