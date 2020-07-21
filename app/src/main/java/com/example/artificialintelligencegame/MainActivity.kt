package com.example.artificialintelligencegame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.artificialintelligencegame.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureBinding()
        configureNavigationView()
    }

    private fun configureBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    @SuppressLint("ResourceType")
    private fun configureNavigationView() {
        navController = findNavController(R.id.content_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        navController.navigate(R.id.mapFragment2)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.content_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}