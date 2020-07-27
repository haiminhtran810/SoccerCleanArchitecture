package com.learn.domain.model

import com.learn.domain.annotation.TagName

data class Tag(@TagName val name: String, val message: String?)