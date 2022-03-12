package ubr.persanal.movieapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.data.model.MovieItemData
import ubr.persanal.movieapp.data.model.PopularMovieResponse
import ubr.persanal.movieapp.databinding.FragmentPopularBinding
import ubr.persanal.movieapp.ui.adapter.MovieAdapter
import ubr.persanal.movieapp.util.DataState
import ubr.persanal.movieapp.util.toast


@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    val viewModel by viewModels<PopularViewModel>()

    private val adapter = MovieAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        viewModel.dataList.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.ResponseData -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let {
                        adapter.setData(it.results)
                    }
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.message?.toast(requireContext())
                }
            }
        }

    }

}