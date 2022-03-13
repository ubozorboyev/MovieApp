package ubr.persanal.movieapp.ui.movie.actors

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
import ubr.persanal.movieapp.data.model.PersonDetailResponse
import ubr.persanal.movieapp.databinding.FragmentActorsBinding
import ubr.persanal.movieapp.ui.adapter.MovieByActorAdapter
import ubr.persanal.movieapp.util.DataState
import ubr.persanal.movieapp.util.showSnack

@AndroidEntryPoint
class ActorsFragment : Fragment(), BaseInterface {

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

        viewModel.dataList.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {}
                is DataState.ResponseData -> {

                    it.data?.cast?.let {
                        adapter.setData(it)
                    }
                }
                is DataState.Error -> {
                    it.message?.showSnack(binding.root)
                }
            }
        }

        viewModel.detailPerson.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.ResponseData -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let { updateDetail(it) }
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.message?.showSnack(binding.root)
                }
            }
        }

    }

    private fun updateDetail(data: PersonDetailResponse) {

        binding.toolBar.title = data.name
        binding.actorBiography.text = data.biography
        binding.birthday.text = data.birthday
        binding.knownFor.text = data.known_for_department
        binding.placeOfBirth.text = data.place_of_birth

        Glide.with(requireActivity()).load(Common.IMAGE_URL + data.profile_path)
            .placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)
            .into(binding.actorImage)

    }

    override fun movieItemClick(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt("MOVIE_ID", movieId)
        findNavController().navigate(R.id.action_actorsFragment_to_aboutMovieFragment, bundle)
    }


}