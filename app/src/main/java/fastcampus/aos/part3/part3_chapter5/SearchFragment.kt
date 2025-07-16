package fastcampus.aos.part3.part3_chapter5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import fastcampus.aos.part3.part3_chapter5.databinding.FragmentSearchBinding
import fastcampus.aos.part3.part3_chapter5.list.ItemHandler
import fastcampus.aos.part3.part3_chapter5.list.ListAdapter
import fastcampus.aos.part3.part3_chapter5.model.ListItem
import fastcampus.aos.part3.part3_chapter5.repository.SearchRepositoryImpl

class SearchFragment : Fragment() {

    private val viewModel : SearchViewModel by viewModels {
        SearchViewModel.SearchViewModelFactory(SearchRepositoryImpl(RetrofitManager.searchService))
    }
    private var binding: FragmentSearchBinding? = null
    private val adapter by lazy { ListAdapter(Handler(viewModel)) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            recyclerView.adapter = adapter
        }
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun searchKeyword(text: String) {
        val trimmed = text.trim()
        if (trimmed.isNotEmpty()) viewModel.search(trimmed)
    }

    private fun observeViewModel() {
        viewModel.listLiveData.observe(viewLifecycleOwner) {
            Log.d("hyunsu SearchFragment", "LiveData 변화 감지, size = ${it.size}")
            binding?.apply {
                if (it.isEmpty()) {
                    emptyTextView.isVisible = true
                    recyclerView.isVisible = false
                } else {
                    emptyTextView.isVisible = false
                    recyclerView.isVisible = true
                }
            }

            adapter.submitList(it.sortedBy { it.dateTime })
        }
    }

    class Handler(private val viewModel: SearchViewModel): ItemHandler {
        override fun onClickFavorite(item: ListItem) {
            viewModel.toggleFavorite(item)
        }
    }
}