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
        if(mediaPlayer==null){
            initPlayer()
            mCurrentFilePath=fileName
            mediaPlayer!!.setDataSource(fileName)
            mediaPlayer!!.prepare()
            mediaPlayer!!.setOnPreparedListener {
                mediaPlayer!!.start()
                VoicePlaySeekHelper.getInstance().listen(mediaPlayer!!,
                        seekListener)
            }
            mediaPlayer!!.setOnCompletionListener(completionListener)
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

    override fun seek(progress: Int) {
        if(mediaPlayer!=null){
            mediaPlayer!!.seekTo(progress)
        }
    }

    override fun playWithSeek(fileName: String, progress: Int) {
        if(mediaPlayer==null||!mediaPlayer!!.isPlaying){
            initPlayer()
            mCurrentFilePath=fileName
            mediaPlayer!!.setDataSource(fileName)
            mediaPlayer!!.prepare()
            mediaPlayer!!.setOnPreparedListener {
                mediaPlayer!!.start()
                mediaPlayer!!.seekTo(progress)
                VoicePlaySeekHelper.getInstance().listen(mediaPlayer!!,
                        seekListener)
            }
            mediaPlayer!!.setOnCompletionListener(completionListener)
        }else{
            mediaPlayer!!.start()
        }
    }


    private fun release() {
        VoicePlaySeekHelper.getInstance().updateSeek(null,null)
        mediaPlayer!!.release()
        mediaPlayer=null
    }

    override fun setCompletionListener(listener: MediaPlayer.OnCompletionListener) {
        completionListener=listener
    }

    override fun setSeekListener(listener: VoicePlaySeekHelper.SeekListener?) {
        seekListener=listener
    }




    private var seekListener: VoicePlaySeekHelper.SeekListener?=null
    private var completionListener: MediaPlayer.OnCompletionListener?=null
}