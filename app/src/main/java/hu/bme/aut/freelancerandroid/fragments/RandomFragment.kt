package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import hu.bme.aut.freelancerandroid.R


class RandomFragment : Fragment(R.layout.fragment_random) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val childFragment: Fragment = GoogleMapsFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, childFragment).commit()

    }
}