package win.whitelife.ui.record

import android.content.Context
import android.os.Bundle
import android.view.View
import win.whitelife.record.VoiceCommand
import win.whitelife.ui.R
import win.whitelife.ui.control.ControlHelper
import win.whitelife.ui.control.IControlView
import win.whitelife.voicesecret.base.main.BaseActivity
import kotlinx.android.synthetic.main.activity_record.*

/**
 * @author wuzefeng
 * 2018/5/18
 */
class RecordActivity: BaseActivity<RecordPresent, RecordContract.IRecordView>()
        ,RecordContract.IRecordView,IControlView{


    override fun getContext(): Context {
        return this
    }

    override fun controlCallback(command: Int, bundle: Bundle?) {
        when(command){
            //结束录音
            VoiceCommand.COMMAND_STOP_RECORD->{
                val filePath=bundle!!.getString(VoiceCommand.COMMAND_FILE)
                present!!.saveVoice(filePath)
                iv_record.setImageResource(R.drawable.ui_record)
                iv_stop.isEnabled=false
            }
            VoiceCommand.COMMAND_START_RECORD->{
                iv_record.setImageResource(R.drawable.ui_pause)
                iv_stop.isEnabled=true
            }
        }
    }


    override fun initView() {
        ControlHelper.getInstance().registerControlView(this)
        iv_stop.isEnabled=false
        iv_play.isEnabled=false
    }

    override fun onDestroy() {
        ControlHelper.getInstance().unRegisterControlView(this)
        super.onDestroy()
    }

    override fun getLayout(): Int {
        return R.layout.activity_record
    }

    override fun createPresent(): RecordPresent {
        return RecordPresent()
    }



    override fun fetchData() {
    }


    fun back(view: View){
        finish()
    }

    fun record(view: View){
        ControlHelper.getInstance().record(this)
    }
    fun stop(view: View){
        ControlHelper.getInstance().stopRecord(this)
    }
}