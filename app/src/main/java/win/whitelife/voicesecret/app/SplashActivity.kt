package win.whitelife.voicesecret.app

import win.whitelife.permission.PermissionManager
import win.whitelife.voicesecret.R
import win.whitelife.voicesecret.base.main.BaseActivity

/**
 * @author wuzefeng
 * 2018/5/18
 */
class SplashActivity: BaseActivity<SplashPresent,SplashView>(),SplashView {


    override fun initView() {
        PermissionManager.with(this)
                .addPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .addPermission(android.Manifest.permission.RECORD_AUDIO)
                .start()
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


}