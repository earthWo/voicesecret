package win.whitelife.base.utils

import android.util.TimeUtils
import java.text.SimpleDateFormat

/**
 * @author wuzefeng
 * 2018/5/22
 */
class TimeUtil {


    companion object {

        fun decodeTime(time: Long, style: String): String{
            val timeFormat : SimpleDateFormat= SimpleDateFormat(style)
            return timeFormat.format(time)
        }


        fun defaultDecodeTime(time: Long):String{
           return decodeTime(time,"yyyy.MM.dd HH:mm")
        }



    }
}