package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.enu.Room
import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Time
import java.util.*

fun Booking(bookingData: BookingData): State = state(Parent) {
    onEntry {
        furhat.say("Looks like you want to book a room, right?")
        if (bookingData.date_provided()){
            if (bookingData.bookable_date()){
                goto(FromTime(bookingData))
            }
            else{
                furhat.say("Sadly I can't help you with that. Try contacting First Line Support my friend")
                goto(Idle)
            }
        }
        else{
            goto(AskForDate(bookingData))
        }

    }

}