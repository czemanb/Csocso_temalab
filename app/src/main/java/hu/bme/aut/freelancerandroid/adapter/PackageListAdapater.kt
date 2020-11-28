package hu.bme.aut.freelancerandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.proba.pack1Item
import hu.bme.aut.freelancerandroid.repository.model.Package
import kotlinx.android.synthetic.main.fragment_dialog_add_package.view.*
import kotlinx.android.synthetic.main.package_row.view.*

class PackageListAdapater() : RecyclerView.Adapter<PackageListAdapater.PackageViewHolder>(){


    private val differCallback = object : DiffUtil.ItemCallback<Package>() {
        override fun areItemsTheSame(oldItem: Package, newItem: Package): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Package, newItem: Package): Boolean {
            return oldItem == newItem
        }
    }

    val packages = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.package_row, parent, false)

        return PackageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return packages.currentList.size
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val pckg = packages.currentList[position]

        holder.nameTextView.text = pckg.name
    }


//    fun addPackage(pckg: Packages) {
//        packages.add(pckg)
//        notifyItemInserted(packages.size - 1)
//    }
//
//
//
//
//    fun update(pckgs: List<Packages>) {
//        packages.clear()
//        packages.addAll(pckgs)
//        notifyDataSetChanged()
//    }

    interface PackageItemClickListener{
        fun onItemChanged(item: Packages)
    }

    inner class PackageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTextView : TextView

        init{
            nameTextView = itemView.findViewById(R.id.textView)
        }
    }
}