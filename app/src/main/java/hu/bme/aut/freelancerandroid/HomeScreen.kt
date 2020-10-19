package hu.bme.aut.freelancerandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_home_screen.*

lateinit var toggle: ActionBarDrawerToggle

class home_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> Toast.makeText(applicationContext, "Clicked item 1", Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(applicationContext, "Clicked item 1", Toast.LENGTH_SHORT).show()
                R.id.item3 -> Toast.makeText(applicationContext, "Clicked item 1", Toast.LENGTH_SHORT).show()
                R.id.item4 -> Toast.makeText(applicationContext, "Clicked item 1", Toast.LENGTH_SHORT).show()
            }
            true
        }

        package_button.setOnClickListener {
            val packageIntent = Intent(this, PackageSrceen::class.java)
            startActivity(packageIntent)
        }

        transport_button.setOnClickListener {
            val transportIntent = Intent(this, TransportScreen::class.java)
            startActivity(transportIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}