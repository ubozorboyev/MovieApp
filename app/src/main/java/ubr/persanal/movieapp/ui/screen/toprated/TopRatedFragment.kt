package ubr.persanal.movieapp.ui.screen.toprated

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
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentTopRatedBinding
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.extentions.isNetworkAvailable
import ubr.persanal.movieapp.util.extentions.showSnack
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter
import ubr.persanal.movieapp.ui.screen.SharedViewModel
import ubr.persanal.movieapp.util.MediaType
import ubr.persanal.movieapp.util.ResourceUI

@AndroidEntryPoint
class TopRatedFragment : Fragment(), MoviesPagingAdapter.Callback {

    private lateinit var binding: FragmentTopRatedBinding

    private val viewModel by viewModels<TopRatedViewModel>()

    private val sharedViewModel by activityViewModels<SharedViewModel>()

    private val adapter = MoviesPagingAdapter(this)
    private var currentPosition = -1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()

        binding.iconOffline.isVisible = !requireContext().isNetworkAvailable()


//        viewModel.dataList.observe(viewLifecycleOwner) {
//
//            when (it) {
//                is ResourceUI.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//                is ResourceUI.Resource -> {
//                    binding.swipeRefreshLayout.isRefreshing = false
//                    binding.progressBar.visibility = View.INVISIBLE
//                    it.data?.let {
//                        adapter.setData(it.results)
//                    }
//                }
//                is ResourceUI.Error -> {
//                    binding.swipeRefreshLayout.isRefreshing = false
//                    binding.progressBar.visibility = View.INVISIBLE
//                    it.error?.toast(requireContext())
//                }
//            }
//        }
    }

    private fun initViews() {


        binding.recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerView.layoutManager = layoutManager

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }


        setUpObservers()



    }

    private fun setUpObservers(){


        lifecycleScope.launch {

            sharedViewModel.updatePagingData.collectLatest {


                if (it) adapter.refresh()

            }

        }
        lifecycleScope.launch {

            viewModel.topRatedListPager.collect {

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

                    sharedViewModel.updateUiPagingData(true)

                    binding.progressBar.isVisible = false

                }

            }
        }
    }




    override fun onDestroy() {
        binding.recyclerView.adapter = null
        super.onDestroy()
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