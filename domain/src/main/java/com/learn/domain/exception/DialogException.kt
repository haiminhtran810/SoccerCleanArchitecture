package com.learn.domain.exception

import com.learn.domain.annotation.ExceptionType
import com.learn.domain.model.Dialog

class DialogException(
    override val code: Int,
    val dialog: Dialog
) : CleanException(code, ExceptionType.DIALOG, null)