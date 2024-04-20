package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.Number
import furhatos.nlu.common.Time
import java.time.LocalTime

fun DecideRoom(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.nb_people_provided()){
            if (bookingData.nb_people!! > 20){
                furhat.say("Buff... Too many people you want to meet with... Contact First Line Support for that. ")
                goto(Idle)
            }else{
                bookingData.find_fiting_room()
                furhat.say("I think " + bookingData.room_name + " would be a nice fit for your needs!")
                goto(BookRoom(bookingData))
            }

        }else{
            val nb_person = furhat.askFor<Number>("How many people are you expecting?") {
                onResponse<DontKnow> {
                    furhat.say("You should really know that!")
                    reentry()
                }
            }
            bookingData.nb_people = nb_person?.value
            reentry()
        }
    }

}