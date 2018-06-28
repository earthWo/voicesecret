package win.whitelife.ui.mainpage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import win.whitelife.ui.R
import win.whitelife.voicesecret.base.main.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import win.whitelife.base.bean.Voice
import win.whitelife.base.utils.TimeUtil
import win.whitelife.ui.play.PlayFragment

class MainActivity : BaseActivity<MainPresent, MainContract.IMainView>(),MainContract.IMainView
        ,View.OnClickListener{

    private var voices: List<Voice>?=null

    override fun bindData(voices: List<Voice>) {
        this.voices=voices
        mAdapter.notifyDataSetChanged()
    }

    override fun createPresent(): MainPresent {
        return MainPresent()
    }

    override fun initView() {
        rv_voice.layoutManager=LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ColorDrawable(Color.WHITE))
        rv_voice.addItemDecoration(dividerItemDecoration)
        rv_voice.adapter=mAdapter
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun fetchData() {
        present!!.fetchData()
    }

    fun jumpToRecord(view: View){
        present!!.jumpToRecord()
    }

    fun jumpToSearch(view: View){
        present!!.jumpToSearch()
    }


    fun jumpToSetting(view: View){
        present!!.jumpToSetting()
    }

    inner class VoiceHolder(view: View): RecyclerView.ViewHolder(view) {
        var titleView: TextView = view.findViewById(R.id.tv_voice_title)
        var timeView: TextView = view.findViewById(R.id.tv_voice_time)
        var rootView=view
        init {
            view.setOnClickListener(this@MainActivity)
        }
    }

    private val mAdapter: RecyclerView.Adapter<VoiceHolder> = object : RecyclerView.Adapter<VoiceHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoiceHolder {
            return VoiceHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.item_voice,parent,false))
        }

        override fun getItemCount(): Int {
            return if(voices==null){
                0
            }else{
                voices!!.size
            }
        }

        override fun onBindViewHolder(holder: VoiceHolder, position: Int) {
            holder.titleView.text=voices!![position].title
            holder.timeView.text=TimeUtil.defaultDecodeTime(voices!![position].createTime)
            holder.rootView.tag=voices!![position]
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cl_voice->{
                PlayFragment.show(supportFragmentManager,v.tag as Voice)
            }
        }
    }

}