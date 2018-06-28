package win.whitelife.fingerprint.fingerpage

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import win.whitelife.voicesecret.base.main.BasePresent
import win.whitelife.voicesecret.base.main.BaseView

/**
 * @author wuzefeng
 * @date 2018/6/26
 */
class FingerPrintContract {

    interface  IFingerPrintPresent {

        fun isSupportFingerPrint(context: Context):Boolean

        fun startIdentification(activity: FragmentActivity,callback: FingerPrinterCallback)

        fun startIdentification(context: Context,callback: FingerPrinterCallback)

        fun startIdentification(fingerPrintView: IFingerPrintView,callback: FingerPrinterCallback)

        fun cancelIdentification()

        fun destory()

    }


    interface IFingerPrintView{

        fun success()

        fun error(msg: String)

        fun startShow()

        fun dismiss()

    }
}