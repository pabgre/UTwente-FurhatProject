package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.utils.ANIMATIONS
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun BookRoom(bookingData: BookingData): State = state(Parent) {
    onEntry {
        furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace))

        furhat.say{random{
            +	"Perfect! I booked the room for you"
            +	"Beautiful! The room is yours!"
            +	"Great! The room is now booked for you!"
            +	"Good! I booked the room for you"
        }}
        delay(1000)
        if (bookingData.inmediate_booking()){
            furhat.say{random{
                +	"Wait for the DreamTeamer on shift. They will give you the key for the room. I don't have hands hahahaha"
                +	"Good stuff! Now we just need a DreamTeamer with arms to hand you the key! Stick around until they come back!"
                +	"Perfect! It's all set! Now we just need someone with hands to pass you the key hehe. Wait for the DreamTeamer to come by"
            }}
            furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.Affirmation1))
        }
        goto(Bye)
    }

}