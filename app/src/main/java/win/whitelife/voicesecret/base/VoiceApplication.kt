package win.whitelife.voicesecret.base

import android.app.Application
import win.whitelife.base.BaseApplication
import win.whitelife.base.utils.FileUtil

/**
 * @author wuzefeng
 * 2018/5/18
 */
class VoiceApplication: BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        init()
    }


    private fun init(){
        FileUtil.init(this)
    }

}