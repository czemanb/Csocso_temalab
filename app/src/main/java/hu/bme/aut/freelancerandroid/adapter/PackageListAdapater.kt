package hu.bme.aut.freelancerandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.proba.pack1Item
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import kotlinx.android.synthetic.main.fragment_dialog_add_package.view.*
import kotlinx.android.synthetic.main.package_row.view.*

class PackageListAdapater(var rowLayout: Int,private  var listener: PackageItemClickListener) : RecyclerView.Adapter<PackageListAdapater.PackageViewHolder>(){


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
            .inflate(rowLayout, parent, false)

        return PackageViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return packages.currentList.size

    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val pckg = packages.currentList[position]
        holder.nameTextView.text = pckg.name
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(pckg) }
            }
        }
        holder.item = pckg
    }

    private var onItemClickListener: ((Package) -> Unit)? = null
    private var onDeleteItemClickListener : ((Package) -> Unit)? = null

    fun setOnItemClickListener(listener: (Package) -> Unit){
        onItemClickListener = listener
    }
    fun setOnDeleteItemClickListener(listener: (Package) -> Unit) {

    }


    interface PackageItemClickListener{
        fun onItemDelete(item: Package)
    }

    inner class PackageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTextView : TextView
        val removeButton : ImageButton? =itemView.findViewById(R.id.imremovepack)
        var item: Package? = null

        init{
            nameTextView = itemView.findViewById(R.id.textView)
                removeButton?.setOnClickListener(){
                    listener.onItemDelete(item!!)
                }

        }
    }
}