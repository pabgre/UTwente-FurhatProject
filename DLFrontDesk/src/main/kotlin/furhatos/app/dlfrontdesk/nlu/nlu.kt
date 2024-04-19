package furhatos.app.dlfrontdesk.nlu

import furhatos.app.dlfrontdesk.enu.Room
import furhatos.nlu.Intent

class Booking : Intent()
class Navigation(
    val room : Room? = null
) : Intent()