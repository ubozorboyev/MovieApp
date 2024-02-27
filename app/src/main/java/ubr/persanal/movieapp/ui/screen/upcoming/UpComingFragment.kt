package ubr.persanal.movieapp.ui.screen.upcoming

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentUpcomingBinding
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.extentions.isNetworkAvailable
import ubr.persanal.movieapp.util.extentions.showSnack
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter
import ubr.persanal.movieapp.ui.adapter.PagingLoadStateAdapter
import ubr.persanal.movieapp.ui.screen.SharedViewModel
import ubr.persanal.movieapp.util.MediaType
import ubr.persanal.movieapp.util.ResourceUI

@AndroidEntryPoint
class UpComingFragment : Fragment(R.layout.fragment_upcoming), MoviesPagingAdapter.Callback {

    private lateinit var binding :FragmentUpcomingBinding

    private val viewModel by viewModels<UpComingViewModel>()

    private val sharedViewModel by activityViewModels<SharedViewModel>()



    private val adapter = MoviesPagingAdapter(this)

    private var currentPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       // binding.recyclerView.adapter = adapter


        binding.iconOffline.isVisible = !requireContext().isNetworkAvailable()




        initViews()

        setUpObservers()

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

    private fun setUpObservers(){



        lifecycleScope.launch {

            sharedViewModel.updatePagingData.collectLatest {


                if (it)  adapter.refresh()

            }

        }
        lifecycleScope.launch {

            viewModel.upComingListPager.collect {

                adapter.submitData(it)

                binding.swipeRefreshLayout.isRefreshing = false

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

                    //adapter.notifyItemChanged(currentPosition)

                    binding.progressBar.isVisible = false

                    sharedViewModel.updateUiPagingData(true)

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

    override fun saveToFavorite(dto: MoviePageItemDto, position:Int) {

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