package za.co.dubedivine.journalapp.ui.main

import android.content.res.Resources
import android.provider.Settings.System.DATE_FORMAT
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.database.JournalEntry
import za.co.dubedivine.journalapp.ui.viewJournal.ViewJournal
import za.co.dubedivine.journalapp.util.getSimpleDateFormatter
import java.text.SimpleDateFormat
import java.util.*


class JournalAdapter : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    private var _journals: ArrayList<JournalEntry> = ArrayList()
    var journals: ArrayList<JournalEntry>
        get() = _journals
        set(value) {
            _journals = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.journal_layout, parent, false)
        return JournalViewHolder(view)
    }

    override fun getItemCount(): Int = journals.size

    // default 16 items
    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        holder.bind(journals[position])
    }

    fun addJournal(journalEntry: JournalEntry) {
        journals.add(journalEntry)
    }

    class JournalViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvBody: TextView = view.findViewById(R.id.tv_body)
        private val tvDate: TextView = view.findViewById(R.id.tv_modified_on)
        private val tvBar: TextView = view.findViewById(R.id.tv_bar)

        fun bind(journalEntry: JournalEntry) {
            tvTitle.text = journalEntry.title
            tvBody.text = journalEntry.body
            tvDate.text = getSimpleDateFormatter().format(journalEntry.modifiedAt)
           // tvBar.setBackgroundColor(genBarColor(journalEntry))
            itemView.setOnClickListener {
                itemView.context.startActivity(ViewJournal.getStartIntent(itemView.context, journalEntry.id!!))
            }
        }

        private fun genBarColor(mood: Int): Int {
           return when (mood) {
                2 -> {
                    Resources.getSystem().getColor(R.color.happy_feeling_color)

                }
                3 -> {
                    Resources.getSystem().getColor(R.color.sad_feeling_color)
                }
               else -> {
                   //todo fix
                   Resources.getSystem().getColor(R.color.neutral_feeling_color)
               }
            }
        }

    }
}