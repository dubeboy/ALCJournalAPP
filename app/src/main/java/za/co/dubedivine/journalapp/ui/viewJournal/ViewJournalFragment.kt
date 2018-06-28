package za.co.dubedivine.journalapp.ui.viewJournal

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import za.co.dubedivine.journalapp.ui.R

class ViewJournalFragment : Fragment() {

    companion object {
        fun newInstance() = ViewJournalFragment()
    }

    private lateinit var viewModel: ViewJournalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.view_journal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewJournalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
