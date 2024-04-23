package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.enu.Room
import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

fun RoomName(bookingData: BookingData): State = state(Parent) {
    onEntry {
        if (bookingData.room_name_provided()){
            var room_name = bookingData.room_name
            if (bookingData.bookable_room()){
                furhat.say{random {
                    +	"Great choice! Let me check if it is available... One second	"
                    +	"Beautiful! Let me see if $room_name is avaiable for you!"
                    +	"$room_name Got it! Let me check if it's free"
                    +	"Beautiful! Let me see if $room_name is free for you!"
                    +	"$room_name Got it! Let me check if it's available"
                goto(RoomAvailable(bookingData))
            }}}
            else{
                if (bookingData.room_name == "toilet"){
                    furhat.ask{ random{
                        +	"	Really? What are you planning to there? Don't tell me... please	"
                        +	"	Okay... I won't ask any questions...	"
                    }}
                }
                else{
                    furhat.ask{ random{
                        +	"	I can't help you with booking that room. Do you have any other room in mind?	"
                        +	"	Sorry human, but I'm afraid that room is not bookable. Take a look at the map behind me and choose a new room	"
                        +	"	Oh oh, seems like I don't have access to book that room. Please choose a different one.	"
                    }}
                }
            }

        }else{

            furhat.ask {  random{
                +	"	Do you have any room in mind?	"
                +	"	Sooo, do you know what room you want?	"
            } }
        }
    }

    onResponse<Yes> {
        bookingData.room_name = furhat.askFor<Room>("Tell me!")?.value
        reentry()
    }

    onPartialResponse<Yes> {
        raise(it, it.secondaryIntent)
    }

    onResponse<Room>{
        bookingData.room_name = it.intent.value
        reentry()
    }

    onResponse{
        furhat.say{ random{
            +	"	Let me find something for you...	"
            +	"	Give me a second, I will find something for you...	"
            +	"	Hang in there human! Let me find something for you...	"
        }}
        goto(DecideRoom(bookingData))
    }

}