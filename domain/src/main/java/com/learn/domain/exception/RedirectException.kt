package com.learn.domain.exception

import com.learn.domain.annotation.ExceptionType
import com.learn.domain.model.Redirect

class RedirectException(
    override val code: Int,
    val redirect: Redirect
) : CleanException(code, ExceptionType.REDIRECT, null)