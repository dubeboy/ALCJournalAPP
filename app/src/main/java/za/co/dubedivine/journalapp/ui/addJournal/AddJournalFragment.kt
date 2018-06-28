package za.co.dubedivine.journalapp.ui.addJournal

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.ui.addJournal.R

class AddJournalFragment : Fragment() {

    companion object {
        fun newInstance() = AddJournalFragment()
    }

    private lateinit var viewModel: AddJournalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.add_journal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddJournalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
