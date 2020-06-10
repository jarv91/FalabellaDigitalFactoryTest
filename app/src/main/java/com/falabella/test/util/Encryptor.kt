package com.falabella.test.util

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Encryptor{

    private val secret = "qwertyuioplkjhgfdsazxcvbnmghytre"

    private fun cipher(opmode:Int):Cipher{
        val c = Cipher.getInstance("AES/GCM/NoPadding")
        val sk = SecretKeySpec(secret.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secret.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opmode, sk, iv)
        return c
    }
    fun encrypt(str:String):String{
        val encrypted = cipher(Cipher.ENCRYPT_MODE).doFinal(str.toByteArray(Charsets.UTF_8))
        return String(Base64.encode(encrypted, Base64.DEFAULT))
    }
    fun decrypt(str:String):String{
        val byteStr = Base64.decode(str.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        return String(cipher(Cipher.DECRYPT_MODE).doFinal(byteStr))
    }
}