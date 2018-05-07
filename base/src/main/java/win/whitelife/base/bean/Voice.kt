package win.whitelife.voicesecret.basem.bean

/**
 * @author wuzefeng
 * 2018/5/7
 */
data class Voice(
                 var name: String,      //名字
                 var filePath: String,  //文件本地地址
                 var url: String,       //文件网络地址
                 var title: String,     //文件标题
                 var description: String,
                 var createTime: Long,  //创建时间
                 var updateTime: Long,
                 var id: Long,          //id
                 var duration: Long)    //语音持续时间
