package com.example.pdfreader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.pdfreader.fragments.EpubReaderFragment
import com.example.pdfreader.fragments.PdfReaderFragment
import com.example.pdfreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainActivityHandler {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var binding: ActivityMainBinding
    private val pdfReaderFragment = PdfReaderFragment()
    private val epubReaderFragment = EpubReaderFragment()
    private var areButtonVisible = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initComponents()
    }

    override fun openPdfReader() {
        areButtonVisible = false
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.fragment, pdfReaderFragment)
            addToBackStack(null)
            commit()
        }
        toggleButtonVisibility(areButtonVisible)
    }

    override fun openEpubReader() {
        areButtonVisible = false

        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.fragment, epubReaderFragment)
            addToBackStack(null)
            commit()
        }
        toggleButtonVisibility(areButtonVisible)
    }

    override fun onBackPressed() {
        if (!areButtonVisible) {
            toggleButtonVisibility(true)
        }
        super.onBackPressed()
    }


    private fun initComponents() {
        binding.handler = this
        fragmentManager = supportFragmentManager
    }

    private fun toggleButtonVisibility(isVisible: Boolean) {
        binding.run{
            btnEpubReader.visibility = if (isVisible) View.VISIBLE else View.GONE
            btnPdfReader.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

}

interface MainActivityHandler {
    fun openPdfReader()
    fun openEpubReader()
}