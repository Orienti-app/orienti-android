package app.orienti.android.ui.screens.trainer.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.orienti.android.R
import app.orienti.android.databinding.ActivityMainTrainerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class TrainerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainTrainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_trainer_bottom_navigation_trainings,
                R.id.menu_trainer_bottom_navigation_tracks,
                R.id.menu_trainer_bottom_navigation_control_points,
                R.id.menu_trainer_bottom_navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, TrainerMainActivity::class.java))
        }
    }
}