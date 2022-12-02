package app.orienti.android.ui.screens.common.launcher

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import app.orienti.android.entities.UserType
import app.orienti.android.models.UserService
import app.orienti.android.ui.screens.common.select_mode.SelectModeActivity
import app.orienti.android.ui.screens.runner.main.RunnerMainActivity
import app.orienti.android.ui.screens.trainer.main.TrainerMainActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import javax.inject.Inject

@AndroidEntryPoint
class LauncherActivity : ParentActivity<ViewBinding>(null) {
    @Inject lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when(userService.getUserType()){
            UserType.NONE -> SelectModeActivity.startActivity(this)
            UserType.RUNNER -> RunnerMainActivity.startActivity(this)
            UserType.TRAINER -> TrainerMainActivity.startActivity(this)
        }

        finish()
    }
}
