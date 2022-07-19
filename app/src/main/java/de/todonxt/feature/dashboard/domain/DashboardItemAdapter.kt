package de.todonxt.feature.dashboard.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.databinding.RviDashboardItemBinding
import de.todonxt.feature.dashboard.data.DASHBOARD_ITEMS
import de.todonxt.feature.dashboard.data.DashboardItem

class DashboardItemAdapter(
    private val onItemSelected: (item: Int) -> Unit
) : RecyclerView.Adapter<DashboardItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RviDashboardItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RviDashboardItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = DASHBOARD_ITEMS[holder.adapterPosition]

        holder.binding.imageView.setImageResource(item.iconResource)
        holder.binding.title.apply {
            setText(item.titleResource)
            isSelected = true
        }

        holder.binding.cardView.setOnClickListener {
            onItemSelected.invoke(item.action)
        }
    }

    override fun getItemCount(): Int = DASHBOARD_ITEMS.size

    companion object {

        private var instance: DashboardItemAdapter? = null

        fun getInstance(onItemSelected: (item: Int )-> Unit): DashboardItemAdapter {
            if (instance == null) {
                instance = DashboardItemAdapter(onItemSelected)
            }

            return instance!!
        }

    }

}