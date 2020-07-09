package com.learn.data.local.pref

import com.learn.data.model.Token

interface PrefHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(boolean: Boolean)

    fun getToken(): Token?

    fun setToken(token: Token)
}