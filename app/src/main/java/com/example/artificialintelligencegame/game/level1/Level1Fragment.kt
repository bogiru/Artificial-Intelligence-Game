package com.example.artificialintelligencegame.game.level1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.Level1FragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class Level1 : Fragment() {

    private val viewModel: Level1ViewModel by viewModel()
    private lateinit var binding: Level1FragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureBinding(inflater, container)
        setupObserverViewModel()

        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.level1_fragment, container, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    private fun setupObserverViewModel() {
        viewModel.showNewRobotText.observe(viewLifecycleOwner, Observer {index ->
            showNewText(index)
        })
    }

    private fun showNewText(index: Int) {
        val animAppearance = AnimationUtils.loadAnimation(this.context, R.anim.appearance)
        val animDisappearance = AnimationUtils.loadAnimation(this.context, R.anim.disappearance)

        binding.robotText.startAnimation(animDisappearance)

        when (index) {
            2 -> {
                binding.robotText.text = activity!!.application.getString(R.string.robot_text_2)
                binding.actionTextView.text = "Эээ, я вообщем-то, спал несколько лет... Эксперимент ученых... Так что же произошло??"
            }
            3 -> {
                binding.robotText.text = activity!!.application.getString(R.string.robot_text_3)

                binding.actionTextView.text =  "Я последня надежда? О чем ты?"

            }
            4 -> {
                binding.robotText.text = activity!!.application.getString(R.string.robot_text_4)
                binding.actionTextView.text = "Я готов!"
            }

            5 -> findNavController().navigate(R.id.action_level1_to_level2Fragment)
        }

        binding.robotText.startAnimation(animAppearance)
    }

}