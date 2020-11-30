package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.maps.model.LatLng
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.repository.dto.TransferDto
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.User
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import kotlinx.android.synthetic.main.fragment_dialog_add_transport.*
import java.io.IOException
import java.util.*

class AddTransportDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewTransportItemDialogListener{
        fun onTransportCreated(newItem: TransferDto)
    }

    private lateinit var transportDate: DatePicker
    private lateinit var citySpinner: Spinner
    private lateinit var fromAddress: EditText
    private lateinit var startHour: EditText
    private lateinit var startMinute: EditText
    private lateinit var listener: NewTransportItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewTransportItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_transport_item)
            .setView(getContentView())
            .setPositiveButton(R.string.ok) {
                    dialogInterface, i -> if (isValid()) {
                listener.onTransportCreated(getTransport())
            }  else{
                val myToast = Toast.makeText(
                    requireActivity().applicationContext,
                    "Adatokban hiba van. Kerem minden adatot  helyesen toltson ki!!",
                    Toast.LENGTH_LONG
                )
                myToast.setGravity(Gravity.CENTER, 0, 0)
                myToast.show()
            } }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun isValid() : Boolean {
        if (fromAddress.text.isEmpty() || startHour.text.isEmpty() || startMinute.text.isEmpty())
            return false
        getLocationFromAddress(fromAddress.text.toString()) ?: return false
        val hour = startHour.text.toString().toInt()
        if (hour < 0 || hour > 24)
            return false
        val minute = startMinute.text.toString().toInt()
        if (minute < 0 || minute >= 59)
            return false
        return true
    }



    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_transport, null)
        fromAddress = contentView.findViewById(R.id.etStartFromAddress)
        startHour= contentView.findViewById(R.id.etHour)
        startMinute= contentView.findViewById(R.id.etMinute)
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

    private fun getLocationFromAddress(strAddress: String): LatLng? {
        val coder = Geocoder(activity)
        var address : MutableList<Address> = mutableListOf()
        var p1 : LatLng? = null

        try {
            address = coder.getFromLocationName(strAddress, 5)
            if (address==null) {
                return null
            }
            val location: Address = address[0]
            location.latitude
            location.longitude
            p1 = LatLng(location.latitude, location.longitude)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return p1
    }

    private fun getDateFrom(picker: DatePicker): String {
        return String.format(
            Locale.getDefault(), "%04d-%02d-%02d",
            picker.year, picker.month + 1, picker.dayOfMonth
        )
    }

    private fun getTimeFrom(hour: String, minute: String) :String {
        return String.format("$hour:$minute:00")
    }

    private fun getTransport(): TransferDto{ 
        val fromAddress = getLocationFromAddress(fromAddress.text.toString())
        val fromLong: Double
        val fromLat: Double

        if (fromAddress !=null) {
            fromLat =fromAddress.latitude
            fromLong = fromAddress.longitude
        }else{
            fromLat = 0.0
            fromLong = 0.0
        }
        return TransferDto(
            date=getDateFrom(transportDate),
            townId = 1,
            vehicleId = -49,
            carrierId = GlobalVariable.activeUser ,
            fromLat = fromLat,
            toLat = fromLat,
            fromLong = fromLong,
            toLong = fromLat,
            startTime = getTimeFrom(startHour.text.toString(), startMinute.text.toString())

        )
    }

    companion object{
        const val TAG = "NewTransportDialogFragment"
    }
}