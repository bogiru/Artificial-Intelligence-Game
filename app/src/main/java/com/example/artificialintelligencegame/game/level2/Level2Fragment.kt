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
        setupObserverViewModel()
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_level2, container, false)
        binding.viewModel = viewModel
        binding.pager.adapter = Level2Adapter(activity!!.application, viewModel)

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

    }

    private fun enLargeView(view: View) {
        view.startAnimation(animEnlarge)
    }

    private fun showText(view: View) {

        when (view.id) {
            R.id.computer -> binding.robotText.text =  activity?.application?.getText(R.string.computer)
            R.id.column -> binding.robotText.text =  activity?.application?.getText(R.string.column)
            R.id.vacuum_cleaner -> binding.robotText.text =  activity?.application?.getText(R.string.vacuumCleaner)
        }

        binding.level2CardView.startAnimation(animAppearence)
        binding.level2CardView.visibility = View.VISIBLE
    }

    private fun hideText() {
        binding.level2CardView.visibility = View.INVISIBLE
    }



}