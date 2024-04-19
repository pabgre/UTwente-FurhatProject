package furhatos.app.dlfrontdesk.flow.main

import furhatos.app.dlfrontdesk.enu.Room
import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.flow.kotlin.*

fun Booking(room : Room?): State = state(Parent) {
    onEntry {
    }

}