package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.Time
import furhatos.nlu.common.Number
import java.time.LocalTime

fun FromTime(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.from_time_provided()){
            if (bookingData.valid_from_time()){
                furhat.say("So a room from " + bookingData.get_from_time() )
                goto(ToTime(bookingData))
            }else{
                val from = furhat.askFor<Time>("The time provided is outside of our opening hours. From what time do you want the room?") {
                    onResponse<DontKnow> {
                        furhat.say("You should really know that!")
                        reentry()
                    }
                    onResponse<Number>{
                        bookingData.from = LocalTime.of(it.intent.value?.toInt()!!, 0)
                        goto(FromTime(bookingData))
                    }
                }
                bookingData.from = from?.asLocalTime()
                reentry()
            }
        }else {
            val from = furhat.askFor<Time>("From what time do you want the room?") {
                onResponse<DontKnow> {
                    furhat.say("You should really know that!")
                    reentry()
                }
                onResponse<Number>{
                    bookingData.from = LocalTime.of(it.intent.value?.toInt()!!, 0)
                    goto(FromTime(bookingData))
                }
            }
            bookingData.from = from?.asLocalTime()
            reentry()
        }
    }

}