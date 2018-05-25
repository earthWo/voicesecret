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

        fun decodeVoiceTime(time: Long):String{
            val second=time/1000%60//秒
            val mintus=time/1000/60
            val hours=time/1000/60/60
            var hoursString=""
            var minutesString=""
            var secondString=""
            if(hours in 0..9){
                hoursString= "0$hours:"
            }else if(hours>10){
                hoursString=hours.toString()+":"
            }
            if(mintus in 0..9){
                minutesString= "0$mintus:"
            }else if(mintus>10){
                minutesString=mintus.toString()+":"
            }
            if(second in 0..9){
                secondString= "0$second"
            }else if(second>10){
                secondString=second.toString()
            }
            return hoursString+minutesString+secondString
        }

    }
}