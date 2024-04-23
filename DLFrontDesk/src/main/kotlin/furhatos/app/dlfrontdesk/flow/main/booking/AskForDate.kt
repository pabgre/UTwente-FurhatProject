package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Date

fun AskForDate(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.date_provided()){
            if (bookingData.bookable_date()){
                goto(FromTime(bookingData))
            }
            else{
                furhat.say {
                    random {
                        +"Sadly I can't help you with that. Try contacting First Line Support my friend"
                        +"Oh I am so sorry! Unfortunately we can only book rooms up to 24 hours in advance! You will need to contact first line support for that"
                        +"My bad human! If you want to book a room more than 24 hours in advance you will need to contact First Line Support! You can find their contact right under my nose"
                    }
                }
                goto(Idle)
            }
        }
        else {
            furhat.ask {random{
                +"So tell me please. Do you want a room for today, for tomorrow...?"
                +"Beautiful, and for what date would you like to book this room?"
                +"In which date would you like to book the room?"
            }}
        }
    }
    onResponse<Date> {
        bookingData.date = it.intent.asLocalDate()
        reentry()
    }
    onResponse {
        furhat.say{
            random{
                +	"	No clue what you said human. Please repeat	"
                +	"	Sorry human, I didn't catch what you said. Could you repeat it?	"
                +	"	I must have some wax in my ears. Could you repeat that for me?	"
            }
        }
        reentry()
    }

}