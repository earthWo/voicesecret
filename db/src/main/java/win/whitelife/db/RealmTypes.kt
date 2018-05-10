package win.whitelife.db

import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Retention

/**
 * @author wuzefeng
 * 2018/5/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER)
annotation class RealmTypes(val value: RealmType)