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
            if (bookingData.bookable_room()){
                furhat.say("Great choice! Let me check if it is available... One second")
                goto(RoomAvailable(bookingData))
            }
            else{
                if (bookingData.room_name == "toilet"){
                    furhat.ask("Really? What are you planning to there? Don't tell me... please")
                }
                else{
                    furhat.ask("I can't help you with booking that room. Do you have any room in mind?")
                }
            }

        }else{

            furhat.ask("Do you have any room in mind?")
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
        furhat.say("Let me find something for you...")
        goto(DecideRoom(bookingData))
    }

}