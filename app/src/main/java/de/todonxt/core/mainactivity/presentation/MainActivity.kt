package de.todonxt.core.mainactivity.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import de.todonxt.R
import de.todonxt.databinding.ActivityMainBinding

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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = getString(
                when (destination.id) {
                    R.id.fragmentDashboard -> R.string.dashboard
                    else -> R.string.app_name
                }
            )
        }

        setContentView(binding.root)
    }
}