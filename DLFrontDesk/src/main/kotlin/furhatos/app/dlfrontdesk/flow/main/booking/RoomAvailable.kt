package furhatos.app.dlfrontdesk.flow.main.booking


import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Greeting
import furhatos.app.dlfrontdesk.utils.ANIMATIONS
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

fun RoomAvailable(bookingData: BookingData): State = state(Parent) {
    onEntry {
        val room = bookingData.room_name
        if (bookingData.room_available()){

            furhat.ask { random{
                +	"	The room is available. Do you want to confirm the booking then?"
                +	"You are in luck! $room is available! Do you want to confirm the boking then?"
                +	"It's your lucky day! Seems like $room is available! Do you want to confirm the boking then?"
                +	"	Perfect! The room seems to be available. Would you like to confirn the booking?	"
            } }
        }else{
            furhat.ask { random{
                +	"	The room is not available. Would you like to book another room?	"
                +	"	Ugh I'm sorry! Seems like that room is not free. Maybe chose a different room?	"
                +	"My apologies human, seems like $room isn't available! Would you like to book a different one?"
            } }
        }

    }

    onResponse<Yes> {
        if (bookingData.room_available()){
            goto(BookRoom(bookingData))
        }else{
            bookingData.room_name = null
            goto(RoomName(bookingData))
        }

    }
    onResponse<No> {
        if (bookingData.room_available()){
            furhat.say("Well, that was a bit of a waste of time if you didn't want to book it", async = true)
            furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace))
            delay(2000)
            goto(Bye)
        }else{
            furhat.say(async = true) { random{
                +	"I am sorry we couldn't help you with your booking	"
                +	"My bad! Sorry I couldn't help you with your booking"
                +	"Sorry I couldn't be of assistance!"
            } }
            furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.Bow))

            goto(Bye)
        }
    }

}