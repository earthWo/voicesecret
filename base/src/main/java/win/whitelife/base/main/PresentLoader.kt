package win.whitelife.voicesecret.base.main

import android.content.Context
import android.support.v4.content.Loader

/**
 * @author wuzefeng
 * 2018/5/7
 */
class PresentLoader<V: BaseView,P :BasePresent<V>>(context: Context,factory: LoaderFactory<P,V>) : Loader<P>(context) {

    private var present: P?=null

    private var factory:  LoaderFactory<P,V>?=null


    init {
        this.factory=factory
    }


    override fun onStartLoading() {
        if(present!=null){
            deliverResult(present)
            return
        }
        forceLoad()
    }


    override fun onForceLoad() {
        present=factory!!.create()
        deliverResult(present)
    }

    override fun onReset() {
        present=null
    }




}