package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.model.Transfer
import java.util.*

class AddTransportDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewTransportItemDialogListener{
        fun onTransportCreated(newItem: Transfer)
    }

    private lateinit var nameEditText2: DatePicker
    private lateinit var listener: AddTransportDialogFragment.NewTransportItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AddTransportDialogFragment.NewTransportItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_transport_item)
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
              //  if (isValid()) {
                    listener.onTransportCreated(getTransport())
               // }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

   // private fun isValid() = nameEditText2.text.isNotEmpty()

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_transport, null)
        nameEditText2 = contentView.findViewById(R.id.dpTrasportDate)
        return contentView
    }

    private fun getTransport() : Transfer{
        val year = nameEditText2.year
        val month = nameEditText2.month
        val day = nameEditText2.dayOfMonth
        val selectedDate = Date(year, month, day)

        return Transfer(date = selectedDate)
    }

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}