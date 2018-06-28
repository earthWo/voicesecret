package win.whitelife.fingerprint.fingerpage

import android.os.Build
import android.security.keystore.KeyProperties
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.support.annotation.RequiresApi
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator


/**
 * @author wuzefeng
 * @date 2018/6/26
 */
class CryptoObjectHelper @Throws(Exception::class)
constructor() {
    internal val keystore: KeyStore

    init {
        keystore = KeyStore.getInstance(KEYSTORE_NAME)
        keystore.load(null)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(Exception::class)
    fun buildCryptoObject(): FingerprintManagerCompat.CryptoObject {
        val cipher = createCipher(true)
        return FingerprintManagerCompat.CryptoObject(cipher)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(Exception::class)
    internal fun createCipher(retry: Boolean): Cipher {
        val key = GetKey()
        val cipher = Cipher.getInstance(TRANSFORMATION)
        try {
            cipher.init(Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE, key)
        } catch (e: KeyPermanentlyInvalidatedException) {
            keystore.deleteEntry(KEY_NAME)
            if (retry) {
                createCipher(false)
            } else {
                throw Exception("Could not create the cipher for fingerprint authentication.", e)
            }
        }

        return cipher
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(Exception::class)
    internal fun GetKey(): Key {
        val secretKey: Key
        if (!keystore.isKeyEntry(KEY_NAME)) {
            CreateKey()
        }

        secretKey = keystore.getKey(KEY_NAME, null)
        return secretKey
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(Exception::class)
    internal fun CreateKey() {
        val keyGen = KeyGenerator.getInstance(KEY_ALGORITHM, KEYSTORE_NAME)
        val keyGenSpec = KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(BLOCK_MODE)
                .setEncryptionPaddings(ENCRYPTION_PADDING)
                .setUserAuthenticationRequired(true)
                .build()
        keyGen.init(keyGenSpec)
        keyGen.generateKey()
    }

    companion object {
        // This can be key name you want. Should be unique for the app.
        internal val KEY_NAME = "win.whitelife.voicesecret.fingerprint_authentication_key"

        // We always use this keystore on Android.
        internal val KEYSTORE_NAME = "AndroidKeyStore"

        // Should be no need to change these values.
        internal val KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        internal val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        internal val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        internal val TRANSFORMATION = KEY_ALGORITHM + "/" +
                BLOCK_MODE + "/" +
                ENCRYPTION_PADDING
    }
}