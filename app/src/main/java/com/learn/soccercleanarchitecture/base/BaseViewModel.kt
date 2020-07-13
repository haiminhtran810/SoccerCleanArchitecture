package com.learn.soccercleanarchitecture.base

import androidx.lifecycle.ViewModel
import com.learn.domain.usecase.UseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel constructor(private vararg val useCases: UseCase<*, *>?) :
    ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        useCases.let {
            if (it.isNotEmpty()) {
                useCases.forEach { useCase -> useCase?.onCleared() }
            }
        }
        super.onCleared()
    }
}