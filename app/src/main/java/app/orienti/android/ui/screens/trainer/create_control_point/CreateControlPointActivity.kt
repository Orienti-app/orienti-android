package app.orienti.android.ui.screens.trainer.create_control_point

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import app.orienti.android.R
import app.orienti.android.databinding.ActivityCreateControlPointBinding
import app.orienti.android.models.TrainingService
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ActivityTransitions
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener
import javax.inject.Inject

@AndroidEntryPoint
class CreateControlPointActivity: ParentActivity<ActivityCreateControlPointBinding>(ActivityCreateControlPointBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService

    override fun getActivityTransitions(): ActivityTransitions = ActivityTransitions.BOTTOM_TOP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.confirmButton.setSafeOnClickListener {
            val name = viewBinding.name.text
            val code = viewBinding.code.text
            when {
                name?.trim()?.isNotEmpty() != true -> {
                    Toast.makeText(this, getString(R.string.validation_enter_valid_name), Toast.LENGTH_LONG).show()
                }
                code?.trim()?.isNotEmpty() != true  -> {
                    Toast.makeText(this, getString(R.string.validation_enter_valid_code), Toast.LENGTH_LONG).show()
                }
                else -> {
                    trainingService.createControlPoint(name, code)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
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
            context.startActivity(Intent(context, CreateControlPointActivity::class.java))
        }
    }
}