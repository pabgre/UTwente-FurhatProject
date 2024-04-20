package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.utils.BookingData
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
            if (bookingData.valid_to_time()){
                furhat.say("Until "+ bookingData.get_to_time() + " right?" )
                goto(RoomName(bookingData))
            }else{
                val to = furhat.askFor<Time>("The time provided is outside of our opening hours. Until what time do you want the room?") {
                    onResponse<DontKnow> {
                        furhat.say("You should really know that!")
                        reentry()
                    }
                    onResponse<Number>{
                        bookingData.to = LocalTime.of(it.intent.value?.toInt()!!, 0)
                        goto(ToTime(bookingData))
                    }
                }
                bookingData.to = to?.asLocalTime()
                reentry()
            }
        }else {
            val to = furhat.askFor<Time>("Until what time do you want the room?") {
                onResponse<DontKnow> {
                    furhat.say("You should really know that!")
                    reentry()
                }
                onResponse<Number>{
                    bookingData.to = LocalTime.of(it.intent.value?.toInt()!!, 0)
                    goto(ToTime(bookingData))
                }
            }
            bookingData.to = to?.asLocalTime()
            reentry()
        }
    }

}