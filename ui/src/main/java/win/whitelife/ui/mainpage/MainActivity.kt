package win.whitelife.ui.mainpage

import android.view.View
import win.whitelife.ui.R
import win.whitelife.voicesecret.base.main.BaseActivity

class MainActivity : BaseActivity<MainPresent, MainContract.IMainView>(),MainContract.IMainView{


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


    fun jumpToRecord(view: View){
        present!!.jumpToRecord()
    }

}