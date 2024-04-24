package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.utils.ANIMATIONS
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.Number
import furhatos.nlu.common.Time
import java.time.LocalTime

fun ToTime(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.to_time_provided()){
            val to_txt = bookingData.get_to_time()
            if (bookingData.valid_to_time()){
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace))
                furhat.say{random{
                    +	"Until $to_txt	"
                    +	"Until $to_txt got it!"
                    +	"Until $to_txt got ya"
                }}
                goto(RoomName(bookingData))
            }else{
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace))
                furhat.ask("The time provided is outside of our opening hours. Until what time do you want the room?")

            }
        }else {
           furhat.ask("Until what time do you want the room?")
        }
    }

    onResponse<Time>{
        bookingData.to = it.intent.asLocalTime()
        reentry()
    }

    onResponse<DontKnow> {
        furhat.say("You should really know that!")
        reentry()
    }
    onResponse<Number>{
        bookingData.to = bookingData.number_to_time(it.intent.value?.toInt()!!)
        goto(ToTime(bookingData))
    }
    onResponse {
        furhat.say(async = true){random{
            +	"	No clue what you said human. Please repeat	"
            +	"	Sorry human, I didn't catch what you said. Could you repeat it?	"
            +	"	I must have some wax in my ears. Could you repeat that for me?	"
        }}
        furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.Question1))
        reentry()
    }

}