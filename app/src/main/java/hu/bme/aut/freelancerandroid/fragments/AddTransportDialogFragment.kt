package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.model.Transfer

class AddTransportDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewTransportItemDialogListener{
        fun onTransportCreated(newItem: Transfer)
    }

    private lateinit var nameEditText2: EditText
    private lateinit var listener: AddTransportDialogFragment.NewTransportItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AddTransportDialogFragment.NewTransportItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_package_item)
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onTransportCreated(getTransport())
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun isValid() = nameEditText2.text.isNotEmpty()

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_transport, null)
        nameEditText2 = contentView.findViewById(R.id.etPackageName2)
        return contentView
    }

    private fun getTransport() = Transfer(
        id = nameEditText2.text.toString().toLong()
    )

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}