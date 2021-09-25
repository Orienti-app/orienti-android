package app.orienti.android.ui.screens.trainer.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.orienti.android.R
import app.orienti.android.databinding.ActivityMainTrainerBinding
import app.orienti.android.ui.screens.runner.main.RunnerMainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class TrainerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainTrainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        RunnerMainActivity.startActivity(this)
    }


}