package com.cooperlighting.xyzdemotraining.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.cooperlighting.xyzdemotraining.R


class CartDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle("Shopping Cart")
            .setMessage("Would you like to add to the shopping cart?")
            .setPositiveButton("OK") { dialogInterface, i ->

                findNavController().navigate(R.id.productsCartFragment)
                dialogInterface.cancel()
            }
            .setNegativeButton("Cancel") { dialogInterface, i -> /*Cancel fragment*/ dialogInterface.cancel() }
            .create()
    }
}