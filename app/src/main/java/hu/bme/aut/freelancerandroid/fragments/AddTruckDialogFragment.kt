package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.model.Transfer
import hu.bme.aut.freelancerandroid.model.Vehicle
import java.util.*

class AddTruckDialogFragment: androidx.fragment.app.DialogFragment() {

    interface NewTruckItemDialogListener{
        fun onTruckCreated(newItem: Vehicle)
    }

    private lateinit var truckName: EditText
    private lateinit var listener: AddTruckDialogFragment.NewTruckItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AddTruckDialogFragment.NewTruckItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_transport_item)
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                //  if (isValid()) {
                listener.onTruckCreated(getTruck())
                // }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    // private fun isValid() = nameEditText2.text.isNotEmpty()

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_truck, null)
        truckName = contentView.findViewById(R.id.etTruckName)
        return contentView
    }

    private fun getTruck() = Vehicle(
        name = truckName.text.toString()
    )

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}