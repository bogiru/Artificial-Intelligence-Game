package com.example.artificialintelligencegame.game.level2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.FragmentLevel2Binding
import org.koin.android.viewmodel.ext.android.viewModel

class Level2Fragment : Fragment() {

    lateinit var binding: FragmentLevel2Binding
    private val viewModel: Level2ViewModel by viewModel()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_level2, container, false)
        binding.viewModel = viewModel


        binding.executePendingBindings()
    }

    private fun configureAnimation() {
        animAppearence = AnimationUtils.loadAnimation(context, R.anim.disappearance)
        animEnlarge = AnimationUtils.loadAnimation(context, R.anim.enlarge)
    }

    private fun setupObserverViewModel() {
        viewModel.showRobot.observe(viewLifecycleOwner, Observer { view ->
            enLargeView(view)
            showText(view)
        })

         viewModel.hideText.observe(viewLifecycleOwner, Observer {
             hideText()
         })

        viewModel.openNextLevel.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_level2Fragment_to_level3Fragment)
        })

    }

    private fun configureDotsIndicator() {
        val wormDotsIndicator = binding.level2DotsIndicator
        val viewPager = binding.pager
        val adapter = Level2Adapter(activity!!.application, viewModel)
        binding.pager.adapter = adapter
        wormDotsIndicator.setViewPager(viewPager)
    }

    private fun enLargeView(view: View) {
        view.startAnimation(animEnlarge)
    }

    private fun showText(view: View) {
        binding.countFoundRobots.text = viewModel.foundRobots.size.toString()

        when (view.id) {
            R.id.computer -> binding.robotText.text =  activity?.application?.getText(R.string.computer)
            R.id.column -> binding.robotText.text =  activity?.application?.getText(R.string.column)
            R.id.vacuum_cleaner -> binding.robotText.text =  activity?.application?.getText(R.string.vacuumCleaner)
            R.id.car -> binding.robotText.text =  activity?.application?.getText(R.string.car)
            R.id.signaling -> binding.robotText.text =  activity?.application?.getText(R.string.signaling)
            R.id.camera -> binding.robotText.text =  activity?.application?.getText(R.string.camera)
            R.id.advertising -> binding.robotText.text =  activity?.application?.getText(R.string.advertising)
            R.id.first_aid_kit -> binding.robotText.text =  activity?.application?.getText(R.string.first_aid_kit)
            R.id.traffic_lights -> binding.robotText.text =  activity?.application?.getText(R.string.traffic_lights)
        }

        binding.level2CardView.startAnimation(animAppearence)
        binding.level2CardView.visibility = View.VISIBLE
    }

    private fun hideText() {
        binding.level2CardView.visibility = View.INVISIBLE
    }



}