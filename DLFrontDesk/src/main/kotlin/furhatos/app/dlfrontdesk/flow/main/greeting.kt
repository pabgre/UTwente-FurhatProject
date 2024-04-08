package furhatos.app.dlfrontdesk.flow.main

import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val Greeting: State = state(Parent) {
    onEntry {
        furhat.ask("Welcome to DesignLab how can I help you?")
    }

    onResponse<Yes> {
        println(it.speech.language.code)
        furhat.say("Hello World! ")
    }

    onResponse<No> {
        furhat.say("Ok.")

    }

}

