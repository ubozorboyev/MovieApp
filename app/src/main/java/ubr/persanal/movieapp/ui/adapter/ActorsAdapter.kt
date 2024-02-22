package ubr.persanal.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.databinding.ItemActorBinding
import ubr.persanal.movieapp.domain.model.ActorItemDto

class ActorsAdapter(private val listener: CallBack) :
    ListAdapter<ActorItemDto,ActorsAdapter.ViewHolderActor>(diffUtil) {


    inner class ViewHolderActor(private val itemBinding: ItemActorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemDto: ActorItemDto) {
            itemBinding.apply {
                actorName.text = itemDto.name
                actorCharacter.text = itemDto.character?.replace("/", "\n")

                Glide.with(this.root)
                    .load(BuildConfig.IMAGE_URL + itemDto.profile_path)
                    .error(R.drawable.avatar)
                    .placeholder(R.drawable.avatar)
                    .into(actorImage)

                itemBinding.root.setOnClickListener {

                    listener.actorItemClick(itemDto)
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
        holder.bind(getItem(position))

    }


    object diffUtil : DiffUtil.ItemCallback<ActorItemDto>() {

        override fun areItemsTheSame(
            oldItem: ActorItemDto,
            newItem: ActorItemDto
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ActorItemDto,
            newItem: ActorItemDto
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }


    interface CallBack{
        fun actorItemClick(itemDto: ActorItemDto)
    }


}