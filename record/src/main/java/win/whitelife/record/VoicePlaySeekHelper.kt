package win.whitelife.record

import android.media.MediaPlayer
import java.util.concurrent.*

/**
 * @author wuzefeng
 * 2018/5/25
 */
class VoicePlaySeekHelper private constructor() {

    private var executor:ThreadPoolExecutor = ThreadPoolExecutor(0, Int.MAX_VALUE,1,TimeUnit.SECONDS, SynchronousQueue<Runnable>(), ThreadPoolExecutor.AbortPolicy())

    companion object {

        private var sInstance:VoicePlaySeekHelper?=null

        fun getInstance():VoicePlaySeekHelper{
            if(sInstance==null){
                synchronized(VoicePlaySeekHelper::class.java){
                    if(sInstance==null){
                        sInstance=VoicePlaySeekHelper()
                    }
                }
            }
            return sInstance!!
        }
    }

    @Volatile
    private var needSeekListener=true



    fun listen(mediaPlayer: MediaPlayer,listener: SeekListener?){
        if(listener!=null){
            executor.execute {
                needSeekListener=true
                while (mediaPlayer!=null&&needSeekListener){
                    updateSeek(mediaPlayer,listener)
                    Thread.sleep(100)
                }
            }
        }
    }

    @Synchronized
    fun updateSeek(mediaPlayer: MediaPlayer?,listener: SeekListener?){
        if(mediaPlayer==null||listener==null){
            needSeekListener=false
            return
        }
        if(needSeekListener){
            listener.getSeek(mediaPlayer.currentPosition)
        }
    }

    interface SeekListener{
        fun getSeek(currentPosition : Int)
    }


    interface SeekControlListener{

        fun getSeek(currentPosition : Int)

        fun needListener(): Boolean
    }

}