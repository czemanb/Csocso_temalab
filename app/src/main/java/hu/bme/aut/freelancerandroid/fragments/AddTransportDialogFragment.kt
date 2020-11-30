package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.User
import java.util.*

class AddTransportDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewTransportItemDialogListener{
        fun onTransportCreated(newItem: Transfer)
    }

    private lateinit var transportDate: DatePicker
    private lateinit var citySpinner: Spinner
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
            .setPositiveButton(R.string.ok) {
                    dialogInterface, i -> listener.onTransportCreated(getTransport())
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_transport, null)
        transportDate = contentView.findViewById(R.id.dpTrasportDate)
        citySpinner = contentView.findViewById(R.id.spinnerTransportCity)
        citySpinner.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.city_names)
            )
        )
        return contentView
    }

    private fun getTransport() : Transfer{ //Todo
//        val year = transportDate.year
//        val month = transportDate.month
//        val day = transportDate.dayOfMonth
//        val selectedDate = Date(year, month, day)
//
//        return Transfer(date = selectedDate)
        return Transfer(null,null,null,1L,null,null, 0.1, 0.1, 0.1, 0.1, "", "")
    }

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}