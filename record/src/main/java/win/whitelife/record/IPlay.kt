package win.whitelife.record

import android.media.MediaPlayer

/**
 * @author wuzefeng
 * 2018/5/11
 */
interface IPlay{



    fun play(fileName: String)

    fun pause()

    fun stop():String

    fun setCompletionListener(listener: MediaPlayer.OnCompletionListener)




}
