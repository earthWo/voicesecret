package win.whitelife.voicesecret.basem.main

/**
 * @author wuzefeng
 * 2018/5/7
 */
abstract class BasePresent<V: BaseView> {

    private var mView: V?=null

    fun attachView(v: V){
        mView=v
    }



}