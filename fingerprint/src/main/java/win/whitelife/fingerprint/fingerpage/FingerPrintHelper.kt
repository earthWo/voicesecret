package win.whitelife.fingerprint.fingerpage

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.support.v4.os.CancellationSignal

/**
 * @author wuzefeng
 * @date 2018/6/28
 */
class FingerPrintHelper : FingerPrintContract.IFingerPrintPresent{


    override fun destory() {
        if(iFingerPrintView!=null){
            iFingerPrintView!!.dismiss()
        }
    }

    private var fingerprintManager: FingerprintManagerCompat?=null

    private var cancellationSignal : CancellationSignal?=null

    private var iFingerPrintView :FingerPrintContract.IFingerPrintView?=null

    private constructor()

    companion object {

        private  var fingerPrintHelper: FingerPrintHelper?=null

        fun get(): FingerPrintHelper{
            if(fingerPrintHelper==null){
                synchronized(FingerPrintHelper::class.java){
                    if(fingerPrintHelper==null){
                        fingerPrintHelper=FingerPrintHelper()
                    }
                }
            }
           return fingerPrintHelper!!
        }
    }

    /**
     * 开始指纹识别
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun startIdentification(activity: FragmentActivity, callback : FingerPrinterCallback) {

        iFingerPrintView=FingerPrintDialog()
        (iFingerPrintView as FingerPrintDialog).manager=activity.supportFragmentManager
        iFingerPrintView!!.startShow()
        identification(callback)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun startIdentification(context: Context, callback: FingerPrinterCallback) {
        iFingerPrintView=FingerPrintAlertDialog(context)
        (iFingerPrintView as FingerPrintAlertDialog).init()
        iFingerPrintView!!.startShow()
        identification(callback)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun identification(callback: FingerPrinterCallback) {
        cancellationSignal= CancellationSignal()
        fingerprintManager!!.authenticate(CryptoObjectHelper().buildCryptoObject(),0,cancellationSignal, object : FingerprintManagerCompat.AuthenticationCallback() {

            override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
                iFingerPrintView?.error(errString.toString())
                callback.error(errString.toString())
            }

            override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {
                cancelIdentification()
                iFingerPrintView?.success()
                callback.success()
            }

            override fun onAuthenticationFailed() {
                iFingerPrintView?.error("扫描失败")
                callback.error("扫描失败")
            }
        }, null)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun startIdentification(fingerPrintView: FingerPrintContract.IFingerPrintView, callback: FingerPrinterCallback) {
        iFingerPrintView!!.startShow()
        identification(callback)
    }


    /**
     * 取消指纹识别
     */
    override fun cancelIdentification() {
        cancellationSignal?.cancel()
    }


    /**
     * 是否支持指纹识别
     */
    override fun isSupportFingerPrint(context: Context): Boolean {
        fingerprintManager = FingerprintManagerCompat.from(context)
        //硬件是否支持指纹识别
        if (!fingerprintManager!!.isHardwareDetected) {
            return false
        }
        val keyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        //是否开启了密码保护
        if (!keyguardManager.isKeyguardSecure) {
            return false
        }
        //是否有注册指纹
        if (!fingerprintManager!!.hasEnrolledFingerprints()) {
            return false
        }
        return true
    }


}
