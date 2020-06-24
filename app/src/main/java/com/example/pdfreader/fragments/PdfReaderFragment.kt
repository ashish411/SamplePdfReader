package com.example.pdfreader.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pdfreader.databinding.FragmentPdfReaderBinding
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnTapListener
import kotlinx.android.synthetic.main.fragment_pdf_reader.*

class PdfReaderFragment : Fragment(), PdfReaderHandler {

    private lateinit var binding: FragmentPdfReaderBinding
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPdfReaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onNextPage() {
        binding.pdfViewr.jumpTo(++currentPage)
    }

    override fun onPrevPage() {
        binding.pdfViewr.jumpTo(--currentPage)
    }

    override fun onLike() {
        Toast.makeText(requireContext(), "Liked", Toast.LENGTH_SHORT).show()
    }


    private fun initComponents() {
        binding.handler = this
        binding.pdfViewr.fromAsset("book.pdf")
            .swipeHorizontal(true)
            .onPageChange(object : OnPageChangeListener {
                override fun onPageChanged(page: Int, pageCount: Int) {
                    currentPage = page
                    binding.tvPageNum.text = "$page / $pageCount"
                }
            })
            .onTap(object : OnTapListener {
                override fun onTap(e: MotionEvent?): Boolean {
                    binding.llOptions.visibility = View.VISIBLE
                    Handler().postDelayed({ ll_options.visibility = View.GONE }, 3000)
                    return true
                }

            })
            .load()
    }
}

interface PdfReaderHandler {
    fun onNextPage()
    fun onPrevPage()
    fun onLike()
}