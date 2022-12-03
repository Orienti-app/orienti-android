package app.orienti.android.ui.screens.runner.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import app.orienti.android.R
import app.orienti.android.databinding.ActivityMainRunnerBinding
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.common.run_detail.RunDetailFragment
import app.orienti.android.ui.screens.common.select_mode.SelectModeActivity
import app.orienti.android.ui.screens.common.set_name.SetNameActivity
import app.orienti.android.ui.screens.runner.scan_control.ScanControlPointActivity
import app.orienti.android.ui.screens.runner.start_run.NewRunActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener
import javax.inject.Inject

@AndroidEntryPoint
class RunnerMainActivity : ParentActivity<ActivityMainRunnerBinding>(ActivityMainRunnerBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.scan.setSafeOnClickListener {
            scanControlPointLauncher.launch(ScanControlPointActivity.createIntent(it.context))
        }

        val runDetailFragment: RunDetailFragment = supportFragmentManager.findFragmentById(R.id.run_detail_fragment) as RunDetailFragment

        trainingService.getActiveRunAsLiveData().observe(this){
            runDetailFragment.setRunId(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_runner_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.context_menu_runner_main_activity_new_run -> {
                newRunLauncher.launch(NewRunActivity.createIntent(this))
                true
            }
            R.id.context_menu_runner_main_activity_change_name -> {
                SetNameActivity.startActivity(this)
                true
            }
            R.id.context_menu_runner_main_activity_change_mode -> {
                SelectModeActivity.startActivity(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val scanControlPointLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            return@registerForActivityResult
        }
    }

    private val newRunLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            return@registerForActivityResult
        }
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, RunnerMainActivity::class.java))
        }
    }
}


