package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.enu.Room
import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Greeting
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.app.dlfrontdesk.utils.ANIMATIONS
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Time
import furhatos.nlu.common.Yes
import java.time.LocalDate
import java.util.*

fun Booking(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.room_name == "toilet"){
            furhat.say(async=true){ random{
                +	"Gross... What are you planning to do there? Don't tell me... please	"
                +	"Okay... I won't ask any questions..."
                + "We can't rent out that room but it is better to shit in your own sink, than sinking in your own shit"
            }}
            furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.Confused2))
            goto(Idle)
        }
        furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace))
        furhat.say{ random{
            +"Looks like you want to book a room"
            +"So you'd like to book a room then"
            +"You want to book a room, did I catch that"
            +"Just to double check, you want to book a room"
        }}


        if (bookingData.date_provided()){
            goto(AskForDate(bookingData))
        }
        else if (bookingData.from_time_provided() || bookingData.to_time_provided()){
                furhat.ask{random{
                    +"I am assuming you want to book it today, right?"
                    +"For today, right?"
                    +"You would like to book it today, right?"
                }}
                bookingData.date = LocalDate.now()
                goto(FromTime(bookingData))
        }
        else{
            goto(AskForDate(bookingData))
        }


    }

    onResponse<Yes> {
        if (bookingData.from_time_provided() || bookingData.to_time_provided()){
            goto(FromTime(bookingData))
        }
        else{
            goto(AskForDate(bookingData))
        }

    }

    onResponse {
        if (bookingData.from_time_provided() || bookingData.to_time_provided()){
            goto(AskForDate(bookingData))
        }
        else{
            goto(Greeting)
        }

    }

    onNoResponse {
        goto(AskForDate(bookingData))
    }

}