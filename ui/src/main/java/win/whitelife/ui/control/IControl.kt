package win.whitelife.ui.control

import android.content.Context

/**
 * @author wuzefeng
 * 2018/5/21
 */
interface IControl {

    fun play(context: Context,filePath: String)

    fun record(context: Context)

    fun stopPlay(context: Context)

    fun releasePlay(context: Context)

    fun pausePlay(context: Context)

    fun stopRecord(context: Context)

    fun releaseRecord(context: Context)

    fun pauseRecord(context: Context)

    fun registerControlView(controlView: IControlView)

    fun unRegisterControlView(controlView: IControlView)

}