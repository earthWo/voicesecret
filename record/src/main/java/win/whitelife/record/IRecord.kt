package win.whitelife.record

/**
 * @author wuzefeng
 * 2018/5/10
 */
interface IRecord {

    fun startRecord(filePath: String)

    fun pauseRecord()

    fun stopRecord():String


}