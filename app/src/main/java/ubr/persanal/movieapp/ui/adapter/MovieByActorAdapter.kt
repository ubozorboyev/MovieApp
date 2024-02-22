package ubr.persanal.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.data.model.MovieByActorItem
import ubr.persanal.movieapp.databinding.ItemMovieActorBinding

class MovieByActorAdapter(private val baseInterface: BaseInterface) :
    RecyclerView.Adapter<MovieByActorAdapter.ViewHolderMovieActor>() {

    private val dataList = arrayListOf<MovieByActorItem>()

    inner class ViewHolderMovieActor(private val itemBinding: ItemMovieActorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: MovieByActorItem) {
            itemBinding.movieName.text = data.title
            Glide.with(itemView).load(BuildConfig.IMAGE_URL + data.poster_path)
                .placeholder(R.drawable.please_holder)
                .into(itemBinding.movieImage)
            itemBinding.root.setOnClickListener {
                //baseInterface.movieItemClick(data.id)
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
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(ls: List<MovieByActorItem>) {
        dataList.clear()
        dataList.addAll(ls)
        notifyDataSetChanged()
    }

}