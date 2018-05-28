package win.whitelife.ui.control

import android.content.Context
import win.whitelife.record.VoicePlaySeekHelper

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

    fun seek(context: Context,progress: Int)

    fun playWithSeek(context: Context,filePath: String,progress: Int)

    fun pauseRecord(context: Context)

    fun registerControlView(controlView: IControlView)

    fun unRegisterControlView(controlView: IControlView)

    fun release(mode: ControlMode,controlView: IControlView)

    fun setSeekListener(listener: VoicePlaySeekHelper.SeekListener?)

    fun registerConnectListener(connectListener: ConnectListener)

}