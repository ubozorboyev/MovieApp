package ubr.persanal.movieapp.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.bumptech.glide.Glide
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.common.Common
import ubr.persanal.movieapp.data.model.MovieItemData
import ubr.persanal.movieapp.databinding.ItemMovieBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MovieAdapter(val baseInterface: BaseInterface) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val dataList = arrayListOf<MovieItemData>()

    inner class ViewHolder(private val itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: MovieItemData) {

            itemBinding.apply {
                movieName.text = data.title
                movieOverview.text = data.overview
                realiseDate.text = data.release_date.replace('-','.')
                progressRating.setProgress(data.vote_average * 10, 100.0)
                progressRating.setProgressTextAdapter(CircularProgressIndicator.ProgressTextAdapter {
                    return@ProgressTextAdapter "${it.toInt()} %"
                })

            }

            Glide.with(itemBinding.root).load(Common.IMAGE_URL + data.poster_path)
                .into(itemBinding.itemImage)

            itemBinding.root.setOnClickListener {
                baseInterface.movieItemClick(data.id)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list: List<MovieItemData>) {
        dataList.addAll(list)
        notifyDataSetChanged()
    }

}