package za.co.dubedivine.journalapp.util

import java.text.SimpleDateFormat
import java.util.*


fun getSimpleDateFormatter(): SimpleDateFormat {
    return SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
}