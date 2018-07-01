package za.co.dubedivine.journalapp.util

import android.content.Context
import android.util.Log
import com.firebase.jobdispatcher.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import za.co.dubedivine.journalapp.firebaseSyncService.FirebaseSyncService
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

private var isInitialized: Boolean = false
private const val REMINDER_INTERVAL_MINUTES = 2L
private val REMINDER_INTERVAL_SECONDS = TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES).toInt()
private val SYNC_FLEX_TIME = REMINDER_INTERVAL_SECONDS


fun scheduleSyncJob(context: Context) {

    Log.d("ScheduleJob", "calling scheduled job")

    if(isInitialized) return
    val driver = GooglePlayDriver(context)
    val firebaseJobDispatcher = FirebaseJobDispatcher(driver)

    val constraintSyncScheduleJob = firebaseJobDispatcher
            .newJobBuilder()
            .setService(FirebaseSyncService::class.java)
            .setTag("scheduleSyncJob")
            .setConstraints(Constraint.ON_ANY_NETWORK)
            .setRecurring(true)
            .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_SECONDS, REMINDER_INTERVAL_SECONDS + SYNC_FLEX_TIME))
            .setLifetime(Lifetime.FOREVER)
            .setReplaceCurrent(true)
            .build()

    firebaseJobDispatcher.schedule(constraintSyncScheduleJob)
    isInitialized = true
}