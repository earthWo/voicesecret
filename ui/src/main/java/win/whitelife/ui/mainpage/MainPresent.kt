package win.whitelife.ui.mainpage

import win.whitelife.base.utils.IntentUtil
import win.whitelife.ui.record.RecordActivity
import win.whitelife.voicesecret.base.main.BasePresent

/**
 * @author wuzefeng
 * 2018/5/7
 */
class MainPresent: MainContract.IMainPresent() {

    fun jumpToRecord(){
        IntentUtil.jumpToActivity(mView!!.obtainContent(),RecordActivity::class.java)
    }

}