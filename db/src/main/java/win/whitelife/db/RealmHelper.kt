package win.whitelife.db

import io.realm.*
import io.realm.kotlin.deleteFromRealm
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

/**
 * @author wuzefeng
 * 2018/5/10
 */
class RealmHelper {

    companion object {
        val realm=Realm.getDefaultInstance()



        fun <T> create(clazz: Class<T>): T{

            return Proxy.newProxyInstance(clazz.classLoader, arrayOf<Class<*>>(clazz), object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
                    if(method!!.declaringClass==Object::class.java){
                        return (method.invoke(this,args) as T)!!
                    }
                    realm.beginTransaction()
                    val any=parseMethod(method,args)
                    realm.commitTransaction()
                    return any
                }


            }) as T
        }



        private fun parseMethod( method: Method, args: Array<out Any>?): Any {

            val realmType=method.getAnnotation(RealmTypes::class.java).value
            when (realmType) {
                RealmType.ADD -> {
                    return parseAddMethod(args!!)
                }
                RealmType.DELETE -> {
                    return parseDeleteMethod(args!!)
                }
                RealmType.SEARCH -> {
                    return parseSearchMethod(args!!)
                }
                RealmType.UPDATE -> {
                    return parseUpdateMethod(args!!)
                }
                RealmType.MODIFY -> {
                    return parseModifyMethod(args!!)
                }
            }
            return EMPTY
        }

        private fun parseModifyMethod(args: Array<out Any>): Any {
            if(args.isNotEmpty()) {
                val c = args[0] as Class<*>
                val realmObject = args[1] as RealmObject
                val names = args[2] as Array<String>
                val classes = args[3] as Array<Class<*>>
                val values = args[4] as Array<Any>
                for (i in names.indices) {
                    try {
                        val method = c.getDeclaredMethod(names[i], classes[i])
                        method.invoke(realmObject, values[i])
                    } catch (e: NoSuchMethodException) {
                        e.printStackTrace()
                    } catch (e: InvocationTargetException) {
                        e.printStackTrace()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }
                }
                return realmObject
            }

            return EMPTY
        }

        private fun parseUpdateMethod(args: Array<out Any>): Any {

            if(args.isNotEmpty()){
                return when{
                    args.size==1&&args[0].javaClass==List<*>::javaClass->{
                        realm.copyToRealmOrUpdate(args[0] as Iterable<RealmModel>)
                    }
                    else->{
                        for(model in args[0] as Array<Any>){
                            realm.copyToRealmOrUpdate(model as RealmModel)
                        }
                        args[0]
                    }
                }
            }

            return EMPTY
        }

        private fun parseSearchMethod(args: Array<out Any>): Any {
            if(args.isNotEmpty()){
                when{
                    args[0] is Class<*>&&args.size===1->{
                        return realm.where(args[0] as Class<RealmModel>).findAll()
                    }
                    args[0].javaClass is Class<*>&&args.size===3->{

                        val conditions:Array<String> = args[1] as Array<String>

                        val parameter:Array<Any> = args[2] as Array<Any>

                        if(parameter.size!=conditions.size){
                            throw Exception("条件和参数的数量不一致")
                        }
                        val result: RealmQuery<RealmModel> = realm.where(args[0] as Class<RealmModel>)

                        for(i in 0 until conditions.size){
                            when{
                                parameter[i].javaClass==Long.javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Long)
                                }
                                parameter[i].javaClass==Int.javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Int)
                                }
                                parameter[i].javaClass==String.javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as String)
                                }
                                parameter[i].javaClass==Float.javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Float)
                                }
                                parameter[i].javaClass==Double.javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Double)
                                }
                                parameter[i].javaClass==Short::javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Short)
                                }
                                parameter[i].javaClass==Byte.javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Byte)
                                }
                                parameter[i].javaClass==Array<Byte>::javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as ByteArray)
                                }
                                parameter[i].javaClass==Boolean::javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Boolean)
                                }
                                parameter[i].javaClass==Date::javaClass->{
                                    result.equalTo(conditions[i],parameter[i] as Date)
                                }
                            }
                            return result.findAll()
                        }
                    }
                }
            }
            return EMPTY
        }

        private fun parseAddMethod(args: Array<out Any>): Any {
            if(args.isNotEmpty()){
                //是否是list
                when {
                    args[0].javaClass is List<*> -> return realm.copyToRealm(args[0] as Iterable<RealmModel>)
                    args[0].javaClass is Array<*> -> return realm.copyToRealm(Arrays.asList(args[0]) as Iterable<RealmModel>)
                    else -> for(arg in args){
                        realm.copyToRealm(arg as RealmModel)
                    }
                }
            }
            return EMPTY
        }



        private fun parseDeleteMethod(args: Array<out Any>): Any{
            if(args.isNotEmpty()){
                when{
                //删除list
                    args[0].javaClass is List<*>->{
                        //只有数组全部删除
                        if(args.size==1){
                            //全部删除
                            if(args[0].javaClass is RealmResults<*>){
                                (args[0] as RealmResults<*>).deleteAllFromRealm()
                            }else{
                                //普通的列表逐个删除
                                for(a in args[0] as List<*>){
                                    (a as RealmModel).deleteFromRealm()
                                }
                            }
                            return args
                        }else{
                            //删除列表中的部分数据
                            for( p in 1 until args.size){
                                ((args[0] as List<*>)[args[p] as Int] as RealmModel).deleteFromRealm()
                            }
                            return args[0]
                        }
                    }
                //删除array
                    args[0].javaClass is Array<*>->{
                        //只有数组全部删除
                        return if(args.size==1){
                            //全部删除
                            //普通的列表逐个删除
                            for(a in args[0] as List<*>){
                                (a as RealmModel).deleteFromRealm()
                            }
                            args
                        }else{
                            //删除列表中的部分数据
                            for( p in 1 until args.size){
                                ((args[0] as Array<*>)[args[p] as Int] as RealmModel).deleteFromRealm()
                            }
                            args[0]
                        }
                    }
                //删除个别数据
                    else->{
                        for(arg in args){
                            (arg as RealmModel).deleteFromRealm()
                        }
                        return args
                    }
                }
            }

            return EMPTY
        }


        private val EMPTY=Object()


    }




}