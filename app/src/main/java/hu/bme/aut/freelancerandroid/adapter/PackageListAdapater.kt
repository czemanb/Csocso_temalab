package hu.bme.aut.freelancerandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import kotlinx.android.synthetic.main.package_row.view.*

class PackageListAdapater(val context: Context) : RecyclerView.Adapter<PackageListAdapater.ViewHolder>(){

   val packages  = mutableListOf<Packages>(
        Packages("item1"),
        Packages("item2"),
        Packages("item3")
   )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.package_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return packages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pckg = packages[position]

        holder.textView.text = pckg.toDoText
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.textView
    }
}