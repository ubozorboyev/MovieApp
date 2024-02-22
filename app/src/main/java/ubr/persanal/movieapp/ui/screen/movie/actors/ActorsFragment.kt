package ubr.persanal.movieapp.ui.screen.movie.actors

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
import ubr.persanal.movieapp.data.model.ActorDetailResponse
import ubr.persanal.movieapp.databinding.FragmentActorsBinding
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.domain.model.MovieByActorItemDto
import ubr.persanal.movieapp.ui.adapter.MovieByActorAdapter
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.movieapp.util.getDateFrom
import ubr.persanal.movieapp.util.showSnack

@AndroidEntryPoint
class ActorsFragment : Fragment(), MovieByActorAdapter.CallBack {

    private lateinit var binding: FragmentActorsBinding

    private val viewModel by viewModels<ActorsViewModel>()

    private val adapter = MovieByActorAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActorsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()
        stateObserve()
    }

    private fun initViews() {
        binding.recyclerViewMovies.adapter = adapter
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun stateObserve() {

        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceUI.Loading -> {}

                is ResourceUI.Resource -> {

                    adapter.submitList(it.data?.cast)

                }
                is ResourceUI.Error -> {
                    it.error?.showSnack(binding.root)
                }

                else -> {

                }
            }
        }

        viewModel.detailActor.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceUI.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResourceUI.Resource -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let { updateDetail(it) }
                }
                is ResourceUI.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.error?.showSnack(binding.root)
                }

                else -> {}
            }
        }

    }

    private fun updateDetail(data: ActorDetailDto) {

        binding.toolBar.title = data.name
        binding.actorBiography.text = data.biography
        binding.birthday.text = data.birthday?.getDateFrom()
        binding.knownFor.text = data.known_for_department
        binding.placeOfBirth.text = data.place_of_birth?.trimStart()

        Glide.with(requireActivity()).load(BuildConfig.IMAGE_URL + data.profile_path)
            .placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)
            .into(binding.actorImage)

    }


    override fun selectMovieItem(itemDto: MovieByActorItemDto) {

        val bundle = Bundle()

        itemDto.id?.let { bundle.putInt("MOVIE_ID", it) }

        findNavController().navigate(R.id.action_actorsFragment_to_aboutMovieFragment, bundle)
    }


}