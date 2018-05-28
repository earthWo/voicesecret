package win.whitelife.ui.search

import win.whitelife.base.bean.Voice
import win.whitelife.voicesecret.base.main.BasePresent
import win.whitelife.voicesecret.base.main.BaseView

/**
 * @author wuzefeng
 * 2018/5/28
 */
class SearchContract {


    interface ISearchView : BaseView{

       fun bindDate(list : List<Voice>)

    }



    abstract class ISearchPresent : BasePresent<ISearchView>(){
        abstract fun search(txt: String)
    }

}