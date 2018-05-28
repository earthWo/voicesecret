package win.whitelife.ui.search

import win.whitelife.base.bean.Voice
import win.whitelife.ui.db.DbHelper

/**
 * @author wuzefeng
 * 2018/5/28
 */
class SearchPresent : SearchContract.ISearchPresent() {


    override fun search(txt: String) {
        var results = DbHelper.getInstance().realm.findContain(Voice::class.java, arrayOf("name"), arrayOf(txt))
        mView!!.bindDate(results)
    }



}