package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Date

fun AskForDate(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.date_provided()){
            if (bookingData.bookable_date()){
                goto(FromTime(bookingData))
            }
            else{
                furhat.say("Sadly I can't help you with that. Try contacting First Line Support my friend")
                goto(Idle)
            }
        }
        furhat.ask("So tell me please. Do you want a room for today, for tomorrow...?")
    }
    onResponse<Date> {
        bookingData.date = it.intent.asLocalDate()
        reentry()
    }

}