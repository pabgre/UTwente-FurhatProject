package furhatos.app.dlfrontdesk.utils

import com.sun.org.apache.xpath.internal.operations.Bool
import furhatos.records.User
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period

class BookingData(var date: LocalDate? = null, var from: LocalTime? = null, var to: LocalTime? = null, var room_name: String? = null, var nb_people : Int? = null, var user_id: String? = null) {

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

    fun number_to_time(t : Int) : LocalTime{
        var h : Int = 0
        var m : Int = 0
        if (24 <= t && t < 100){
            h = t % 24
        }
        else if (t >= 100){
            h = t / 100
            m = t % 100
        }
        else{
            h = t
            m = 0
        }

        m %= 60
        if (h < 8){
            h += 12
        }
        return (LocalTime.of(h, m))
    }

    fun valid_from_time() : Boolean{
        val hour : Int? = from?.hour?.toInt()

        return true
    }

    fun valid_to_time() : Boolean{
        val hour : Int? = to?.hour?.toInt()
        val from_h : Int? = from?.hour?.toInt()
        return true
    }

    fun room_name_provided() : Boolean{
        return room_name != null && room_name != "a room"
    }

    fun nb_people_provided() : Boolean{
        return nb_people != null
    }

    fun find_fiting_room(){
        if (nb_people!! < 8){
            room_name = "play"
        }
        else if (nb_people!! < 15){
            room_name = "invite"
        }
        else{
            room_name = "connect"
        }

    }

    fun bookable_room() : Boolean{
        return room_name == "play" || room_name == "connect" || room_name == "invite" || room_name == "d-workshop"
    }

    fun room_available() : Boolean{

        return room_name != "invite"
    }

    fun inmediate_booking() : Boolean {

        return true
    }

    fun get_from_time(): String{
        val hour : Int? = from?.hour?.toInt()!! % 12
        val min : Int? = from?.minute?.toInt()
        var out : String = hour?.toString().toString()
        if (min != 0){
            out += " " + min?.toString()
        }
        return out
    }

    fun get_to_time(): String{
        val hour : Int? = to?.hour?.toInt()!! % 12
        val min : Int? = to?.minute?.toInt()
        var out : String = hour?.toString().toString()
        if (min != 0){
            out += " " + min?.toString()
        }
        return out
    }

}