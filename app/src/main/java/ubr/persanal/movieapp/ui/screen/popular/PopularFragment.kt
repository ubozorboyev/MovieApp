package ubr.persanal.movieapp.ui.screen.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentPopularBinding
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter
import ubr.persanal.movieapp.util.BitmapConverter
import ubr.persanal.movieapp.util.MediaType
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.movieapp.util.showSnack


@AndroidEntryPoint
class PopularFragment : Fragment(), MoviesPagingAdapter.Callback {

    private lateinit var binding: FragmentPopularBinding
    private val viewModel by viewModels<PopularViewModel>()

    private val adapter = MoviesPagingAdapter(this)

    private  val TAG = "PopularFragment"

    private var currentPosition = -1


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
                    binding.progressBar.isVisible = false

                    adapter.notifyItemChanged(currentPosition)

                }

            }
        }

    }


    override fun selectMovieItem(dto: MoviePageItemDto) {
        val bundle = Bundle()
        dto.id?.let { bundle.putLong("MOVIE_ID", it) }

        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_main_host)
        navController.navigate(R.id.aboutMovieFragment, bundle)
    }

    override fun saveToFavorite(dto: MoviePageItemDto, position:Int) {

        lifecycleScope.launch {

            dto.id?.let {

                currentPosition = position

                val requestDto = FavoriteRequestDto(
                    favorite = (dto.is_favorote ?: false).not(),
                    mediaId = dto.id.toInt(),
                    mediaType = MediaType.movie.name,
                )

                viewModel.setFavoriteMovie(requestDto, dto)
            }


        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null

    }

}