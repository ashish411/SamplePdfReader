package com.example.pdfreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnTapListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pdfViewr.fromAsset("book.pdf")
            .swipeHorizontal(true)
            .onPageChange(object: OnPageChangeListener{
                override fun onPageChanged(page: Int, pageCount: Int) {
                    currentPage = page
                    tv_page_num.text = "$page / $pageCount"
                }
            })
            .onTap(object: OnTapListener{
                override fun onTap(e: MotionEvent?): Boolean {
                    ll_options.visibility = View.VISIBLE
                    Handler().postDelayed({ll_options.visibility = View.GONE},3000)
                    return true
                }

            })
            .load()

        iv_prev_page.setOnClickListener(this)
        iv_next_page.setOnClickListener(this)
        iv_like.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            iv_next_page -> pdfViewr.jumpTo(++currentPage)
            iv_prev_page -> pdfViewr.jumpTo(--currentPage)
            iv_like -> Toast.makeText(this,"Liked",Toast.LENGTH_SHORT).show()
        }
    }
}