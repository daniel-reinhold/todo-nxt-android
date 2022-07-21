package de.todonxt.feature.dashboard.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.core.data.source.local.entities.TaskEntity
import de.todonxt.core.ui.viewholder.TaskViewHolder
import de.todonxt.core.util.formatDate
import de.todonxt.core.util.formatDateTime
import de.todonxt.core.util.formatTime
import de.todonxt.databinding.RviTaskBinding

class TaskAdapter(
    private val onClick: (taskID: Int) -> Unit
) : ListAdapter<TaskEntity, TaskViewHolder>(TaskEntity.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            RviTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        val context = holder.binding.root.context

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

        holder.binding.root.setOnClickListener {
            onClick.invoke(item.id ?: 0)
        }
    }
}