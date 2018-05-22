package win.whitelife.base.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @author wuzefeng
 * 2018/5/18
 */
class IntentUtil {


    companion object {

        fun<T: Activity> jumpToActivity(context: Context,page: Class<T>){
            val intent = Intent(context,page)
            context.startActivity(intent)
        }

        fun<T: Activity> jumpToActivity(context: Context,page: Class<T>,bundle: Bundle){
            val intent = Intent(context,page)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        fun<T: Activity> jumpToActivityForResult(context: Activity,page: Class<T>,result: Int){
            val intent = Intent(context,page)
            context.startActivityForResult(intent,result)
        }

        fun<T: Activity> jumpToActivityForResult(context: Activity,page: Class<T>,bundle: Bundle,result: Int){
            val intent = Intent(context,page)
            intent.putExtras(bundle)
            context.startActivityForResult(intent,result)
        }

    }
}