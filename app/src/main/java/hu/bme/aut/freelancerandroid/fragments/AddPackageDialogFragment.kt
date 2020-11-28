package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages

class AddPackageDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewPackageItemDialogListener{
        fun onPackageCreated(newItem: Packages)
    }

    private lateinit var nameEditText: EditText
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
                    val myToast = Toast.makeText(requireActivity().applicationContext,"Kerem minden adatot toltson ki",Toast.LENGTH_SHORT)
                    myToast.setGravity(Gravity.CENTER, 0, 0)
                    myToast.show()
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun isValid() = nameEditText.text.isNotEmpty()

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_package, null)

        nameEditText = contentView.findViewById(R.id.etPackageName)

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

    private fun getPackage() = Packages(
        name = nameEditText.text.toString()
    )

    companion object{
        const val TAG = "NewPackageDialogFragment"
    }
}