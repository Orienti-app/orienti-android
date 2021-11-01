package app.orienti.android.ui.screens.common.select_mode

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import app.orienti.android.databinding.ActivitySelectModeBinding
import app.orienti.android.entities.UserType
import app.orienti.android.models.UserModel
import app.orienti.android.ui.screens.common.set_name.SetNameActivity
import app.orienti.android.ui.screens.runner.main.RunnerMainActivity
import app.orienti.android.ui.screens.trainer.main.TrainerMainActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener
import javax.inject.Inject

@AndroidEntryPoint
class SelectModeActivity : ParentActivity<ActivitySelectModeBinding>(ActivitySelectModeBinding::inflate) {
    @Inject lateinit var userModel: UserModel

    private val hasEnteredName get() = userModel.hasUserName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.runner.setSafeOnClickListener {
            if(!hasEnteredName){
                setRunnerNameLauncher.launch(SetNameActivity.getStartingIntent(this))
            } else {
                RunnerMainActivity.startActivity(this)
                userModel.setUserType(UserType.RUNNER)
            }
        }

        viewBinding.trainer.setSafeOnClickListener {
            if(!hasEnteredName){
                setTrainerNameLauncher.launch(SetNameActivity.getStartingIntent(this))
            } else {
                TrainerMainActivity.startActivity(this)
                userModel.setUserType(UserType.TRAINER)
            }
        }
    }

    private val setTrainerNameLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            TrainerMainActivity.startActivity(this)
            userModel.setUserType(UserType.TRAINER)
            finish()
        }
    }

    private val setRunnerNameLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            RunnerMainActivity.startActivity(this)
            userModel.setUserType(UserType.RUNNER)
            finish()
        }
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, SelectModeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}
