package furhatos.app.dlfrontdesk.flow.main

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.booking.Booking
import furhatos.app.dlfrontdesk.nlu.Booking
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.event.actions.ActionGaze
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures.getResourceGesture
import furhatos.records.Location
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime


val Greeting: State = state(Parent) {
    onEntry {
        if (users.current.isAttendingFurhat()) {
            furhat.ask{ random{
                +	"How can I help you?"
                +	"What can I assist you with?"
            } }
        }
    }

    onUserAttend(instant = true) {user ->
        if (user.isAttendingFurhat()) {
            furhat.ask{ random{
                +	"How can I help you?"
                +	"What can I assist you with?"
            } }
        } else {
            furhat.say { random{
                +	"Down here! I can help you"
                +	"Hey! Down here! Let me help you out!"
                +	"Pssst! Let me lend you a hand! ... Or a head!"
            } }
        }
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
            furhat.say { random{
                +	"Let's see if we can arrange something"
                +	"Let's check if we can do something about that"
            } }
        }


        val bookingData = BookingData(date = date, from = from, to = to, room_name = room_name, nb_people = nb_people, user_id= it.userId)

        goto(Booking(bookingData))
    }

    onNoResponse {
        furhat.say { random{
            +	"Down here! I can help you"
            +	"Hey! Down here! Let me help you out!"
            +	"Pssst! Let me lend you a hand! ... Or a head!"
        } }
        reentry()
    }

    onResponse {
        furhat.say { random{
            +	"Sorry. I couldn't hear properly. I can help you with room booking and navigation"
            +	 "My bad human! I did not catch what you said. But as of right now I can help you with room booking and directions"
        } }
        reentry()
    }


}

