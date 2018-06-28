package za.co.dubedivine.journalapp.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.database.JournalEntry


class JournalAdapter : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    private var _journals: ArrayList<JournalEntry> = ArrayList()
    private var journals: ArrayList<JournalEntry>
        get() = _journals
        set(value) {
            _journals = value
        }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.journal_layout, parent, false)
        return JournalViewHolder(view)
    }

    override fun getItemCount(): Int = journals.size

    override fun onBindViewHolder(holder: JournalViewHolder?, position: Int) {
        holder?.bind(journals[position])
    }

    fun addJournal(journalEntry: JournalEntry) {
        journals.add(journalEntry)
    }

    class JournalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvBody: TextView = view.findViewById(R.id.tv_body)
        val tvDate: TextView = view.findViewById(R.id.tv_modified_on)

        fun bind(journalEntry: JournalEntry) {
            tvTitle.text = journalEntry.title
            tvBody.text = journalEntry.body
            tvTitle.text = journalEntry.title
        }

    }
}