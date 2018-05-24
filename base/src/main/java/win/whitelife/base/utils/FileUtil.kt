package win.whitelife.base.utils

import android.content.Context
import android.util.TimeUtils
import java.io.File

/**
 * @author wuzefeng
 * 2018/5/11
 */

class FileUtil{


    companion object {



        private var FILE_ROOT_PATH: String?=null


        private const val VOICE_TYPE=".aac"


        fun init(context: Context){
            FILE_ROOT_PATH =  context.filesDir.absolutePath
        }



        private fun getVoiceRootPath(): String?{
            return FILE_ROOT_PATH
        }


        private fun createVoiceFileName(): String{
            val time=System.currentTimeMillis()
            return getVoiceRootPath()+File.separator+"voice"+time+VOICE_TYPE
        }


        private fun createVoiceFile(): File{
            val fileName=createVoiceFileName()
            val file=File(fileName)
            if(!file.parentFile.exists()){
                file.parentFile.mkdirs()
            }
            if(!file.exists()){
                file.createNewFile()
            }
            return file
        }

        fun getVoiceFilePath(): String{
            return createVoiceFile().absolutePath
        }


    }

}