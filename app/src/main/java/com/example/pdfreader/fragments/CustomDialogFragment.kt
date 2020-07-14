package com.example.pdfreader.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.pdfreader.R
import com.example.pdfreader.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment(), DialogInterface.OnClickListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        builder.setView(inflater.inflate(R.layout.fragment_custom_dialog,null))
            .setTitle(R.string.dialog_title)
            .setPositiveButton(R.string.done,
            this)
            .setNegativeButton(R.string.Cancel,this)
        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        dialog?.dismiss()
    }
}