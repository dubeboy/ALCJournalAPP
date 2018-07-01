package za.co.dubedivine.journalapp.ui.settings

import android.os.Bundle
import android.support.v7.preference.CheckBoxPreference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import za.co.dubedivine.journalapp.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import android.content.Intent
import android.R.attr.data
import android.content.SharedPreferences
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException


class PreferenceSettingFragment : PreferenceFragmentCompat() {

    companion object {
        const val PREF_EMAIL = "pref_email"
        const val RC_SIGN_IN = 100
    }

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var pref: SharedPreferences
    private lateinit var signInPref: CheckBoxPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_journal)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        pref = PreferenceManager.getDefaultSharedPreferences(this.context)

        signInPref = findPreference("sign_in") as CheckBoxPreference

        signInPref.setOnPreferenceClickListener {

            mGoogleSignInClient = GoogleSignIn.getClient(activity?.applicationContext!!, gso)
            val account = GoogleSignIn.getLastSignedInAccount(activity?.applicationContext!!)
            if (account == null) {
                val signInIntent = mGoogleSignInClient?.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            } else {
                signInUser(account, pref)
            }
            true
        }

    }

    private fun signInUser(account: GoogleSignInAccount, pref: SharedPreferences) {
        val email = account.email
        signInPref.summary = "$email"
        pref.edit().putString(PREF_EMAIL, email).apply() // saving the
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                signInUser(account, pref)
            } catch (e: ApiException) {
                signInPref.isChecked = false // dont show it as checked bra
            }
        }
    }
}