package za.co.dubedivine.journalapp.util

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import java.text.SimpleDateFormat
import java.util.*

//val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestEmail()
//        .build()
//
//fun googleSignIn(context: Context) {
//    mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
//    val account = GoogleSignIn.getLastSignedInAccount(context)
//}

fun getSimpleDateFormatter(): SimpleDateFormat {
    return SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()) //todo fix this
}