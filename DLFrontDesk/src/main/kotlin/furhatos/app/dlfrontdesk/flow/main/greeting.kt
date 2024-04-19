package furhatos.app.dlfrontdesk.flow.main

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.nlu.Booking
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.flow.kotlin.*

val Greeting: State = state(Parent) {
    onEntry {
        furhat.ask("Hello there! How can I help you?")
    }


    onResponse<Navigation> {
        goto(Directions(it.intent.room))
    }


}

