package saurabh.learn.rxandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun randomPause(string: String) =
            Observable.just(string)
                .delay(5,TimeUnit.SECONDS)
                .doOnDispose { Log.d(string,"Disposed") }
                .doOnComplete {  Log.d(string,"Completed") }

        RxTextView.textChanges(searchEditText)
            .filter { it.length > 2 }
            .observeOn(AndroidSchedulers.mainThread())
            .switchMap {
                randomPause(it.toString())
            }.subscribe {
                Log.d("Ops", "$it Completed")
            }

    }

}




