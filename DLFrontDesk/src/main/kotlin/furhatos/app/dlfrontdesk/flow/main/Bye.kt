package furhatos.app.dlfrontdesk.flow.main.booking

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.Greeting
import furhatos.app.dlfrontdesk.flow.main.Idle
import furhatos.app.dlfrontdesk.utils.BookingData
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

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