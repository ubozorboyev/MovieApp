package ubr.persanal.movieapp.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.BottomNavigationFragmentBinding


@AndroidEntryPoint
class BottomNavigationFragment : Fragment() {

    private lateinit var binding: BottomNavigationFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomNavigationFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val navController =
            Navigation.findNavController(requireActivity(), R.id.navigation_bottom_host)


        binding.bottomNavigationView.setupWithNavController(navController)

        val navOptions: NavOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .setPopUpTo(
                navController.graph.startDestinationId,
                false,
                true
            )
            .build()


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            navController.navigate(item.itemId, null, navOptions)
            true
        }

    }

}