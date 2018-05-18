package win.whitelife.record

import android.app.IntentService
import android.content.Intent

/**
 * @author wuzefeng
 * 2018/5/15
 */
class VoiceService(name: String?) : IntentService(name) {


    override fun onHandleIntent(intent: Intent?) {

        when(intent!!.getIntExtra(VoiceCommand.COMMAND,0)){
            VoiceCommand.COMMAND_START_RECORD->{
                val filePath=intent.getStringExtra(VoiceCommand.COMMAND_INTENT_FILEPATH)
                VoiceRecord.getInstance().startRecord(filePath)
            }
            VoiceCommand.COMMAND_STOP_RECORD->{
                VoiceRecord.getInstance().stopRecord()
            }
            VoiceCommand.COMMAND_RELEASE_RECORD->{
                VoiceRecord.getInstance().release()
            }
            VoiceCommand.COMMAND_START_PLAY->{
                val fileName=intent!!.getStringExtra(VoiceCommand.COMMAND_FILE)
                VoicePlay.getInstance().play(fileName)
            }
            VoiceCommand.COMMAND_PAUSE_PLAY->{
                VoicePlay.getInstance().pause()
            }
            VoiceCommand.COMMAND_STOP_PLAY->{
                VoicePlay.getInstance().stop()
            }
            VoiceCommand.COMMAND_RELEASE_PLAY->{
                VoicePlay.getInstance().realse()
            }
        }
    }


}