package win.whitelife.ui.record

import win.whitelife.base.utils.VoiceUtil
import win.whitelife.ui.db.DbHelper

/**
 * @author wuzefeng
 * 2018/5/18
 */
class RecordPresent: RecordContract.IRecordPresent() {



    override fun saveVoice(filePath: String) {

        val voice=VoiceUtil.createVoice()
        voice.filePath=filePath
        voice.createTime=System.currentTimeMillis()
        voice.name=filePath.substring(filePath.lastIndexOf("/")+1)
        voice.title="默认录音"+voice.name
        DbHelper.getInstance().realm.copyToRealm(voice)

    }


}