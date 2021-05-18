package com.funcrib.rxjavaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        val list = listOf(1, 3, 5, 64, 645, 35, 5)

        val flowable = Flowable.just(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value ->
                value.map {
                    it*3
                }
            }
        compositeDisposable.add(flowable)
    }

    override fun onDestroy(){
        super.onDestroy()

        compositeDisposable.dispose()
    }
}