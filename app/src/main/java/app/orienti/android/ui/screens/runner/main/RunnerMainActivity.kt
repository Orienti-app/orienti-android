package app.orienti.android.ui.screens.runner.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.R
import app.orienti.android.databinding.ActivityMainRunnerBinding
import app.orienti.android.ui.screens.common.select_mode.SelectModeActivity
import app.orienti.android.ui.screens.common.set_name.SetNameActivity
import sk.backbone.parent.ui.components.LinearSpacingItemDecorationVertical
import sk.backbone.parent.ui.screens.ParentActivity

class RunnerMainActivity : ParentActivity<ActivityMainRunnerBinding>(ActivityMainRunnerBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.controlPointsRecycler.layoutManager = LinearLayoutManager(this)
        viewBinding.controlPointsRecycler.addItemDecoration(LinearSpacingItemDecorationVertical(resources.getDimension(R.dimen.spacing_default_15_dp).toInt()))
        viewBinding.controlPointsRecycler.adapter = ControlPointsAdapter(this).apply {
            replaceDataSet(listOf(true, true, false, true, false, true, false, true, false, true, false, true, false, true, false))
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

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, RunnerMainActivity::class.java))
        }
    }
}


