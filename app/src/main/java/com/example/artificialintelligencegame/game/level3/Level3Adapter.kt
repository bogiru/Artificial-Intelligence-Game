package com.example.artificialintelligencegame.game.level3

import android.app.Application
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.Level3ItemBinding
import kotlinx.android.synthetic.main.flash_card_layout_back_level3.view.*
import kotlinx.android.synthetic.main.flash_card_layout_front_level3.view.*
import org.koin.dsl.koinApplication

class Level3Adapter(private val application: Application) : RecyclerView.Adapter<Level3Adapter.Level3Holder>() {

        private val cards = listOf(
                Card(
                    application.getDrawable(
                        R.color.colorBackground1)!!,
                    "Победа в ГО",
                    application.getDrawable(R.drawable.column)!!,
                    "Ли Седоль является единственным человеком в мире, которому удалось однажды выиграть у ИИ. Речь идет о его матче 2016 года с программой AlphaGo, разработанной компанией DeepMind, которая ныне принадлежит Google. Всего было сыграно 5 партий, по результатам которых искусственный интеллект выиграл со счетом 4:1. При этом сам Ли считает, что своей победой он обязан неисправности в системе AlphaGo."
                ),

            Card(
                application.getDrawable(
                    R.color.colorBackground2)!!,
                "Победа в ГО",
                application.getDrawable(R.drawable.column)!!,
                "Ли Седоль является единственным человеком в мире, которому удалось однажды выиграть у ИИ. Речь идет о его матче 2016 года с программой AlphaGo, разработанной компанией DeepMind, которая ныне принадлежит Google. Всего было сыграно 5 партий, по результатам которых искусственный интеллект выиграл со счетом 4:1. При этом сам Ли считает, что своей победой он обязан неисправности в системе AlphaGo."
            ),

            Card(
                application.getDrawable(
                    R.color.colorBackground3)!!,
                "Победа в ГО",
                application.getDrawable(R.drawable.column)!!,
                "Ли Седоль является единственным человеком в мире, которому удалось однажды выиграть у ИИ. Речь идет о его матче 2016 года с программой AlphaGo, разработанной компанией DeepMind, которая ныне принадлежит Google. Всего было сыграно 5 партий, по результатам которых искусственный интеллект выиграл со счетом 4:1. При этом сам Ли считает, что своей победой он обязан неисправности в системе AlphaGo."
            ),

            Card(
                application.getDrawable(
                    R.color.colorBackground4)!!,
                "Победа в ГО",
                application.getDrawable(R.drawable.column)!!,
                "бла, бла, бла"
            )

        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Level3Holder {
            return Level3Holder.from(parent)
        }

        override fun getItemCount(): Int = cards.size

        override fun onBindViewHolder(holder: Level3Holder, position: Int) {
            holder.bind(cards[position])
        }

        class Level3Holder(private val binding: Level3ItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(card: Card) {
                binding.level3ItemLayout.background = card.background
                binding.easyFlipView.text.text = card.text
                binding.easyFlipView.title.text = card.title
                binding.easyFlipView.image.setImageDrawable(card.image)
                binding.executePendingBindings()
            }

            companion object {
                fun from(parent: ViewGroup): Level3Holder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = Level3ItemBinding.inflate(layoutInflater, parent, false)
                    return Level3Adapter.Level3Holder(binding)
                }
            }
        }

    }