package com.example.hemapalanisamy.smartbasecode.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.example.hemapalanisamy.smartbasecode.utils.PreferenceHelper.get
import com.example.hemapalanisamy.smartbasecode.utils.PreferenceHelper.set
import java.nio.charset.StandardCharsets
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

object RSACryptography {

     val CRYPTO_METHOD = "RSA"
     val CRYPTO_BITS = 2048
     val CRYPTO_TRANSFORM = "RSA/ECB/OAEPWithSHA1AndMGF1Padding"

    fun PublicKey.key() = Base64.encodeToString(this.encoded, Base64.DEFAULT)!!
    fun PrivateKey.key() = Base64.encodeToString(this.encoded, Base64.DEFAULT)!!


    private lateinit var prefs: SharedPreferences

    private var privateKey: String? = null

    fun generateKeyPair(mainActivity: Context, prefs: SharedPreferences) {
       this.prefs=prefs
        val kp: KeyPair
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(CRYPTO_METHOD)

        kpg.initialize(CRYPTO_BITS)
        kp = kpg.genKeyPair()

        prefs[AppConstant.PUBLIC_KEY] =kp.public.key()
        prefs[AppConstant.PRIVATE_KEY] =kp.private.key()
        this.privateKey =prefs[AppConstant.PRIVATE_KEY]


    }

    fun String.toPublicKey(): PublicKey {
        val keyBytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
        val spec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(CRYPTO_METHOD)

        return keyFactory.generatePublic(spec)
    }// Converts a string to a PrivateKey object

    fun String.toPrivateKey(): PrivateKey {
        val keyBytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
        val spec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(CRYPTO_METHOD)

        return keyFactory.generatePrivate(spec)
    }

    // Encrypts a string
    fun encrypt(message: String?, key: String?): String {
        val encryptedBytes: ByteArray
        val pubKey: PublicKey? = key?.toPublicKey()
        val cipher: Cipher = Cipher.getInstance(CRYPTO_TRANSFORM)

        cipher.init(Cipher.ENCRYPT_MODE, pubKey)
        encryptedBytes = cipher.doFinal(message?.toByteArray(StandardCharsets.UTF_8))

        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }


    // Decrypts a message
   fun decrypt(message: String?): String {
        val decryptedBytes: ByteArray
        val key: PrivateKey? = privateKey?.toPrivateKey()
        val cipher: Cipher = Cipher.getInstance(CRYPTO_TRANSFORM)

        cipher.init(Cipher.DECRYPT_MODE, key)
        decryptedBytes = cipher.doFinal(Base64.decode(message, Base64.DEFAULT))

        return String(decryptedBytes)
    }



}