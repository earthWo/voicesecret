package win.whitelife.ui.play

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import android.widget.ImageView
import win.whitelife.base.bean.Voice
import win.whitelife.base.utils.DimenUtil
import win.whitelife.record.VoiceCommand
import win.whitelife.ui.R
import win.whitelife.ui.control.ControlHelper
import win.whitelife.ui.control.ControlMode
import win.whitelife.ui.control.IControlView


/**
 * @author wuzefeng
 * 2018/5/24
 */
class PlayFragment  :  DialogFragment(), IControlView{


    private var mode: ControlMode=ControlMode.INIT

    private var voice: Voice?=null


    private var mPlayView: ImageView?=null


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
        mPlayView!!.setOnClickListener { play() }
    }

    private fun play(){
        when(mode){
            ControlMode.PLAY->{}
            ControlMode.PLAY_PAUSE->{}
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

    override fun dismiss() {
        super.dismiss()
        ControlHelper.getInstance().release(mode,this)
    }

    override fun getContext(): Context {
        return context
    }

    override fun controlCallback(command: Int, bundle: Bundle?) {
        when(command){
            VoiceCommand.COMMAND_START_PLAY->{mPlayView!!.setImageResource(R.drawable.ui_dialog_pause)}
            VoiceCommand.COMMAND_PAUSE_PLAY->{mPlayView!!.setImageResource(R.drawable.ui_dialog_play)}
        }
    }


}