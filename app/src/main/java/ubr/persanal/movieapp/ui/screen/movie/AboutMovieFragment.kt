package ubr.persanal.movieapp.ui.screen.movie

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.AboutMovieFragmentBinding
import ubr.persanal.movieapp.domain.model.ActorItemDto
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.util.extentions.showSnack
import ubr.persanal.movieapp.ui.adapter.ActorsAdapter
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.movieapp.util.extentions.isNetworkAvailable
import java.lang.Exception

@AndroidEntryPoint
class AboutMovieFragment : Fragment(), ActorsAdapter.CallBack {


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

        if (requireContext().isNetworkAvailable().not()){
            binding.movieOverview.text = ContextCompat.getString(requireContext(),R.string.no_access_network)
        }


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
                  //  binding.progressBar.visibility
                }
                is ResourceUI.Resource -> {

                    Log.d("ACTORACTOR","${state.data}")
                    adapter.submitList(state.data?.cast)

                }
                is ResourceUI.Error -> {
                    state.error?.showSnack(binding.root)
                }
            }
        }
    }

    private fun setDetail(data: MovieDetailsDto) {
        try {

            binding.movieVote.text = data.vote_count.toString()

            binding.toolBar.title = data.title

            binding.movieOverview.text = data.overview

            binding.progressRating.setProgress((data.vote_average?:0.0) * 10, 100.0)

            if ((data.vote_average?.toInt()?:0) * 10 >= 70) {

                binding.progressRating.dotColor = ContextCompat.getColor(requireContext(),R.color.progress_green)
                binding.progressRating.progressColor = ContextCompat.getColor(requireContext(),R.color.progress_green)
                binding.progressRating.progressBackgroundColor = ContextCompat.getColor(requireContext(),R.color.progress_bg_green)
            }



        } catch (e: Exception) {
            e.printStackTrace()
        }

        Glide.with(requireContext()).load(BuildConfig.IMAGE_URL + data.backdrop_path)
            .into(binding.movieImage)

    }

    override fun actorItemClick(itemDto: ActorItemDto) {

        val bundle = Bundle()

        itemDto.id?.let { bundle.putInt("PERSON_ID", it) }

        findNavController().navigate(R.id.actorsFragment, bundle)
    }


}