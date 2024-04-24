package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.utils.ANIMATIONS
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.flow.kotlin.*
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.Time
import furhatos.nlu.common.Number
import java.time.LocalTime

fun FromTime(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.from_time_provided()){
            if (bookingData.valid_from_time()){
                val from = bookingData.get_from_time()
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.Nod))
                furhat.say{
                    random{
                        +"So a room from $from"
                        +"So a booking from $from"
                        +"From $from, got it!"
                    }}

                goto(ToTime(bookingData))
            }else{
                furhat.ask("The time provided is outside of our opening hours. From what time do you want the room?")

            }
        }else {
            furhat.ask("From what time do you want the room?")

        }
    }

    onResponse<Time>{
        bookingData.from = it.intent.asLocalTime()
        reentry()
    }

    onResponse<DontKnow> {
        furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.SideEye), async = true)
        furhat.say("You should really know that!")
        reentry()
    }
    onResponse<Number>{
        bookingData.from = bookingData.number_to_time(it.intent.value?.toInt()!!)
        goto(FromTime(bookingData))
    }
    onResponse {
        furhat.say(async=true) { random{
            +	"	No clue what you said human. Please repeat	"
            +	"	Sorry human, I didn't catch what you said. Could you repeat it?	"
            +	"	I must have some wax in my ears. Could you repeat that for me?	"
        } }
        furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.Confused2))
        reentry()
    }

}