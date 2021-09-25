package app.orienti.android.ui.screens.common.launcher

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import app.orienti.android.ui.screens.common.select_mode.SelectModeActivity
import app.orienti.android.ui.screens.runner.main.RunnerMainActivity
import sk.backbone.parent.ui.screens.ParentActivity

class LauncherActivity : ParentActivity<ViewBinding>(null) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SelectModeActivity.startActivity(this)
        finish()
    }
}
