package hu.bme.aut.freelancerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.Package

class SpecialPackageListAdapater(private var rowLayout: Int, private val listener: PackageItemClickListener) : RecyclerView.Adapter<SpecialPackageListAdapater.SpecialPackageViewHolder>(){


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
        private val arrowUp: ImageView
        private val arrowDown: ImageView
        private val toAddress : TextView

        init{
            arrowUp = itemView.findViewById(R.id.arrowUp)
            arrowDown = itemView.findViewById(R.id.arrowDown)
            toAddress = itemView.findViewById(R.id.textView)
        }

        fun initialize(pack: Package, action: PackageItemClickListener){
            toAddress.text = pack.name
            arrowUp.setOnClickListener{
                action.onArrowUpClicked(pack)
            }
            arrowDown.setOnClickListener{
                action.onArrowDownClicked(pack)
            }
        }
    }
}