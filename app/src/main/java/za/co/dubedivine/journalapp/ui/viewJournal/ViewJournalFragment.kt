package za.co.dubedivine.journalapp.ui.viewJournal

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.journal_layout.*
import kotlinx.android.synthetic.main.view_journal_fragment.*
import za.co.dubedivine.journalapp.R
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

        // todo should observe this data!!
        viewModel = ViewModelProviders.of(this).get(ViewJournalViewModel::class.java)
        val journalId = arguments?.getInt(EXTRA_JOURNAL_ID)
        viewModel.getJournalById(journalId!!).observe(this, Observer {
            if (it != null) {
                tv_journal_title.text = it.title
                tv_journal_body.text = it.body
                tv_modified_on.text = getSimpleDateFormatter().format(it.modifiedAt)
                tv_created_at.text = getSimpleDateFormatter().format(it.createdAt)
            }
        })

    }

}
