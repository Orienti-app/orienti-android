package app.orienti.android.ui.screens.trainer.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.orienti.android.R
import app.orienti.android.databinding.ActivityMainTrainerBinding
import app.orienti.android.ui.screens.common.select_mode.SelectModeActivity
import app.orienti.android.ui.screens.common.set_name.SetNameActivity
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
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_trainer_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.context_menu_trainer_main_activity_synchronize -> {
                true
            }
            R.id.context_menu_trainer_main_activity_change_name -> {
                SetNameActivity.startActivity(this)
                true
            }
            R.id.context_menu_trainer_main_activity_change_mode -> {
                SelectModeActivity.startActivity(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, TrainerMainActivity::class.java))
        }
    }
}