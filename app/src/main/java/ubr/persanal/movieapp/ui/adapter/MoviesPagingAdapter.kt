package ubr.persanal.movieapp.ui.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.ItemMovieBinding
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.BitmapConverter
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

            val context = itemBinding.root.context

            if ((itemDto.vote_average?.toInt()?: 0) * 10 >= 70) {

                itemBinding.progressRating.dotColor = ContextCompat.getColor(context,R.color.progress_green)

                itemBinding.progressRating.progressColor = ContextCompat.getColor(context,R.color.progress_green)
                itemBinding.progressRating.progressBackgroundColor = ContextCompat.getColor(context,R.color.progress_bg_green)
            } else {
                itemBinding.progressRating.dotColor = ContextCompat.getColor(context,R.color.progress_yellow)
                itemBinding.progressRating.progressColor = ContextCompat.getColor(context,R.color.progress_yellow)
                itemBinding.progressRating.progressBackgroundColor = ContextCompat.getColor(context,R.color.progress_bg_yellow)
            }

            val resImage = if (itemDto.is_favorote == true) R.drawable.ic_favorite else R.drawable.ic_favorite_border


            itemBinding.favoriteButton.setImageResource(resImage)

            var bitmap:Bitmap? = null

                Glide.with(itemBinding.root)
                .asBitmap()
                .load(BuildConfig.IMAGE_URL + itemDto.poster_path)
                .into(object :CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bitmap = resource

                        itemBinding.itemImage.setImageBitmap(resource)

                    }

                    override fun onLoadCleared(placeholder: Drawable?) { }

                })


            itemBinding.movieItem.setOnClickListener {
                listener.selectMovieItem(itemDto)
            }

            itemBinding.favoriteButton.setOnClickListener {

                if (itemDto.is_favorote == false && bitmap != null){

                    itemDto.imageString = BitmapConverter.converterBitmapToString(bitmap!!)
                }

                listener.saveToFavorite(itemDto, bindingAdapterPosition)

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

        fun saveToFavorite(dto: MoviePageItemDto, position: Int)
    }


}