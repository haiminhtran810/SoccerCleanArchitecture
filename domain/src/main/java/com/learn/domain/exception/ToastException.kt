package com.learn.domain.exception

import com.learn.domain.annotation.ExceptionType

class ToastException(
    override val code: Int,
    override val message: String
) : CleanException(code, ExceptionType.TOAST, message)