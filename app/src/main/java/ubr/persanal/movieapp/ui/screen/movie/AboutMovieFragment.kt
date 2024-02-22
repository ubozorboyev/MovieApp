package ubr.persanal.movieapp.ui.screen.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.data.model.MovieDetailsResponse
import ubr.persanal.movieapp.databinding.AboutMovieFragmentBinding
import ubr.persanal.movieapp.ui.adapter.ActorsAdapter
import ubr.persanal.movieapp.util.ResourceUI
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
                is ResourceUI.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResourceUI.Resource -> {
                    it.data?.let { setDetail(it) }
                    binding.progressBar.visibility = View.INVISIBLE
                }
                is ResourceUI.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.error?.showSnack(binding.root)
                }
            }
        }

        viewModel.movieActors.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResourceUI.Loading -> {

                }
                is ResourceUI.Resource -> {
                    state.data?.cast?.let {
                        adapter.setData(it)
                    }
                }
                is ResourceUI.Error -> {
                    state.error?.showSnack(binding.root)
                }
            }
        }
    }

    private fun setDetail(data: MovieDetailsResponse) {
        try {
            binding.movieVote.text = data.vote_count.toString()
            binding.progressRating.setProgress(data.vote_average!! * 10, 100.0)
            if (data.vote_average.toInt() * 10 >= 70) {
                binding.progressRating.dotColor = Color.parseColor("#09A193")
                binding.progressRating.progressColor = Color.parseColor("#09A193")
                binding.progressRating.progressBackgroundColor = Color.parseColor("#8009A193")
            }
            binding.toolBar.title = data.title
            binding.movieOverview.text = data.overview

        } catch (e: Exception) {
            e.printStackTrace()
        }
        Glide.with(requireContext()).load(BuildConfig.IMAGE_URL + data.backdrop_path)
            .into(binding.movieImage)

    }

    override fun actorItemClick(actorId: Int) {
        val bundle = Bundle()
        bundle.putInt("PERSON_ID", actorId)
        findNavController().navigate(R.id.actorsFragment, bundle)
    }


}