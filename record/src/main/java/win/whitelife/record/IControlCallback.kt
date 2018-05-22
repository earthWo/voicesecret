package win.whitelife.record

import android.os.Bundle

/**
 * @author wuzefeng
 * 2018/5/21
 */

interface IControlCallback{

    fun controlCallback(command: Int,bundle: Bundle?)

}