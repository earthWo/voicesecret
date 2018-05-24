package win.whitelife.record

import android.annotation.TargetApi
import android.media.MediaRecorder
import android.os.Build
import win.whitelife.base.utils.FileUtil

/**
 * 录音
 * @author wuzefeng
 * 2018/5/10
 */
class VoiceRecord: IRecord{

    private var mMediaRecorder: MediaRecorder?=null


    private var filePath: String?=null

    companion object {

        @Volatile
        private var instance: VoiceRecord?=null

        private const val AUDIO_SAMPLING_RATE=20000

        private const val AUDIO_ENCODING_BIT_RATE=96000

        internal fun getInstance(): VoiceRecord{
            if(instance==null){
                synchronized(VoiceRecord::class.java){
                    if(instance==null){
                        instance=VoiceRecord()
                    }
                }
            }
            return instance!!
        }
    }

    private constructor()


    /**
     * 开始录音
     */
    override fun startRecord(filePath: String){
        release()
        createMediaRecorder()
        //数据来源麦克风
        mMediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        //保存数据格式
        mMediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        this.filePath=filePath
        mMediaRecorder!!.setOutputFile(filePath)
        //设置声音数据编码格式,音频通用格式是AAC
        mMediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

        mMediaRecorder!!.prepare()
        mMediaRecorder!!.start()
    }


    private fun createMediaRecorder(){
        if(mMediaRecorder==null){
            mMediaRecorder = MediaRecorder()
        }
    }


    @TargetApi(Build.VERSION_CODES.N)
    override fun pauseRecord(){
        mMediaRecorder!!.pause()
    }

    override fun stopRecord(): String {
        mMediaRecorder!!.stop()
        release()
        return filePath!!
    }

    private fun release() {
        if(mMediaRecorder!=null){
            mMediaRecorder!!.release()
            mMediaRecorder=null
        }
    }

}
