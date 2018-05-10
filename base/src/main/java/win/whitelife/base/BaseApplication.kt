package win.whitelife.base

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * @author wuzefeng
 * 2018/5/9
 */
class BaseApplication : Application(){


    override fun onCreate() {
        super.onCreate()
        initDb()
    }



    private fun initDb(){
        Realm.init(this)
        val config=RealmConfiguration.Builder()
                .name("voice_secret")
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }


}
