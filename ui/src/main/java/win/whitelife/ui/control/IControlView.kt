package win.whitelife.ui.control

import android.content.Context
import android.os.Bundle

/**
 * @author wuzefeng
 * 2018/5/21
 */
interface IControlView{

    fun getViewContext(): Context


    fun controlCallback(command: Int,bundle: Bundle?)


}
