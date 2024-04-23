package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Directions
import furhatos.app.dlfrontdesk.flow.main.Greeting
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.nlu.Booking
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import java.time.LocalDate
import java.time.LocalTime

val Bye: State = state(Parent) {
    onEntry {
        furhat.ask{
            random{
                +	"It was a pleasure. Is there anything else I can help you with?"
                +	"I am glad I could help you! Is there anything else I can assist you with?"
            }
        }

    }

    onUserLeave {
        furhat.say { random{
            +	"Have a creative day"
            +	"Have a great day!"
            +	"Have a good day!"
            +	"Enjoy your day!"
            +	"Bye bye!"
            +	"See you!"
            +	"See you later alligator!"
        } }
        goto(Idle)
    }

    onResponse<Yes> {
        goto(Greeting)
    }

    onPartialResponse<Yes> {
        raise(it, it.secondaryIntent)
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

    onResponse<No> {

        furhat.say { random{
            +	"You can go in peace human"
            +	"You are dismissed"
            +	"We are done here, bye bye!"
        } }
    }

    onResponse {
        furhat.say { random{
            +	"Whatever... I will go to my default state"
            +	"Alright then... I will just go to sleep"
        } }
    }




}