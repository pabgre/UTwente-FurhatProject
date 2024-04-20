package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun BookRoom(bookingData: BookingData): State = state(Parent) {
    onEntry {
        furhat.say("Perfect! I booked the room for you")
        if (bookingData.inmediate_booking()){
            furhat.say("Wait for the DreamTeamer on shift. They will give you the key for the room. I don't have hands hahahaha")

        }
        goto(Bye)
    }

}