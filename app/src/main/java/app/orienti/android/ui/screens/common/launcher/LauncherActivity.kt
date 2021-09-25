package app.orienti.android.ui.screens.common.launcher

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import app.orienti.android.entities.UserType
import app.orienti.android.ui.base.DefaultViewModel
import app.orienti.android.ui.screens.common.select_mode.SelectModeActivity
import app.orienti.android.ui.screens.runner.main.RunnerMainActivity
import app.orienti.android.ui.screens.trainer.main.TrainerMainActivity
import sk.backbone.parent.ui.screens.ParentActivity

class LauncherActivity : ParentActivity<ViewBinding>(null) {
    private val viewModel by lazy {
        getViewModel<DefaultViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when(viewModel.getUserType()){
            UserType.NONE -> SelectModeActivity.startActivity(this)
            UserType.RUNNER -> RunnerMainActivity.startActivity(this)
            UserType.TRAINER -> TrainerMainActivity.startActivity(this)
        }



        finish()
    }
}
