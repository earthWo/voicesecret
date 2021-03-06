package win.whitelife.voicesecret.basem.main

/**
 * @author wuzefeng
 * 2018/5/7
 */
interface LoaderFactory<out P: BasePresent<V>,V: BaseView> {

    fun create(): P
}