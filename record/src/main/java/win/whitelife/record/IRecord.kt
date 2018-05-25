package win.whitelife.record

import android.os.Bundle

/**
 * @author wuzefeng
 * 2018/5/10
 */
interface IRecord {

    fun startRecord(filePath: String)

    fun pauseRecord()

    fun stopRecord():Bundle


}