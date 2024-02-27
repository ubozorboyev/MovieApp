package ubr.persanal.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ubr.persanal.movieapp.databinding.ItemLoadingPaginBinding

class PagingLoadStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<PagingLoadStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): NetworkStateItemViewHolder {


        val inflater = LayoutInflater.from(parent.context)

       return NetworkStateItemViewHolder(
            ItemLoadingPaginBinding.inflate(inflater, parent,false),
            retryCallback
        )

    }



    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class NetworkStateItemViewHolder(
        private val binding: ItemLoadingPaginBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {



        fun bind(loadState: LoadState) {
            binding.buttonRetry.setOnClickListener { retryCallback() }

            with(binding) {
                progressbar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState is LoadState.Error
                textViewError.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                textViewError.text = (loadState as? LoadState.Error)?.error?.message

            }
        }
    }
}
