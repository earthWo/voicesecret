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
import win.whitelife.record.VoicePlaySeekHelper
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
        val intent= Intent(controlView.getViewContext(),VoiceService::class.java)
        mServiceConnection=object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
            }
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder=service as VoiceService.VoiceBinder
                binder.setControlCallback(object : IControlCallback {
                    override fun controlCallback(command: Int, bundle: Bundle?) {
                        controlView.controlCallback(command,bundle)
                    }
                })
                binder.setSeekListener(object : VoicePlaySeekHelper.SeekListener{
                    override fun getSeek(currentPosition: Int) {
                        if(seekListener!=null){
                            seekListener!!.getSeek(currentPosition)
                        }
                    }
                })
            }
        }
        controlView.getViewContext().bindService(intent,mServiceConnection ,Context.BIND_AUTO_CREATE)
    }


    private var mServiceConnection: ServiceConnection?=null

    override fun unRegisterControlView(controlView: IControlView) {
        if(mServiceConnection!=null){
            controlView.getViewContext().unbindService(mServiceConnection)
        }
    }

    private var seekListener: VoicePlaySeekHelper.SeekListener?=null

    override fun setSeekListener(listener: VoicePlaySeekHelper.SeekListener?) {
        seekListener=listener
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
        intent.putExtra(VoiceCommand.COMMAND_FILE,filePath)
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

        fun getInstance(): IControl{
            if(sInstance==null){
                synchronized(ControlHelper.javaClass){}
                if(sInstance==null){
                    sInstance=ControlHelper()
                }
            }
            return sInstance!!
        }
    }

    override fun release(mode: ControlMode,controlView: IControlView){
        unRegisterControlView(controlView)
        when(mode){
            ControlMode.PLAY,
            ControlMode.PLAY_PAUSE->{
                stopPlay(controlView.getViewContext())
            }
            ControlMode.RECORD,
            ControlMode.RECORD_PAUSE->{
                stopRecord(controlView.getViewContext())
            }
        }
    }

    override fun seek(context: Context,progress: Int) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_SEEK)
        intent.putExtra(VoiceCommand.COMMAND_SEEK_PROGRESS,progress)
        context.startService(intent)
    }


    override fun playWithSeek(context: Context, filePath: String, progress: Int) {
        val intent= Intent(context,VoiceService::class.java)
        intent.putExtra(VoiceCommand.COMMAND,VoiceCommand.COMMAND_PLAY_SEEK)
        intent.putExtra(VoiceCommand.COMMAND_SEEK_PROGRESS,progress)
        intent.putExtra(VoiceCommand.COMMAND_FILE,filePath)
        context.startService(intent)

    }



}