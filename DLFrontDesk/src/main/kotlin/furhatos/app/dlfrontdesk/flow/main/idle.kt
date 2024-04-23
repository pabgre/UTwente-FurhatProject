package furhatos.app.dlfrontdesk.flow.main

import furhatos.event.actions.ActionGaze
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

val Idle: State = state {
    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        furhat.say { random{
            +	"Hello there!"
            +	"Hey hey!"
            +	"Wasabi"
        } }
        goto(Greeting)
    }



}
