package ubr.persanal.movieapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.bumptech.glide.Glide
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.databinding.ItemMovieBinding
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.getDateFrom

class MoviesPagingAdapter(private val listener:Callback) : PagingDataAdapter<MoviePageItemDto,MoviesPagingAdapter.ViewHolder>(diffUtil) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPagingAdapter.ViewHolder {

        val itemBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }


    inner class ViewHolder(private val itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemDto: MoviePageItemDto) {

            itemBinding.apply {

                movieName.text = itemDto.title

                movieOverview.text = itemDto.overview

                realiseDate.text = itemDto.release_date?.getDateFrom()

                progressRating.setProgress((itemDto.vote_average?:0.0) * 10, 100.0)

                progressRating.setProgressTextAdapter(CircularProgressIndicator.ProgressTextAdapter {
                    return@ProgressTextAdapter "${it.toInt()} %"
                })
            }

            if ((itemDto.vote_average?.toInt()?: 0) * 10 >= 70) {
                itemBinding.progressRating.dotColor = Color.parseColor("#09A193")
                itemBinding.progressRating.progressColor = Color.parseColor("#09A193")
                itemBinding.progressRating.progressBackgroundColor = Color.parseColor("#8009A193")
            } else {
                itemBinding.progressRating.dotColor = Color.parseColor("#FFC001")
                itemBinding.progressRating.progressColor = Color.parseColor("#FFC001")
                itemBinding.progressRating.progressBackgroundColor = Color.parseColor("#80FFC001")
            }

            Glide.with(itemBinding.root).load(BuildConfig.IMAGE_URL + itemDto.poster_path)
                .into(itemBinding.itemImage)

            itemBinding.root.setOnClickListener {
                listener.selectMovieItem(itemDto)
                //baseInterface.movieItemClick(data.id)
            }
        }
    }





    object diffUtil : DiffUtil.ItemCallback<MoviePageItemDto>() {

        override fun areItemsTheSame(
            oldItem: MoviePageItemDto,
            newItem: MoviePageItemDto
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MoviePageItemDto,
            newItem: MoviePageItemDto
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface Callback {
        fun selectMovieItem(dto: MoviePageItemDto)
    }


}