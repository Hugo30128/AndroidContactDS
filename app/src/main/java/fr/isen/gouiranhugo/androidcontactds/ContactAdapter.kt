package fr.isen.gouiranhugo.androidcontactds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.gouiranhugo.androidcontactds.model.Results

internal class ContactAdapter(private var contactList: ArrayList<Results>, val onItemClickListener:(Results) -> Unit ): RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var image: ImageView = view.findViewById(R.id.picture)
        var mail: TextView= view.findViewById(R.id.mail)
        var adress: TextView= view.findViewById(R.id.adress)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = contactList[position]
        holder.name.text= item.name?.first + "  " + item.name?.last?.uppercase()
        holder.adress.text=item.location?.street?.number.toString() + " " + item.location?.street?.name + " " + item.location?.state + " " + item.location?.city
        holder.mail.text=item.email

        if (item.picture?.large!!.isNotEmpty()) {
            Picasso.get().load(item.picture?.large).into(holder.image)
        }
        holder.name.setOnClickListener {
            onItemClickListener(item)
        }
    }

    fun refreshList(contactsFromAPI: ArrayList<Results>) {
        contactList = contactsFromAPI
        notifyDataSetChanged()
    }


}




