package app.orienti.android.ui.screens.trainer.create_training

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import app.orienti.android.R
import app.orienti.android.databinding.ActivityCreateTrainingBinding
import app.orienti.android.ui.base.DefaultViewModel
import app.orienti.android.ui.screens.trainer.create_track.CreateTrackActivity
import sk.backbone.parent.ui.screens.ActivityTransitions
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener

class CreateTrainingActivity: ParentActivity<ActivityCreateTrainingBinding>(ActivityCreateTrainingBinding::inflate) {
    private val viewModel by lazy {
        getViewModel<DefaultViewModel>()
    }

    override fun getActivityTransitions(): ActivityTransitions = ActivityTransitions.BOTTOM_TOP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.confirmButton.setSafeOnClickListener {
            val name = viewBinding.name.text
            if(name?.trim()?.isNotEmpty() != true){
                Toast.makeText(this, getString(R.string.validation_enter_valid_name), Toast.LENGTH_LONG).show()
            } else {
                viewModel.createTraining(name)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, CreateTrainingActivity::class.java))
        }
    }
}