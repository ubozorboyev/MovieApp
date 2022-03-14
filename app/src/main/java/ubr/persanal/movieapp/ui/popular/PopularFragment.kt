package ubr.persanal.movieapp.ui.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.data.model.MovieItemData
import ubr.persanal.movieapp.data.model.PopularMovieResponse
import ubr.persanal.movieapp.databinding.FragmentPopularBinding
import ubr.persanal.movieapp.ui.adapter.MovieAdapter
import ubr.persanal.movieapp.util.DataState
import ubr.persanal.movieapp.util.toast


@AndroidEntryPoint
class PopularFragment : Fragment(), BaseInterface {

    private lateinit var binding: FragmentPopularBinding
    val viewModel by viewModels<PopularViewModel>()

    private val adapter = MovieAdapter(this)
    private  val TAG = "PopularFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        binding = FragmentPopularBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        Log.d(TAG, "onViewCreated: ")
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.clearList()
            viewModel.fetchData()
        }

        viewModel.dataList.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.ResponseData -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let {
                        adapter.setData(it.results)
                    }
                }
                is DataState.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.progressBar.visibility = View.INVISIBLE
                    it.message?.toast(requireContext())
                }
            }
        }

    }

    override fun movieItemClick(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt("MOVIE_ID", movieId)
        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_main_host)
        navController.navigate(R.id.aboutMovieFragment, bundle)
    }

    
    
    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        binding.recyclerView.adapter = null
        super.onDestroy()
    }

}