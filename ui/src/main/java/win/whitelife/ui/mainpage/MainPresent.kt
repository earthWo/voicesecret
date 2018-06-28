package win.whitelife.ui.mainpage

import win.whitelife.base.bean.Voice
import win.whitelife.base.utils.IntentUtil
import win.whitelife.ui.db.DbHelper
import win.whitelife.ui.record.RecordActivity
import win.whitelife.ui.search.SearchActivity
import win.whitelife.ui.setting.SettingActivity

/**
 * @author wuzefeng
 * 2018/5/7
 */
class MainPresent: MainContract.IMainPresent() {

    override fun jumpToSetting() {
        IntentUtil.jumpToActivity(mView!!.obtainContent(),SettingActivity::class.java)
    }

    override fun fetchData() {
        val list: List<Voice> = DbHelper.getInstance().realm.findAll(Voice::class.java)
        mView!!.bindData(list)
    }

    override fun jumpToRecord(){
        IntentUtil.jumpToActivity(mView!!.obtainContent(),RecordActivity::class.java)
    }

    override fun jumpToSearch(){
        IntentUtil.jumpToActivity(mView!!.obtainContent(),SearchActivity::class.java)
    }

}