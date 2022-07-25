package de.todonxt.feature.task_list.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.core.data.source.local.entities.TaskEntity
import de.todonxt.core.ui.viewholder.TaskViewHolder
import de.todonxt.core.util.formatDate
import de.todonxt.core.util.formatTime
import de.todonxt.databinding.RviTaskBinding

class TaskListAdapter(
    private val onClick: (taskID: Int) -> Unit,
    private val onDateClick: (task: TaskEntity) -> Unit,
    private val onTimeClick: (task: TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    private var items: List<TaskEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            RviTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = items[holder.adapterPosition]
        val context = holder.binding.root.context

        holder.binding.textViewTitle.text = item.title

        if (item.date != null) {
            holder.binding.chipDate.apply {
                text = item.date?.formatDate(context)
                visibility = View.VISIBLE
                setOnClickListener {
                    item.date?.let {
                        onDateClick.invoke(item)
                    }
                }
            }
        } else {
            holder.binding.chipDate.apply {
                visibility = View.GONE
                setOnClickListener(null)
            }
        }

        if (item.time != null) {
            holder.binding.chipTime.apply {
                text = item.time?.formatTime(context)
                visibility = View.VISIBLE
                setOnClickListener {
                    item.time?.let {
                        onTimeClick.invoke(item)
                    }
                }
            }
        } else {
            holder.binding.chipTime.visibility = View.GONE
        }

        holder.binding.root.setOnClickListener {
            item.id?.let {
                onClick.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(updatedItems: List<TaskEntity>) {
        items = updatedItems
        notifyDataSetChanged()
    }
}