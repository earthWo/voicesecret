package win.whitelife.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_search.*
import win.whitelife.base.bean.Voice
import win.whitelife.base.utils.TimeUtil
import win.whitelife.ui.R
import win.whitelife.ui.play.PlayFragment
import win.whitelife.voicesecret.base.main.BaseActivity

/**
 * @author wuzefeng
 * 2018/5/28
 */
class SearchActivity : BaseActivity<SearchPresent,SearchContract.ISearchView>(),SearchContract.ISearchView,View.OnClickListener{

    private var voices: List<Voice>?=null

    override fun initView() {
        rv_voice.layoutManager= LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ColorDrawable(Color.WHITE))
        rv_voice.addItemDecoration(dividerItemDecoration)
        rv_voice.adapter=mAdapter
    }

    override fun getLayout(): Int {
        return R.layout.activity_search
    }

    override fun createPresent(): SearchPresent {
        return SearchPresent()
    }

    override fun fetchData() {
        et_voice.setOnKeyListener { _, keyCode, event ->
            if(event!!.action==KeyEvent.ACTION_UP&&keyCode==KeyEvent.KEYCODE_ENTER){
                present!!.search(findViewById<AppCompatEditText>(R.id.et_voice).text.toString())
            }
            false
        }
    }

    fun back(view: View){
        finish()
    }

    override fun bindDate(list: List<Voice>) {
        voices=list
        mAdapter.notifyDataSetChanged()
    }

    inner class VoiceHolder(view: View): RecyclerView.ViewHolder(view) {
        var titleView: TextView = view.findViewById(R.id.tv_voice_title)
        var timeView: TextView = view.findViewById(R.id.tv_voice_time)
        var rootView=view
        init {
            view.setOnClickListener(this@SearchActivity)
        }
    }

    private val mAdapter: RecyclerView.Adapter<SearchActivity.VoiceHolder> = object : RecyclerView.Adapter<SearchActivity.VoiceHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActivity.VoiceHolder {
            return VoiceHolder(LayoutInflater.from(this@SearchActivity).inflate(R.layout.item_voice,parent,false))
        }

        override fun getItemCount(): Int {
            return if(voices==null){
                0
            }else{
                voices!!.size
            }
        }

        override fun onBindViewHolder(holder: SearchActivity.VoiceHolder, position: Int) {
            holder.titleView.text=voices!![position].title
            holder.timeView.text= TimeUtil.defaultDecodeTime(voices!![position].createTime)
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