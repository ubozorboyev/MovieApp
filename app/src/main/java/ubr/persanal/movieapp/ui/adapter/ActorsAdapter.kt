package ubr.persanal.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.common.BaseInterface
import ubr.persanal.movieapp.data.model.ActorItem
import ubr.persanal.movieapp.databinding.ItemActorBinding

class ActorsAdapter(private val baseInterface: BaseInterface) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolderActor>() {

    private val dataList = arrayListOf<ActorItem>()

    inner class ViewHolderActor(private val itemBinding: ItemActorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: ActorItem) {
            itemBinding.apply {
                actorName.text = data.name
                actorCharacter.text = data.character?.replace("/", "\n")

                Glide.with(this.root)
                    .load(BuildConfig.IMAGE_URL + data.profile_path)
                    .error(R.drawable.avatar)
                    .placeholder(R.drawable.avatar)
                    .into(actorImage)

                itemBinding.root.setOnClickListener {
                   // baseInterface.actorItemClick(data.id)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {

        val itemBinding =
            ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderActor(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(ls: List<ActorItem>) {
        dataList.clear()
        dataList.addAll(ls)
        notifyDataSetChanged()
    }


}