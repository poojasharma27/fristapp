package com.firstapp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment() ,CoroutineScope{
  //  lateinit var  progressBar : ProgressBar

    lateinit var job : Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job =Job()
     //   progressBarDialog(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
   /* fun progressBarDialog(context: Context?){
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        progressBar.isIndeterminate=true


    }
    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }
*/
}