package win.whitelife.voicesecret.app

import android.content.Context
import android.support.multidex.MultiDex
import win.whitelife.base.BaseApplication
import win.whitelife.base.utils.FileUtil

/**
 * @author wuzefeng
 * 2018/5/18
 */
class VoiceApplication: BaseApplication() {


     override fun init(){
        FileUtil.init(this)
    }

     override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}