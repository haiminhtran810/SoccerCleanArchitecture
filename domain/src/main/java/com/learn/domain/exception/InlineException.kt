package com.learn.domain.exception

import com.learn.domain.annotation.ExceptionType
import com.learn.domain.model.Tag

class InlineException(
    override val code: Int,
    vararg val tags: Tag
) : CleanException(code, ExceptionType.INLINE, null)