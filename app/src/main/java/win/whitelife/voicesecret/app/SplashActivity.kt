package win.whitelife.voicesecret.app

import android.content.Context
import win.whitelife.voicesecret.R
import win.whitelife.voicesecret.base.main.BaseActivity

/**
 * @author wuzefeng
 * 2018/5/18
 */
class SplashActivity: BaseActivity<SplashPresent,SplashView>(),SplashView {


    override fun initView() {
    }

    override fun getLayout(): Int {
      return R.layout.activity_splash
    }

    override fun createPresent(): SplashPresent {
        return SplashPresent()
    }

    override fun fetchData() {
        present?.jumpToMainPage()
    }

    override fun obtainContent(): Context {
        return super.obtainContent()
    }

}