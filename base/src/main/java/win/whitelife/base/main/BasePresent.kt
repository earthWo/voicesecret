package win.whitelife.voicesecret.base.main

/**
 * @author wuzefeng
 * 2018/5/7
 */
abstract class BasePresent<V: BaseView> {

    var mView: V?=null

    fun attachView(v: V){
        mView=v
    }



}