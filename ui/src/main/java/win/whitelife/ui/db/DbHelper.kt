package win.whitelife.ui.db

import win.whitelife.db.IRealm
import win.whitelife.db.RealmHelper


/**
 * @author wuzefeng
 * 2018/5/21
 */

class DbHelper{

    var realm: IRealm = RealmHelper.create(IRealm::class.java)

    private constructor()

    companion object {

        @Volatile
        private  var sInstance : DbHelper?=null

        fun getInstance(): DbHelper{
            if(sInstance==null){
                synchronized(DbHelper.javaClass){
                    if(sInstance==null){
                        sInstance=DbHelper()
                    }
                }
            }
            return sInstance!!
        }
    }
}