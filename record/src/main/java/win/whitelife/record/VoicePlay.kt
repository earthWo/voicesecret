package win.whitelife.record

/**
 * @author wuzefeng
 * 2018/5/11
 */
class VoicePlay: IPlay {

    private constructor()

    companion object {

        @Volatile
        private var sInstance: VoicePlay?=null

        fun getInstance(): VoicePlay{

            if(sInstance==null){
                synchronized(VoicePlay::class.java){
                    if(sInstance==null){
                        sInstance= VoicePlay()
                    }
                }
            }
            return sInstance!!
        }

    }





    override fun play(fileName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun realse() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}