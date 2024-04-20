package furhatos.app.dlfrontdesk.flow.main

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.booking.Booking
import furhatos.app.dlfrontdesk.nlu.Booking
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.*
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

val Greeting: State = state(Parent) {
    onEntry {
        furhat.ask("Hello there! How can I help you?")
    }


    onResponse<Navigation> {
        goto(Directions(it.intent.room))
    }

    onResponse<Booking>{
        var from : LocalTime? = it.intent.from?.asLocalTime()
        var to : LocalTime? = it.intent.to?.asLocalTime()
        val duration : Number? = it.intent.duration?.value
        var room_name : String? = it.intent.room?.value
        var activity : String? = it.intent.activity?.value
        var date : LocalDate? = it.intent.date?.asLocalDate()
        var nb_people : Int? = it.intent.people?.value
        var duration_scale : String? = it.intent.durationscale?.value
        if (duration != null){
            if (from == null){
                from = LocalTime.now()
            }
        }
        if (activity != null){
            furhat.say("Let me help you with your booking for your " + activity)
        }else{
            furhat.say("Let's see if we can arrange something")
        }

        val bookingData = BookingData(date = date, from = from, to = to, room_name = room_name, nb_people = nb_people)

        goto(Booking(bookingData))
    }


}

