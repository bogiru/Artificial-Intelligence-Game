package com.example.artificialintelligencegame.game.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artificialintelligencegame.databinding.MapItemBinding

class MapAdapter(
    private val levels: List<Int>,
    private val viewModel: MapViewModel
) : RecyclerView.Adapter<MapAdapter.MapViewHolder>() {

    override fun onBindViewHolder(viewHolder: MapViewHolder, position: Int) {
        viewHolder.bind(viewModel, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        return MapViewHolder.from(parent)
    }

    override fun getItemCount(): Int = levels.size

    class MapViewHolder(private val binding: MapItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MapViewModel, position: Int) {
            binding.viewModel = viewModel
            binding.level = position + 1
            binding.executePendingBindings()

            if (position % 2 == 0) setButtonToLeft()
            else setButtonToRight()

        }

        private fun setButtonToRight() {
            binding.mapItemLayout.setPadding(200, 32, 0,32 )
        }

        private fun setButtonToLeft() {
            binding.mapItemLayout.setPadding(0, 32, 200, 32)
        }

        companion object {
            fun from(parent: ViewGroup): MapViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MapItemBinding.inflate(layoutInflater, parent, false)
                return MapViewHolder(binding)
            }
        }
    }
}