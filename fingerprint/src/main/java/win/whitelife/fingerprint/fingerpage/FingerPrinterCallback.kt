package win.whitelife.fingerprint.fingerpage

/**
 * @author wuzefeng
 * @date 2018/6/28
 */
interface FingerPrinterCallback {

    fun success()

    fun error(msg: String)

}