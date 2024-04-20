package furhatos.app.dlfrontdesk.nlu

import furhatos.app.dlfrontdesk.enu.Activity
import furhatos.app.dlfrontdesk.enu.DurationScale
import furhatos.app.dlfrontdesk.enu.Room
import furhatos.nlu.Intent
import furhatos.nlu.common.Time
import furhatos.nlu.common.Date
import furhatos.nlu.common.Number
class Booking(
    val room : Room? = null,
    val activity: Activity? = null,
    val people: Number? = null,
    val date: Date? = null,
    val from : Time? = null,
    val to : Time? = null,
    val duration : Number? = null,
    val durationscale : DurationScale? = null
) : Intent()
class Navigation(
    val room : Room? = null
) : Intent()