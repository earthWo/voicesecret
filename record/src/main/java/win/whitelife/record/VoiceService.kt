package win.whitelife.record

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Bundle
import android.os.IBinder

/**
 * @author wuzefeng
 * 2018/5/15
 */
class VoiceService : Service() ,MediaPlayer.OnCompletionListener{

    override fun onBind(intent: Intent?): IBinder? {
        return VoiceBinder()
    }

    private lateinit var mControlCallback: IControlCallback

    inner class VoiceBinder : Binder(){

        fun setControlCallback(controlCallback: IControlCallback){
            this@VoiceService.mControlCallback=controlCallback
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        onHandleIntent(intent)
        return super.onStartCommand(intent, flags, startId)
    }


    private fun controlCallback(command: Int, bundle: Bundle?){
        if(mControlCallback!=null){
            mControlCallback.controlCallback(command,bundle)
        }
    }



    private fun onHandleIntent(intent: Intent?) {

        when(intent!!.getIntExtra(VoiceCommand.COMMAND,0)){
            VoiceCommand.COMMAND_START_RECORD->{
                val filePath=intent.getStringExtra(VoiceCommand.COMMAND_INTENT_FILEPATH)
                VoiceRecord.getInstance().startRecord(filePath)
                val bundle=Bundle()
                bundle.putString(VoiceCommand.COMMAND_FILE,filePath)
                controlCallback(VoiceCommand.COMMAND_START_RECORD,bundle)
            }
            VoiceCommand.COMMAND_STOP_RECORD->{
                val filePath= VoiceRecord.getInstance().stopRecord()
                val bundle=Bundle()
                bundle.putString(VoiceCommand.COMMAND_FILE,filePath)
                controlCallback(VoiceCommand.COMMAND_STOP_RECORD,bundle)
            }
            VoiceCommand.COMMAND_START_PLAY->{
                val fileName=intent!!.getStringExtra(VoiceCommand.COMMAND_FILE)
                VoicePlay.getInstance().play(fileName)
                controlCallback(VoiceCommand.COMMAND_START_PLAY,null)
                VoicePlay.getInstance().setCompletionListener(this)
            }
            VoiceCommand.COMMAND_PAUSE_PLAY->{
                VoicePlay.getInstance().pause()
            }
            VoiceCommand.COMMAND_STOP_PLAY->{
                VoicePlay.getInstance().stop()
            }
        }
    }


    override fun onCompletion(mp: MediaPlayer?) {
        val filePath= VoicePlay.getInstance().stop()
        val bundle=Bundle()
        bundle.putString(VoiceCommand.COMMAND_FILE,filePath)
        controlCallback(VoiceCommand.COMMAND_STOP_PLAY,bundle)
    }

}