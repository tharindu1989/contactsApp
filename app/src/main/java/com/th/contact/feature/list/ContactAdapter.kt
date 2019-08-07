package com.th.contact.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.th.contact.R
import com.th.contact.data.entity.Contact

/**
 * Created By Tharindu on 8/7/2019
 *
 */
class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    var items: List<Contact> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * refresh the adapter with data
     * @param items : List Items to refresh
     */
    fun refreshAdapter(items: List<Contact>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_contact, parent, false)) {

    var nameTxt: TextView = itemView.findViewById(R.id.contactTxt)
    var profileImg: ImageView = itemView.findViewById(R.id.profileImg)

    fun bindData(contact: Contact) {
        nameTxt.text = contact.firstName
        Picasso.get()
            .load(contact.avatar)
            .into(profileImg)
    }
}