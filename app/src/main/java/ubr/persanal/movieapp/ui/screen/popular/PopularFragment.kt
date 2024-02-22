package ubr.persanal.movieapp.ui.screen.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.databinding.FragmentPopularBinding
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.movieapp.util.toast


@AndroidEntryPoint
class PopularFragment : Fragment(), MoviesPagingAdapter.Callback {

    private lateinit var binding: FragmentPopularBinding
    private val viewModel by viewModels<PopularViewModel>()

    private val adapter = MoviesPagingAdapter(this)
    private  val TAG = "PopularFragment"


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

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()

        }

        lifecycleScope.launch {

            viewModel.popularListPager.collect {

                adapter.submitData(it)

                binding.swipeRefreshLayout.isRefreshing = false

            }
        }

        //adapter.withLoadStateFooter(LoadStateAdapter())

        lifecycleScope.launch {

            adapter.loadStateFlow.collectLatest {


            }
        }


    }






    override fun selectMovieItem(dto: MoviePageItemDto) {
        val bundle = Bundle()
        dto.id?.let { bundle.putInt("MOVIE_ID", it) }

        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_main_host)
        navController.navigate(R.id.aboutMovieFragment, bundle)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null

    }

}