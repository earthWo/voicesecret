package win.whitelife.base.utils

import android.content.Context

/**
 * @author wuzefeng
 * 2018/5/24
 */
object DimenUtil {

    fun px2dp(context: Context,px: Float):Int{
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun dp2px(context: Context,dp: Float):Int{
         val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}