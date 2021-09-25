package app.orienti.android.ui.screens.common.set_name

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import app.orienti.android.R
import app.orienti.android.databinding.ActivitySetNameBinding
import app.orienti.android.ui.base.DefaultViewModel
import app.orienti.android.ui.screens.trainer.create_track.CreateTrackActivity
import sk.backbone.parent.ui.screens.ActivityTransitions
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener

class SetNameActivity: ParentActivity<ActivitySetNameBinding>(ActivitySetNameBinding::inflate) {
    private val viewModel by lazy {
        getViewModel<DefaultViewModel>()
    }

    override fun getActivityTransitions(): ActivityTransitions = ActivityTransitions.BOTTOM_TOP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getUserName()?.let {
            viewBinding.name.text = it
        }

        viewBinding.confirmButton.setSafeOnClickListener {
            val name = viewBinding.name.text
            if(name?.trim()?.isEmpty() == true){
                Toast.makeText(this, getString(R.string.validation_enter_valid_name), Toast.LENGTH_LONG).show()
            } else {
                viewModel.setUserName(name)
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
        fun getStartingIntent(context: Context): Intent {
            return Intent(context, CreateTrackActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(getStartingIntent(context))
        }
    }
}