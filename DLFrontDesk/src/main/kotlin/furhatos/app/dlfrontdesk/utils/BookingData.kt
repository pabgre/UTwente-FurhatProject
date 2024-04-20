package furhatos.app.dlfrontdesk.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.Period

class BookingData(var date: LocalDate? = null, var from: LocalTime? = null, var to: LocalTime? = null, var room_name: String? = null, var nb_people : Int? = null) {
    var bookable_rooms = arrayOf("play", "invite", "connect")

    fun date_provided() : Boolean{
        return date != null
    }

    fun from_time_provided() : Boolean{
        return from != null
    }

    fun to_time_provided() : Boolean{
        return to != null
    }

    fun bookable_date() : Boolean{
        val period = Period.between(date, LocalDate.now().plusDays(1))
        return !period.isNegative && period.days <= 1
    }

    fun valid_from_time() : Boolean{
        val hour : Int? = from?.hour?.toInt()
        return hour != null && 8 < hour && hour < 20
    }

    fun valid_to_time() : Boolean{
        val hour : Int? = to?.hour?.toInt()
        val from_h : Int? = from?.hour?.toInt()
        return hour != null  && from_h != null && from_h <= hour && 9 < hour && hour < 21
    }

    fun room_name_provided() : Boolean{
        return room_name != null
    }

    fun nb_people_provided() : Boolean{
        return nb_people != null
    }

    fun find_fiting_room(){
        room_name = "play"
    }

    fun room_available() : Boolean{
        return true
    }

    fun inmediate_booking() : Boolean {

        return true
    }
}