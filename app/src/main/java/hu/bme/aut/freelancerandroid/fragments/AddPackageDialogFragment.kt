package hu.bme.aut.freelancerandroid.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages

class AddPackageDialogFragment : androidx.fragment.app.DialogFragment() {

    interface NewPackageItemDialogListener{
        fun onPackageCreated(newItem: Packages)
    }

    private lateinit var nameEditText: EditText
    private lateinit var listener: NewPackageItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewPackageItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")

        val options = arrayOf("Budapest", "Miskolc", "Debrecen")

        //option = findViewById(spinner1) as Spinner

        //option.adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, options)

        /*option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //options.get(position)
            }
        }*/
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_package_item)
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onPackageCreated(getPackage())
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
        return contentView
    }

    private fun getPackage() = Packages(
        name = nameEditText.text.toString()
    )

    companion object{
        const val TAG = "NewPackageDialogFragment"
    }
}