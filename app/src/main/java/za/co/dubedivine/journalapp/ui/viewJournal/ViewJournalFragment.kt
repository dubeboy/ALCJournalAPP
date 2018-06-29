package za.co.dubedivine.journalapp.ui.viewJournal

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_journal_fragment.*
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.ui.addJournal.AddJournal
import za.co.dubedivine.journalapp.ui.addJournal.AddJournalViewModelFactory
import za.co.dubedivine.journalapp.util.getSimpleDateFormatter

class ViewJournalFragment : Fragment() {

    companion object {

        const val EXTRA_JOURNAL_ID = "extra_journal_id"

        fun newInstance(id: Int): ViewJournalFragment {
            val viewJournalFragment = ViewJournalFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_JOURNAL_ID, id)
            viewJournalFragment.arguments = bundle
            return viewJournalFragment
        }
    }

    private lateinit var viewModel: ViewJournalViewModel
    private lateinit var database: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(
                R.layout.view_journal_fragment,
                container,
                false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        database = AppDatabase.getInstance(activity!!.applicationContext)
        //create the factory to inject the database instance on the ViewModel
        val factory = AddJournalViewModelFactory(database)
        // todo should observe this data!!
        viewModel = ViewModelProviders.of(this, factory).get(ViewJournalViewModel::class.java)
        val journalId = arguments?.getInt(EXTRA_JOURNAL_ID)
        viewModel.getJournalById(journalId!!).observe(this, Observer {
            if (it != null) {
                tv_journal_title.text = it.title
                tv_journal_body.text = it.body
                tv_modified_at.text = getSimpleDateFormatter().format(it.modifiedAt)  //todo should use a string template
                tv_created_at.text = getSimpleDateFormatter().format(it.createdAt)
            }
        })


        fab_edit_journal.setOnClickListener {
            activity!!.startActivity(AddJournal.getStartIntent(it.context, journalId))
        }
    }
}
