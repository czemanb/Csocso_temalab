package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.android.gms.maps.model.LatLng
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.dto.PackDto
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddPackageDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewPackageItemDialogListener{
        fun onPackageCreated(newItem: PackDto?)
    }

    private lateinit var nameEditText: EditText
    private lateinit var fromAddressEditText: EditText
    private lateinit var toAddressEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var valueEditText: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var citySpinner: Spinner
    private lateinit var packageSizeSpinner: Spinner
    private lateinit var listener: NewPackageItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewPackageItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")


    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                if (isValid())
                    listener.onPackageCreated(getPackage())
                else{
                    val myToast = Toast.makeText(
                        requireActivity().applicationContext,
                        "Adatokban hiba van. Kerem minden adatot toltson ki",
                        Toast.LENGTH_LONG
                    )
                    myToast.setGravity(Gravity.CENTER, 0, 0)
                    myToast.show()
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun isValid() : Boolean {
        if (nameEditText.text.isEmpty() ||
            fromAddressEditText.text.isEmpty() ||
            toAddressEditText.text.isEmpty() ||
            weightEditText.text.isEmpty() ||
            valueEditText.text.isEmpty()){
            return false
        }
        getLocationFromAddress(fromAddressEditText.text.toString()) ?: return false
        getLocationFromAddress(toAddressEditText.text.toString()) ?: return false
        return true
    }

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_package, null)

        nameEditText = contentView.findViewById(R.id.etPackageName)
        weightEditText = contentView.findViewById(R.id.etPackageWeight)
        valueEditText = contentView.findViewById(R.id.etPackageValue)
        fromAddressEditText = contentView.findViewById(R.id.etFromAddress)
        toAddressEditText = contentView.findViewById(R.id.etToAddress)
        datePicker = contentView.findViewById(R.id.dpPackageDate)
        citySpinner = contentView.findViewById(R.id.spinnerCity)
        citySpinner.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.city_names)
            )
        )
        packageSizeSpinner = contentView.findViewById(R.id.spinnerPackageSize)
        packageSizeSpinner.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.package_sizes)
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
            val location: Address =address.get(0)
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

        private fun getPackage() : PackDto? {
        val toAddress = getLocationFromAddress(toAddressEditText.text.toString())
        val fromAddress = getLocationFromAddress(fromAddressEditText.text.toString())
        val toLong : Double
        val fromLong: Double
        val toLat : Double
        val fromLat: Double
        if (toAddress !=null) {
           toLat =toAddress.latitude
            toLong = toAddress.longitude
        }else {
            return null
        }

        if (fromAddress !=null) {
            fromLat =fromAddress.latitude
            fromLong = fromAddress.longitude
        }else{
            return null
        }

        return PackDto(
            name = nameEditText.text.toString(),
            size = packageSizeSpinner.selectedItem.toString() ?: "S",
            weight = weightEditText.text.toString().toDouble(),
            fromLat = fromLat,
            toLat = toLat,
            fromLong = fromLong,
            toLong = toLong,
//            fromLat = 0.0,
//            toLat = 0.0,
//            fromLong = 0.0,
//            toLong = 0.0,
            senderId = GlobalVariable.activeUser,
            dateLimit = getDateFrom(datePicker),
            townId = 1,
            value = valueEditText.text.toString().toInt()
        )
    }

    companion object{
        const val TAG = "NewPackageDialogFragment"
    }
}