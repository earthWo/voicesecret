package win.whitelife.record

import android.media.MediaPlayer

/**
 * @author wuzefeng
 * 2018/5/11
 */
class VoicePlay: IPlay {


    private  var mediaPlayer:MediaPlayer?=null


    private var mCurrentFilePath: String?=null

    private constructor()

    companion object {

        @Volatile
        private var sInstance: VoicePlay?=null

        fun getInstance(): VoicePlay{

            if(sInstance==null){
                synchronized(VoicePlay::class.java){
                    if(sInstance==null){
                        sInstance= VoicePlay()
                    }
                }
            }
            return sInstance!!
        }
    }

    override fun play(fileName: String) {
        if(mediaPlayer==null||!mediaPlayer!!.isPlaying){
            initPlayer()
            mCurrentFilePath=fileName
            mediaPlayer!!.setDataSource(fileName)
            mediaPlayer!!.prepare()
            mediaPlayer!!.setOnPreparedListener {
                mediaPlayer!!.start()
            }
        }else{
            mediaPlayer!!.start()
        }
    }


    private fun initPlayer(){
        if (mediaPlayer==null){
            mediaPlayer= MediaPlayer()
        }
        mediaPlayer!!.reset()
    }

    override fun pause() {
        mediaPlayer!!.pause()
    }

    override fun stop():String {
        mediaPlayer!!.stop()
        release()
        return mCurrentFilePath!!
    }

    private fun release() {
        mediaPlayer!!.release()
        mediaPlayer=null
    }

    override fun setCompletionListener(listener: MediaPlayer.OnCompletionListener) {
        mediaPlayer!!.setOnCompletionListener(listener)
    }



}