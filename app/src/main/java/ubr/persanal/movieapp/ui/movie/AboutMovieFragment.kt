package ubr.persanal.movieapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.common.Common
import ubr.persanal.movieapp.data.model.MovieDetailsResponse
import ubr.persanal.movieapp.databinding.AboutMovieFragmentBinding
import ubr.persanal.movieapp.ui.adapter.ActorsAdapter
import ubr.persanal.movieapp.util.DataState
import ubr.persanal.movieapp.util.showSnack
import java.lang.Exception

@AndroidEntryPoint
class AboutMovieFragment : Fragment(), BaseInterface {


    private lateinit var binding: AboutMovieFragmentBinding
    private val viewModel by viewModels<AboutMovieViewModel>()

    private val adapter = ActorsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AboutMovieFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()

    }

    private fun initViews() {

        binding.recyclerViewCasts.adapter = adapter
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.movieDetail.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.ResponseData -> {
                    it.data?.let { setDetail(it) }
                    binding.progressBar.visibility = View.INVISIBLE
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.message?.showSnack(binding.root)
                }
            }
        }

        viewModel.movieActors.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Loading -> {

                }
                is DataState.ResponseData -> {
                    state.data?.cast?.let {
                        adapter.setData(it)
                    }
                }
                is DataState.Error -> {
                    state.message?.showSnack(binding.root)
                }
            }
        }
    }

    private fun setDetail(data: MovieDetailsResponse) {
        try {
            binding.toolBar.title = data.title
            binding.movieOverview.text = data.overview

        } catch (e: Exception) {
            e.printStackTrace()
        }
        Glide.with(requireContext()).load(Common.IMAGE_URL + data.backdrop_path)
            .into(binding.movieImage)

    }

    override fun actorItemClick(actorId: Int) {
        val bundle = Bundle()
        bundle.putInt("PERSON_ID", actorId)
        findNavController().navigate(R.id.actorsFragment, bundle)
    }


}