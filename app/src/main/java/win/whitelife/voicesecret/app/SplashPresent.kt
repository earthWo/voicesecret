package win.whitelife.voicesecret.app

import android.content.Intent
import win.whitelife.base.utils.IntentUtil
import win.whitelife.ui.mainpage.MainActivity
import win.whitelife.voicesecret.base.main.BasePresent

/**
 * @author wuzefeng
 * 2018/5/18
 */

class SplashPresent: BasePresent<SplashView>() {

    fun jumpToMainPage(){
       IntentUtil.jumpToActivity(mView!!.obtainContent(),MainActivity::class.java)
    }

}