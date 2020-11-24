package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isGone
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import kotlinx.android.synthetic.main.fragment_vehicle_screen.*
import java.util.*

class AddTruckDialogFragment: androidx.fragment.app.DialogFragment() {

    interface NewTruckItemDialogListener{
        fun onTruckCreated(newItem: Vehicle)
    }

    private lateinit var truckName: EditText
    private lateinit var sizeX: EditText
    private lateinit var sizeY: EditText
    private lateinit var sizeZ: EditText
    private lateinit var maxWeight: EditText
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
                  if (isValid()) {
                      listener.onTruckCreated(getTruck())
                  }
                  else {
                      val myToast = Toast.makeText(requireActivity().applicationContext,"Kerem minden adatot toltson ki",Toast.LENGTH_SHORT)
                      myToast.setGravity(Gravity.CENTER, 0, 0)
                      myToast.show()
                  }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun isValid() = truckName.text.isNotEmpty() && sizeX.text.isNotEmpty() && sizeY.text.isNotEmpty() && sizeZ.text.isNotEmpty() && maxWeight.text.isNotEmpty()

    private fun getContentView(): View {
        val contentView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_truck, null)

        truckName = contentView.findViewById(R.id.etTruckName)
        sizeX = contentView.findViewById(R.id.etX)
        sizeY = contentView.findViewById(R.id.etY)
        sizeZ = contentView.findViewById(R.id.etZ)
        maxWeight = contentView.findViewById(R.id.etMaxWeight)

        return contentView
    }

    private fun getTruck() = Vehicle(
        id = 1, //Todo
        name = truckName.text.toString(),
        x = sizeX.text.toString().toInt(),
        y = sizeY.text.toString().toInt(),
        z = sizeZ.text.toString().toInt(),
        weightLimit = maxWeight.text.toString().toDouble(),
        owner = null,//TOdo
        cc = null
    )

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}