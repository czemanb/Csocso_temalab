package hu.bme.aut.freelancerandroid.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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

class SpecialPackageListAdapater(var rowLayout: Int, private val listener: SpecialPackageListAdapater.PackageItemClickListener) : RecyclerView.Adapter<SpecialPackageListAdapater.SpecialPackageViewHolder>(){


    private val differCallback = object : DiffUtil.ItemCallback<Package>() {
        override fun areItemsTheSame(oldItem: Package, newItem: Package): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Package, newItem: Package): Boolean {
            return oldItem == newItem
        }
    }

    val packages = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialPackageListAdapater.SpecialPackageViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(rowLayout, parent, false)

        return SpecialPackageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return packages.currentList.size

    }

    override fun onBindViewHolder(holder: SpecialPackageViewHolder, position: Int) {
        holder.initialize(packages.currentList[position], listener)
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
        fun onArrowUpClicked(item: Package)
        fun onArrowDownClicked(item: Package)
    }

    inner class SpecialPackageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val arrowUp: ImageView
        val arrowDown: ImageView
        val toAddress : TextView

        init{
            arrowUp = itemView.findViewById(R.id.arrowUp)
            arrowDown = itemView.findViewById(R.id.arrowDown)
            toAddress = itemView.findViewById(R.id.textView)
        }

        fun initialize(pack: Package, action: PackageItemClickListener){
            toAddress.text = pack.name.toString()
            arrowUp.setOnClickListener{
                action.onArrowUpClicked(pack)
            }
            arrowDown.setOnClickListener{
                action.onArrowDownClicked(pack)
            }
        }
    }
}