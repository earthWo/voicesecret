package win.whitelife.ui.mainpage

import win.whitelife.ui.R
import win.whitelife.voicesecret.basem.main.BaseActivity

class MainActivity : BaseActivity<MainPresent, MainActivity>(), MainView {


    override fun createPresent(): MainPresent {
        return MainPresent()
    }

    override fun initView() {
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    override fun fetchData() {
    }


}