package win.whitelife.ui.play

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.AppCompatSeekBar
import android.view.*
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import win.whitelife.base.bean.Voice
import win.whitelife.base.utils.DimenUtil
import win.whitelife.base.utils.TimeUtil
import win.whitelife.record.VoiceCommand
import win.whitelife.record.VoicePlaySeekHelper
import win.whitelife.ui.R
import win.whitelife.ui.control.ControlHelper
import win.whitelife.ui.control.ControlMode
import win.whitelife.ui.control.IControlView


/**
 * @author wuzefeng
 * 2018/5/24
 */
class PlayFragment : DialogFragment(), IControlView,VoicePlaySeekHelper.SeekListener, SeekBar.OnSeekBarChangeListener{

    private var mode: ControlMode=ControlMode.INIT

    private var voice: Voice?=null

    private var mPlayView: ImageView?=null

    private var mSeekBar: AppCompatSeekBar?=null

    companion object {
        fun show(manager: FragmentManager?,voice: Voice): PlayFragment{
            val playFragment=PlayFragment()
            playFragment.voice=voice
            playFragment.show(manager!!,"play_dialog")
            return playFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_play,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.iv_dialog_cancel).setOnClickListener { dismiss() }
        mPlayView=view.findViewById(R.id.iv_dialog_play)
        mSeekBar=view.findViewById(R.id.sb_voice)
        mPlayView!!.setOnClickListener { play() }
        view.findViewById<TextView>(R.id.tv_voice_name).text=voice!!.name
        ControlHelper.getInstance().setSeekListener(this)
        ControlHelper.getInstance().registerControlView(this)
        view.findViewById<TextView>(R.id.tv_voice_time).text=TimeUtil.decodeVoiceTime(voice!!.duration)
        mSeekBar!!.setOnSeekBarChangeListener(this)
        mSeekBar!!.max= voice!!.duration.toInt()
    }




    private fun play(){
        when(mode){
            ControlMode.INIT->{
                if(seekUpdate){
                    ControlHelper.getInstance().playWithSeek(context!!,voice!!.filePath,mSeekBar!!.progress)
                }else{
                    ControlHelper.getInstance().play(context!!,voice!!.filePath)
                }
                seekUpdate=false
            }
            ControlMode.PLAY_PAUSE->{
                if(seekUpdate){
                    ControlHelper.getInstance().seek(context!!,mSeekBar!!.progress)
                }
                ControlHelper.getInstance().play(context!!,voice!!.filePath)
                seekUpdate=false
            }
            ControlMode.PLAY->{ControlHelper.getInstance().pausePlay(context!!)}
        }
    }

    override fun onStart() {
        val params = dialog.window.attributes
        params.width= ViewGroup.LayoutParams.MATCH_PARENT
        params.height= DimenUtil.dp2px(context!!,120f)
        params.windowAnimations = 0
        dialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        params.dimAmount = 0.3f //dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
        dialog.window.attributes = params
        super.onStart()
    }


    override fun getViewContext(): Context {
        return context!!
    }

    override fun controlCallback(command: Int, bundle: Bundle?) {
        when(command){
            VoiceCommand.COMMAND_START_PLAY->{
                mPlayView!!.setImageResource(R.drawable.ui_dialog_pause)
                mode=ControlMode.PLAY
            }
            VoiceCommand.COMMAND_PAUSE_PLAY->{
                mPlayView!!.setImageResource(R.drawable.ui_dialog_play)
                mode=ControlMode.PLAY_PAUSE
            }
            VoiceCommand.COMMAND_STOP_PLAY->{
                mPlayView!!.setImageResource(R.drawable.ui_dialog_play)
                mSeekBar!!.progress = 0
                mode=ControlMode.INIT
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        ControlHelper.getInstance().release(mode,this)
        ControlHelper.getInstance().setSeekListener(null)
    }


    private var seekUpdate=false

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekUpdate=true
        when(mode){
            ControlMode.PLAY->{
                ControlHelper.getInstance().seek(context!!,seekBar!!.progress)
            }
        }

    }

    override fun getSeek(currentPosition: Int) {
        mSeekBar!!.progress = currentPosition
    }

}