package win.whitelife.ui.record

import win.whitelife.voicesecret.base.main.BasePresent
import win.whitelife.voicesecret.base.main.BaseView

/**
 * @author wuzefeng
 * 2018/5/18
 */
class RecordContract {

    abstract class IRecordPresent :BasePresent<IRecordView>(){

        abstract fun saveVoice(filePath: String,time: Long)

    }

    interface IRecordView : BaseView

}