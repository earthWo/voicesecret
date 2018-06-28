package win.whitelife.fingerprint.fingerpage

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
class FingerPrintDialog : DialogFragment(),FingerPrintContract.IFingerPrintView {

    override fun success() {
        messageView!!.text="识别成功"
    }

    override fun error(msg: String) {
        messageView!!.text=msg
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.dialog_finger_print,container,false)
    }


    private var messageView :TextView?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageView=view!!.findViewById(R.id.tv_msg)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }



    override fun startShow(){
        this.show(manager,"fp")
    }

    var manager: FragmentManager?=null

    override fun onStart() {
        val params = dialog.window.attributes
        params.width= ViewGroup.LayoutParams.MATCH_PARENT
        params.height= dp2px(120)
        params.windowAnimations = 0
        dialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        params.dimAmount = 0.3f //dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
        dialog.window.attributes = params
        super.onStart()
    }

    fun dp2px(dp:Int) : Int{
        val  scale = context!!.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }


}