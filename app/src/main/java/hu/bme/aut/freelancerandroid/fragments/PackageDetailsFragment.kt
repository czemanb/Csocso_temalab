package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import hu.bme.aut.freelancerandroid.R
import kotlinx.android.synthetic.main.fragment_package_details.*
import kotlinx.android.synthetic.main.fragment_profile.*

class PackageDetailsFragment: Fragment(R.layout.fragment_package_details), View.OnClickListener{
    val args: PackageDetailsFragmentArgs by navArgs()
    private lateinit var status: TextView
    private lateinit var deadline: TextView
    private lateinit var weight: TextView
    private lateinit var size: TextView
    private lateinit var city: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pack = args.pack
        status = view.findViewById(R.id.pacStatus)
        deadline = view.findViewById(R.id.packDeadline)
        weight= view.findViewById(R.id.packWeight)
        size = view.findViewById(R.id.packSize)
        city = view.findViewById(R.id.packCity)
        value = view.findViewById(R.id.packValue)
        name = view.findViewById(R.id.packName)
        status.text = pack.status
        deadline.text = pack.dateLimit
        weight.text = "${pack.weight.toString()} kg"
        size.text = pack.size
        city.text = pack.town.name
        value.text = "${pack.value.toString()} HUF"
        name.text = pack.name

        btnBackButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBackButton -> requireActivity().onBackPressed()
        }
    }
}