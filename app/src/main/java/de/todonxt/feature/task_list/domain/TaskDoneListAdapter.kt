package de.todonxt.feature.task_list.domain

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.core.data.source.local.entities.TaskEntity
import de.todonxt.core.ui.bindIsGone
import de.todonxt.core.ui.viewholder.TaskDoneViewHolder
import de.todonxt.core.util.formatDate
import de.todonxt.core.util.formatTime
import de.todonxt.databinding.RviTaskDoneBinding

class TaskDoneListAdapter : RecyclerView.Adapter<TaskDoneViewHolder>() {
    private var items: List<TaskEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDoneViewHolder {
        return TaskDoneViewHolder(
            RviTaskDoneBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskDoneViewHolder, position: Int) {
        val item = items[holder.adapterPosition]

        holder.binding.textViewTitle.text = item.title
        if (item.date != null) {
            holder.binding.chipDate.apply {
                text = item.date?.formatDate(context)
                visibility = View.VISIBLE
            }
        } else {
            holder.binding.chipDate.visibility = View.GONE
        }

        if (item.time != null) {
            holder.binding.chipTime.apply {
                text = item.time?.formatTime(context)
                visibility = View.VISIBLE
            }
        } else {
            holder.binding.chipTime.visibility = View.GONE
        }


        holder.binding.textViewTitle.apply {
            paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(updatedItems: List<TaskEntity>) {
        items = updatedItems
        notifyDataSetChanged()
    }
}