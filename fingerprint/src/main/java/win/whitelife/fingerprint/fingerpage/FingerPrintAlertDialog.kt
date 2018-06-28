package win.whitelife.fingerprint.fingerpage

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import win.whitelife.fingerprint.R



/**
 * @author wuzefeng
 * @date 2018/6/26
 */
class FingerPrintAlertDialog(context: Context) : AlertDialog(context),FingerPrintContract.IFingerPrintView {

    override fun success() {
        messageView!!.text="识别成功"
    }

    override fun error(msg: String) {
        messageView!!.text=msg
    }

    fun init(){
        val view: View =LayoutInflater.from(context).inflate(R.layout.dialog_finger_print,null,false)
        messageView=view!!.findViewById(R.id.tv_msg)
        this.setView(view)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }


    private var messageView :TextView?=null


    override fun startShow(){
        this.show()
        val params = window.attributes
        params.windowAnimations = 0
        window!!.setBackgroundDrawableResource(R.color.transparent)
        params.dimAmount = 0.3f //dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
        window.attributes = params
    }


}