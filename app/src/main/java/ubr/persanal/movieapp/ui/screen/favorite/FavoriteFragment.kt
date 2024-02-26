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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentFavoriteBinding
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.extentions.showSnack
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter
import ubr.persanal.movieapp.ui.screen.SharedViewModel
import ubr.persanal.movieapp.util.MediaType
import ubr.persanal.movieapp.util.ResourceUI

@AndroidEntryPoint
class FavoriteFragment : Fragment(),MoviesPagingAdapter.Callback {


    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel by viewModels<FavoriteViewModel>()

    private val sharedViewModel by activityViewModels<SharedViewModel>()


    private val adapter = MoviesPagingAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()

        }

        observerParameters()



    }

    private fun observerParameters(){


        lifecycleScope.launch {

            sharedViewModel.updatePagingData.collectLatest {

                Log.d("TAG_SHARED_DATA", "FavoriteFragment: $it")

                if (it) adapter.refresh()

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
                        sharedViewModel.updateUiPagingData(true)
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