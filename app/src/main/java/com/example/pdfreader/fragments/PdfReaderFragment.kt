package com.example.pdfreader.fragments

import android.content.res.AssetManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pdfreader.databinding.FragmentPdfReaderBinding
import com.example.pdfreader.utils.Utils
import com.github.barteksc.pdfviewer.listener.OnLongPressListener
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
            .pageSnap(true)
            .autoSpacing(true)
            .pageFling(true)
            .onPageChange(object : OnPageChangeListener {
                override fun onPageChanged(page: Int, pageCount: Int) {
                    currentPage = page
                    Utils.playPageSwipeSound(requireContext())
                    if (currentPage == 4) {
                        CustomDialogFragment().show(childFragmentManager, null)
                    }
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
            .onLongPress(object : OnLongPressListener {
                override fun onLongPress(e: MotionEvent?) {

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