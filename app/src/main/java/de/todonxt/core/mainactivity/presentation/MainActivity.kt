package de.todonxt.core.mainactivity.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val appBarConfiguration = AppBarConfiguration(
        topLevelDestinationIds = setOf(
            R.id.fragmentDashboard
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)

        val navController = (
            supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        ).navController

        binding.toolbar.setupWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )

        setContentView(binding.root)
    }
}