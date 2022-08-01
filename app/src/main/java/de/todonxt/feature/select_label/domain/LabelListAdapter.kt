package de.todonxt.feature.select_label.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.core.data.source.local.entities.LabelEntity
import de.todonxt.core.util.setOnClickListener
import de.todonxt.databinding.RviLabelBinding
import de.todonxt.databinding.RviLabelSelectedBinding
import de.todonxt.feature.select_label.data.LabelDto

private const val VIEW_TYPE_NORMAL = 0
private const val VIEW_TYPE_SELECTED = 1

class LabelListAdapter(
    private val onLabelSelected: (label: LabelEntity) -> Unit,
    private val onLabelUnSelected: (label: LabelEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<LabelDto> = emptyList()

    object ViewHolder {
        class Normal(val binding: RviLabelBinding) : RecyclerView.ViewHolder(binding.root)
        class Selected(val binding: RviLabelSelectedBinding) : RecyclerView.ViewHolder(binding.root)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isSelected) {
            VIEW_TYPE_SELECTED
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder.Normal(
                RviLabelBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> ViewHolder.Selected(
                RviLabelSelectedBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[holder.adapterPosition]

        if (holder is ViewHolder.Normal) {
            holder.binding.textViewTitle.text = item.label.title
            holder.binding.colorView.setBackgroundColor(item.label.color)

            holder.binding.root.setOnClickListener {
                onLabelSelected.invoke(item.label)
            }
        }

        if (holder is ViewHolder.Selected) {
            holder.binding.textViewTitle.text = item.label.title
            holder.binding.colorView.setBackgroundColor(item.label.color)

            holder.binding.root.setOnClickListener {
                onLabelUnSelected.invoke(item.label)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(updatedItems: List<LabelDto>) {
        items = updatedItems
        notifyDataSetChanged()
    }

}