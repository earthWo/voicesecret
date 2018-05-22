package win.whitelife.ui.control

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import win.whitelife.base.utils.FileUtil
import win.whitelife.base.utils.VoiceUtil
import win.whitelife.record.IControlCallback
import win.whitelife.record.VoiceCommand
import win.whitelife.record.VoiceService

/**
 * 录音播放控制类
 * @author wuzefeng
 * 2018/5/21
 */
class ControlHelper : IControl {

    private  var controlView: IControlView?=null


    override fun registerControlView(controlView: IControlView) {
        this.controlView=controlView
        val intent= Intent(controlView.getContext(),VoiceService::class.java)
        mServiceConnection=object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
            }
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                (service as VoiceService.VoiceBinder).setControlCallback(object : IControlCallback {
                    override fun controlCallback(command: Int, bundle: Bundle?) {
                        controlView.controlCallback(command,bundle)
                    }
                })
            }
        }
        controlView.getContext().bindService(intent,mServiceConnection ,Context.BIND_AUTO_CREATE)
    }


    private lateinit var mServiceConnection: ServiceConnection

    override fun unRegisterControlView(controlView: IControlView) {
        controlView.getContext().unbindService(mServiceConnection)
    }

    override fun stopPlay(context: Context) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_STOP_PLAY)
        context.startService(intent)
    }

    override fun releasePlay(context: Context) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_RELEASE_PLAY)
        context.startService(intent)
    }

    override fun pausePlay(context: Context) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_PAUSE_PLAY)
        context.startService(intent)
    }

    override fun stopRecord(context: Context) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_STOP_RECORD)
        context.startService(intent)
    }

    override fun releaseRecord(context: Context) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_RELEASE_RECORD)
        context.startService(intent)
    }

    override fun pauseRecord(context: Context) {
    }


    override fun play(context: Context, filePath: String) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_START_PLAY)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_FILE)
        context.startService(intent)
    }

    override fun record(context: Context) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_START_RECORD)
        intent.putExtra(VoiceCommand.COMMAND_INTENT_FILEPATH,FileUtil.getVoiceFilePath())
        context.startService(intent)
    }

    companion object {

        private  var sInstance: ControlHelper?=null

        fun getInstance(): ControlHelper{
            if(sInstance==null){
                synchronized(ControlHelper.javaClass){}
                if(sInstance==null){
                    sInstance=ControlHelper()
                }
            }
            return sInstance!!
        }
    }

}