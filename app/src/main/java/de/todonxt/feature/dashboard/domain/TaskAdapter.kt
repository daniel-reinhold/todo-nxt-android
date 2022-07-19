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
import de.todonxt.databinding.RviTaskBinding

class TaskAdapter : ListAdapter<TaskEntity, TaskViewHolder>(TaskEntity.DIFF_CALLBACK) {
    private var items: List<TaskEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            RviTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = items[holder.adapterPosition]
        val context = holder.binding.root.context

        holder.binding.textViewTitle.text = item.title

        holder.binding.chipTime.apply {
            visibility = if (item.time != null) View.VISIBLE else View.GONE
            text = if (!item.isWholeDay) {
                item.time?.formatDateTime(context)
            } else {
                item.time?.formatDate(context)
            }
        }

        holder.binding.chipIsWholeDay.visibility = if (item.isWholeDay) {
            View.VISIBLE
        } else {
            View.GONE
        }

    }

    override fun getItemCount(): Int = items.size
}