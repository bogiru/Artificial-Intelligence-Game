package com.example.artificialintelligencegame.game.level2

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.FragmentBillboardBinding
import com.example.artificialintelligencegame.databinding.FragmentHomeBinding
import com.example.artificialintelligencegame.databinding.FragmentStreetBinding


class Level2Adapter(
    private val application: Application,
    private val viewModel: Level2ViewModel
) :  PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = application.baseContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val one = DataBindingUtil.inflate<FragmentHomeBinding>(layoutInflater,R.layout.fragment_home, container, false)
        val two = DataBindingUtil.inflate<FragmentStreetBinding>(layoutInflater,R.layout.fragment_street, container, false)
        val three = DataBindingUtil.inflate<FragmentBillboardBinding>(layoutInflater, R.layout.fragment_billboard, container, false)

        one.viewModel = viewModel
        two.viewModel = viewModel
        three.viewModel = viewModel

        val viewarr = arrayOf(one.root, two.root, three.root)
        container.addView(viewarr[position])
        return viewarr[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}