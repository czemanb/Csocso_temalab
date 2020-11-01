package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import kotlinx.android.synthetic.main.activity_package_sreen.*

class PackageSrceen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_sreen)

        rwPackages.adapter = PackageListAdapater(this)
    }
}