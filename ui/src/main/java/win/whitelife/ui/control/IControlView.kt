package win.whitelife.ui.control

import android.content.Context
import android.os.Bundle

/**
 * @author wuzefeng
 * 2018/5/21
 */
interface IControlView{

    fun getContext(): Context


    fun controlCallback(command: Int,bundle: Bundle?)


}
