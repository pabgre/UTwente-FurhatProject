package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun DecideRoom(bookingData: BookingData): State = state(Parent) {
    onEntry {
        furhat.say("I don't know bro... They forgot to code this case")
    }

}