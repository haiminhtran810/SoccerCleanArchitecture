package com.learn.domain.exception

import com.learn.domain.annotation.ExceptionType

open class CleanException(
    open val code: Int,
    @ExceptionType val exceptionType: Int,
    override val message: String?
) : Throwable(message)