package za.co.dubedivine.journalapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.ui.addJournal.AddJournal

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var journalAdapter: JournalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // setup the recycler view
        journalAdapter = JournalAdapter()
        recycler_view_journals.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        recycler_view_journals.adapter = journalAdapter

        //setup view model
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getAllJournals()?.observe(this, Observer {
            Log.d(TAG, "the main model observer is called")
            journalAdapter.journals = ArrayList(it)
        })

        // setup fab onClick
        fab_add_journal.setOnClickListener {
            startActivity(AddJournal.getStartIntent(activity!!))
        }
    }
}
