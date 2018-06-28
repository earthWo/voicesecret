package win.whitelife.ui.setting

import win.whitelife.ui.R
import win.whitelife.voicesecret.base.main.BaseActivity

/**
 * @author wuzefeng
 * 2018/5/29
 */
class SettingActivity : BaseActivity<SettingPresent,SettingContract.ISettingView>(),SettingContract.ISettingView {






    override fun initView() {
    }

    override fun getLayout(): Int {
        return R.layout.activity_setting
    }

    override fun createPresent(): SettingPresent {
        return SettingPresent()
    }

    override fun fetchData() {
    }
}