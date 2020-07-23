package com.example.artificialintelligencegame.game.level3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.FragmentLevel2Binding
import com.example.artificialintelligencegame.databinding.Level3FragmentBinding
import com.example.artificialintelligencegame.game.level2.Level2Adapter
import com.example.artificialintelligencegame.game.level2.Level2ViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class Level3Fragment : Fragment() {

    lateinit var binding: Level3FragmentBinding
    private val viewModel: Level3ViewModel by viewModel()

    private lateinit var animAppearence: Animation
    private lateinit var animEnlarge: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureAnimation()
        configureBinding(inflater, container)
        configureDotsIndicator()
        setupObserverViewModel()
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.level3_fragment, container, false)
        binding.viewModel = viewModel


        binding.executePendingBindings()
    }

    private fun configureAnimation() {
        animAppearence = AnimationUtils.loadAnimation(context, R.anim.disappearance)
        animEnlarge = AnimationUtils.loadAnimation(context, R.anim.enlarge)
    }

    private fun setupObserverViewModel() {

        viewModel.hideText.observe(viewLifecycleOwner, Observer {
            hideText()
        })

    }

    private fun configureDotsIndicator() {
        val wormDotsIndicator = binding.level3DotsIndicator
        val viewPager = binding.pagerLevel3
        val adapter = Level3Adapter(activity!!.application)
        binding.pagerLevel3.adapter = adapter
        wormDotsIndicator.setViewPager2(viewPager)
    }

    private fun hideText() {
        binding.backgroundRobotLayout.visibility = View.INVISIBLE
    }
}