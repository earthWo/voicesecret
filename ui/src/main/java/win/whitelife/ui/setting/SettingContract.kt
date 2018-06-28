package win.whitelife.ui.setting

import win.whitelife.voicesecret.base.main.BasePresent
import win.whitelife.voicesecret.base.main.BaseView

/**
 * @author wuzefeng
 * 2018/5/29
 */
class SettingContract {


    interface ISettingView : BaseView



    abstract class ISettingPresent : BasePresent<ISettingView>()


}