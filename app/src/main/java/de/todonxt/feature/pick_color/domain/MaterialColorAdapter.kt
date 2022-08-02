package de.todonxt.feature.pick_color.domain

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.core.ui.MaterialPalette
import de.todonxt.databinding.RviColorBinding
import de.todonxt.feature.pick_color.presentation.MaterialColorViewHolder

class MaterialColorAdapter(
    private val palette: MaterialPalette
) : RecyclerView.Adapter<MaterialColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialColorViewHolder {
        return MaterialColorViewHolder(
            RviColorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MaterialColorViewHolder, position: Int) {
        holder.binding.cardView.setCardBackgroundColor(
            Color.parseColor(palette.colors[holder.adapterPosition].hex)
        )
    }

    override fun getItemCount(): Int = palette.colors.size
}