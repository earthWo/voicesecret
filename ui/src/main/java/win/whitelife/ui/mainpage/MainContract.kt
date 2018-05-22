package win.whitelife.ui.mainpage

import win.whitelife.base.bean.Voice
import win.whitelife.voicesecret.base.main.BasePresent
import win.whitelife.voicesecret.base.main.BaseView

/**
 * @author wuzefeng
 * 2018/5/18
 */
class MainContract{

    interface IMainView: BaseView{
       fun bindData(voices: List<Voice>)
    }

    abstract class IMainPresent:BasePresent<IMainView>(){
       abstract fun fetchData()
    }

}