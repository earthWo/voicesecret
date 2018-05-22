package win.whitelife.voicesecret.base.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity

/**
 */
abstract class BaseActivity<P: BasePresent<V>,V: BaseView>: AppCompatActivity(), LoaderManager.LoaderCallbacks<P>, BaseView {


    protected var present: P?=null

    private val LOAD_ID=1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //加载layout
        var layoutId=getLayout()
        if(layoutId!=0){
            setContentView(getLayout())
        }
        //初始化view
        initView()
        //初始化present
        supportLoaderManager.initLoader(LOAD_ID,null,this)
    }

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 获取layout id
     */
    protected abstract fun getLayout(): Int

    /**
     * 创建present
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<P> {
        return return createPresentLoader(id, args)
    }

    /**
     * 创建present
     */
    private fun createPresentLoader(id: Int, args: Bundle?): Loader<P> {
        return PresentLoader(this, object : LoaderFactory<P,V> {
            override fun create(): P {
               return createPresent()
            }
        })
    }

    abstract fun createPresent(): P
    /**
     * 创建结束赋值
     */
    override fun onLoadFinished(loader: Loader<P>, data: P) {
        present=data
        present!!.attachView(this as V)
        fetchData()
    }

    override fun onStop() {
        super.onStop()
        supportLoaderManager.destroyLoader(LOAD_ID)
    }

    /**
     * 加载数据
     */
    abstract fun fetchData()

    /**
     * 重置时清空
     */
    override fun onLoaderReset(loader: Loader<P>) {
        present=null
    }

    override fun obtainContent(): Context {
        return this
    }


}