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
import win.whitelife.ui.control.ControlMode

/**
 * @author wuzefeng
 * 2018/5/18
 */
class RecordActivity: BaseActivity<RecordPresent, RecordContract.IRecordView>()
        ,RecordContract.IRecordView,IControlView{

    private var mMode: ControlMode=ControlMode.INIT

    private var currentFilePath: String?=null

    override fun getViewContext(): Context {
        return this
    }

    override fun controlCallback(command: Int, bundle: Bundle?) {
        when(command){
            //结束录音
            VoiceCommand.COMMAND_STOP_RECORD->{
                val filePath=bundle!!.getString(VoiceCommand.COMMAND_FILE)
                present!!.saveVoice(filePath,bundle!!.getLong(VoiceCommand.COMMAND_TIME))
                iv_record.setImageResource(R.drawable.ui_record)
                iv_stop.isEnabled=false
                iv_play.isEnabled=true
                mMode=ControlMode.INIT
                currentFilePath=filePath
            }
            VoiceCommand.COMMAND_START_RECORD->{
                iv_record.setImageResource(R.drawable.ui_pause)
                iv_stop.isEnabled=true
                iv_play.isEnabled=false
                mMode=ControlMode.RECORD
            }
            VoiceCommand.COMMAND_START_PLAY->{
                iv_stop.isEnabled=true
                mMode=ControlMode.PLAY
                iv_record.isEnabled=false
                iv_play.setImageResource(R.drawable.ui_pause)
            }
            VoiceCommand.COMMAND_PAUSE_PLAY->{
                iv_stop.isEnabled=true
                mMode=ControlMode.PLAY_PAUSE
                iv_record.isEnabled=false
                iv_play.setImageResource(R.drawable.ui_pause)
            }
            VoiceCommand.COMMAND_STOP_PLAY->{
                iv_stop.isEnabled=false
                mMode=ControlMode.INIT
                iv_record.isEnabled=true
                iv_play.setImageResource(R.drawable.ui_play)
            }
        }
    }

    override fun initView() {
        ControlHelper.getInstance().registerControlView(this)
        iv_stop.isEnabled=false
        iv_play.isEnabled=false
    }

    override fun onDestroy() {
        ControlHelper.getInstance().release(mMode,this)
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
        if(mMode==ControlMode.RECORD){
            ControlHelper.getInstance().stopRecord(this)
        }else if(mMode==ControlMode.RECORD){
            ControlHelper.getInstance().stopPlay(this)
        }
    }

    fun play(view: View){
       if((mMode==ControlMode.INIT&&currentFilePath!=null)||mMode==ControlMode.PLAY_PAUSE){
           ControlHelper.getInstance().play(this,currentFilePath!!)
       }else if(mMode==ControlMode.PLAY){
           ControlHelper.getInstance().pausePlay(this)
       }
    }

}