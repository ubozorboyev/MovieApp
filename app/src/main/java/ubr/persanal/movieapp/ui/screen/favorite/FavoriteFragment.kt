package ubr.persanal.movieapp.ui.screen.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.map
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentFavoriteBinding
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.extentions.showSnack
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter
import ubr.persanal.movieapp.ui.adapter.PagingLoadStateAdapter
import ubr.persanal.movieapp.ui.screen.SharedViewModel
import ubr.persanal.movieapp.util.MediaType
import ubr.persanal.movieapp.util.ResourceUI

@AndroidEntryPoint
class FavoriteFragment : Fragment(),MoviesPagingAdapter.Callback {


    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel by viewModels<FavoriteViewModel>()

    private val sharedViewModel by activityViewModels<SharedViewModel>()


    private val adapter = MoviesPagingAdapter(this)

    private var currentPosition =  -1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        initViews()

        observerParameters()

    }

    private fun initViews(){


        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()

        }

        val headerStateAdapter = PagingLoadStateAdapter {
            adapter.retry()
        }

        val footerStateAdapter = PagingLoadStateAdapter {
            adapter.retry()
        }

        adapter.addLoadStateListener { loadStates ->
            headerStateAdapter.loadState = loadStates.refresh
            footerStateAdapter.loadState = loadStates.append
        }

        val concatAdapter = ConcatAdapter(headerStateAdapter, adapter, footerStateAdapter)



        binding.recyclerView.adapter = concatAdapter
    }

    private fun observerParameters(){


        lifecycleScope.launch {

            sharedViewModel.updatePagingData.collectLatest {

                //if (it) adapter.refresh() updates list

            }

        }


        lifecycleScope.launch {

            viewModel.favoriteListPager.collect {

                adapter.submitData(it)

                binding.swipeRefreshLayout.isRefreshing = false

            }
        }


        lifecycleScope.launch {

            viewModel.successFavorite.observe(viewLifecycleOwner){

                when(it){
                    is ResourceUI.Loading ->{
                        binding.progressBar.isVisible = true
                    }
                    is ResourceUI.Error ->{
                        binding.progressBar.isVisible = false

                        it.error?.showSnack(binding.root)

                    }
                    is ResourceUI.Resource ->{
                        adapter.notifyItemRemoved(currentPosition)
                       // sharedViewModel.updateUiPagingData(true)
                        binding.progressBar.isVisible = false

                    }

                }
            }
        }

    }


    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    override fun selectMovieItem(dto: MoviePageItemDto) {
        val bundle = Bundle()

        dto.id?.let { bundle.putLong("MOVIE_ID", it) }

        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_main_host)

        navController.navigate(R.id.aboutMovieFragment, bundle)
    }

    override fun saveToFavorite(dto: MoviePageItemDto,  position:Int) {

        currentPosition = position

        dto.id?.let {

            val requestDto = FavoriteRequestDto(
                favorite = false,
                mediaId = it.toInt(),
                mediaType = MediaType.movie.name
            )

            viewModel.setFavoriteMovie(requestDto, dto)
        }



    }

}