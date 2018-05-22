package win.whitelife.ui.mainpage

import win.whitelife.voicesecret.base.main.BasePresent
import win.whitelife.voicesecret.base.main.BaseView

/**
 * @author wuzefeng
 * 2018/5/18
 */
class MainContract{

    interface IMainView: BaseView

    abstract class IMainPresent:BasePresent<IMainView>()

}