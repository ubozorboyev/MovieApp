package ubr.persanal.movieapp.ui.screen.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.FragmentTopRatedBinding
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.ui.adapter.MoviesPagingAdapter

@AndroidEntryPoint
class TopRatedFragment : Fragment(), MoviesPagingAdapter.Callback {

    private lateinit var binding: FragmentTopRatedBinding
    val viewModel by viewModels<TopRatedViewModel>()
    private val adapter = MoviesPagingAdapter(this)

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

        lifecycleScope.launchWhenCreated {

            viewModel.topRatedListPager.collect {

                adapter.submitData(it)

                binding.swipeRefreshLayout.isRefreshing = false

            }
        }


    }




    override fun onDestroy() {
        binding.recyclerView.adapter = null
        super.onDestroy()
    }

    override fun selectMovieItem(dto: MoviePageItemDto) {
        val bundle = Bundle()
        dto.id?.let { bundle.putInt("MOVIE_ID", it) }

        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_main_host)
        navController.navigate(R.id.aboutMovieFragment, bundle)
    }

}