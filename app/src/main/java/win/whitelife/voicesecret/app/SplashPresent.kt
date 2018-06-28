package win.whitelife.voicesecret.app

import android.app.Activity
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import win.whitelife.base.utils.IntentUtil
import win.whitelife.fingerprint.fingerpage.FingerPrintHelper
import win.whitelife.fingerprint.fingerpage.FingerPrinterCallback
import win.whitelife.ui.mainpage.MainActivity
import win.whitelife.voicesecret.base.main.BasePresent

/**
 * @author wuzefeng
 * 2018/5/18
 */

class SplashPresent: BasePresent<SplashView>() {

    @RequiresApi(Build.VERSION_CODES.M)
    fun jumpToFingerPage(){
        if(FingerPrintHelper.get().isSupportFingerPrint(mView!!.obtainContent())){
            FingerPrintHelper.get().startIdentification(mView!!.obtainContent(), object : FingerPrinterCallback {
                override fun success() {
                    IntentUtil.jumpToActivity(mView!!.obtainContent(), MainActivity::class.java)
                    FingerPrintHelper.get().destory()
                    (mView!!.obtainContent() as Activity).finish()
                }

                override fun error(msg: String) {
                }
            })
        }else{
            IntentUtil.jumpToActivity(mView!!.obtainContent(), MainActivity::class.java)
        }

    }

}