package app.orienti.android.ui.screens.runner.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import app.orienti.android.R
import app.orienti.android.databinding.ActivityMainRunnerBinding
import com.google.android.material.snackbar.Snackbar

class RunnerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainRunnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainRunnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_runner_main_activity, menu)
        return true
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, RunnerMainActivity::class.java))
        }
    }
}