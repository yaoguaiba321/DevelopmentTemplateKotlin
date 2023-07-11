package com.waterflowflow.developmenttemplatekotlin.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.waterflowflow.developmenttemplatekotlin.R
import com.waterflowflow.developmenttemplatekotlin.logic.network.ServiceCreator
import com.waterflowflow.developmenttemplatekotlin.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_start.*
import java.lang.Exception

class StartActivity : AppCompatActivity() {

    var thread: timeThread? = null

    var MSG = 1

    val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){
                MSG -> {
                    btnSkip.text = "跳过${msg.arg1}"
                    if(msg.arg1 <= 0){
                        //先销毁开屏界面
                        finish()
                        //再跳转去主界面
                        val intent = Intent(this@StartActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    inner class timeThread: Thread(){
        override fun run() {
            super.run()
            var i = 3//默认3秒开屏时长，不建议修改
            while(i >= 0){
                var msg = Message()
                msg.what = MSG
                msg.arg1 = i
                handler.sendMessage(msg)

                i--
                try{
                    sleep(1000)
                }catch (e: Exception){
                    e.printStackTrace()
                    break
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //设置开屏页面的图片
        Glide.with(this).load("http://www.waterflowflow.work/MyApplicationMarketServer/startAdvert/Test")
                .skipMemoryCache(true)//关闭内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭磁盘缓存
                .into(ivStartAdvert)


        if(thread == null){
            thread = timeThread()
            thread?.start()
        }
        btnSkip.setOnClickListener {
            if(thread != null){
                thread?.interrupt()
                thread = null
            }
            //销毁开屏界面
            finish()
            //跳转页面
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}