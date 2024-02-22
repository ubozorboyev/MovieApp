package ubr.persanal.movieapp.ui.screen.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentUpcomingBinding
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter

@AndroidEntryPoint
class UpComingFragment : Fragment(), MoviesPagingAdapter.Callback {

    private lateinit var binding: FragmentUpcomingBinding

    private val viewModel by viewModels<UpComingViewModel>()

    private val adapter = MoviesPagingAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()

        }

        lifecycleScope.launchWhenCreated {

            viewModel.upComingListPager.collect {

                adapter.submitData(it)

                binding.swipeRefreshLayout.isRefreshing = false

            }
        }

    }


    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    override fun selectMovieItem(dto: MoviePageItemDto) {
        val bundle = Bundle()

        dto.id?.let { bundle.putInt("MOVIE_ID", it) }

        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_main_host)

        navController.navigate(R.id.aboutMovieFragment, bundle)
    }


}