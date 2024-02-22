package ubr.persanal.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.ItemMovieActorBinding
import ubr.persanal.movieapp.domain.model.MovieByActorItemDto

class MovieByActorAdapter(private val listener: CallBack) :
    ListAdapter<MovieByActorItemDto,MovieByActorAdapter.ViewHolderMovieActor>(diffUtill) {


    inner class ViewHolderMovieActor(private val itemBinding: ItemMovieActorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemDto: MovieByActorItemDto) {

            itemBinding.movieName.text = itemDto.title

            Glide.with(itemView).load(BuildConfig.IMAGE_URL + itemDto.poster_path)
                .placeholder(R.drawable.please_holder)
                .into(itemBinding.movieImage)

            itemBinding.root.setOnClickListener {
                listener.selectMovieItem(itemDto)
            }
            itemBinding.cardView.setBackgroundResource(R.drawable.item_movie_card_bg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovieActor {
        val itemBinding =
            ItemMovieActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderMovieActor(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderMovieActor, position: Int) {
        holder.bind(getItem(position))
    }


    object diffUtill: DiffUtil.ItemCallback<MovieByActorItemDto>(){
        override fun areItemsTheSame(oldItem: MovieByActorItemDto, newItem: MovieByActorItemDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieByActorItemDto, newItem: MovieByActorItemDto): Boolean {
            return oldItem.id == newItem.id
        }

    }

    interface CallBack{

        fun selectMovieItem(itemDto: MovieByActorItemDto)
    }


}