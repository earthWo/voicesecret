package win.whitelife.base.utils

import win.whitelife.base.bean.Voice
import java.util.*

/**
 * @author wuzefeng
 * 2018/5/21
 */
class VoiceUtil {


    companion object {

        fun createVoiceId(): Long{
            return UUID.randomUUID().mostSignificantBits
        }



        fun createVoice(): Voice{

            val voice= Voice()

            voice.id=createVoiceId()

            return voice

        }


    }





}