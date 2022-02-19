package com.example.mvvmretrofitcoroutine.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretrofitcoroutine.R
import com.example.mvvmretrofitcoroutine.data.model.Company

class CompanyAdapter():  RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    private var companies = mutableListOf<Company>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyName: TextView = view.findViewById(R.id.company_name)
        val companyCatchphrase: TextView = view.findViewById(R.id.company_catchphrase)
        val companyBs: TextView = view.findViewById(R.id.company_bs)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.company_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.companyName.text = companies[position].name
        viewHolder.companyCatchphrase.text = companies[position].catchPhrase
        viewHolder.companyBs.text = companies[position].bs
    }


    fun updateCompanies(companies: List<Company>){
        this.companies.clear()
        this.companies.addAll(companies)
        notifyDataSetChanged()
    }

    override fun getItemCount() = companies.size
}