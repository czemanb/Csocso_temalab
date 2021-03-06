package hu.bme.aut.freelancerandroid.ui.vehicles.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto


class AddTruckDialogFragment: androidx.fragment.app.DialogFragment() {

    interface NewTruckItemDialogListener{
        fun onTruckCreated(newItem: VehicleDto)
    }

    private lateinit var truckName: EditText
    private lateinit var sizeX: EditText
    private lateinit var sizeY: EditText
    private lateinit var sizeZ: EditText
    private lateinit var maxWeight: EditText
    private lateinit var listener: NewTruckItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewTruckItemDialogListener
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

    private fun getTruck() = VehicleDto(
        name = truckName.text.toString(),
        x = sizeX.text.toString().toInt(),
        y = sizeY.text.toString().toInt(),
        z = sizeZ.text.toString().toInt(),
        weightLimit = maxWeight.text.toString().toDouble(),
        ownerId = 1 //Todo add ActiveUserId
    )

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}