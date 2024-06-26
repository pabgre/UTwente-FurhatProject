package furhatos.app.dlfrontdesk.flow.main

import furhatos.app.dlfrontdesk.enu.Room
import furhatos.app.dlfrontdesk.flow.Parent
import furhatos.app.dlfrontdesk.flow.main.booking.Bye
import furhatos.app.dlfrontdesk.nlu.Navigation
import furhatos.app.dlfrontdesk.utils.ANIMATIONS
import furhatos.app.dlfrontdesk.utils.CustomGestures
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*

fun Directions(room : Room?): State = state(Parent) {
    onEntry {
        furhat.setDefaultMicroexpression(blinking = true, facialMovements = true, eyeMovements = true)

        when (room?.value.toString()){
            "ideate" -> {
                furhat.say(async = true) {
                    random {
                        +"Ideate is right over here on your right!"
                        +"See that open space on your right? That's Ideate"
                        +"Ideate is that open space on your right behind the wooden hives"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointLeft), async = false)
                delay(2000)
            }
            "conceptualize"-> {

                furhat.say(async = true) {
                    random {
                        +"Conceptualize is on the left side of the couches in the lounge area behind me!"
                        +"See that workshop on your left? conceptualize is next to it to the right"
                        +"Just go to your left, you'll see conceptualize on your right when you reach the workshop"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointRight), async = false)
                delay(2000)
            }
            "inspire"-> {
                furhat.say(async = true) {
                    random {
                        +"To reach the Inspire, go through the hallway behind you, head to the PlayGround with black flooring, and find inspire, the first room on your left"
                        +"see that hallway behind you? past through it and when you reach that area with black flooring, Inspire is the first room at your left"
                        +"Go past through the hallway behind you, when you reach those interesting installation in the black flooring area, Inspire is the first room at your left"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace), async = false)
                delay(2000)
            }
            "learn-x"-> {
                furhat.say(async = true) {
                    random {
                        +"To reach the Learn-X, go through the hallway behind you, head to the PlayGround with black flooring, and find Learn-X, the second room on your left"
                        +"see that hallway behind you? past through it and when you reach that area with black flooring, Learn-x is the second room at your left"
                        +"Go past through the hallway behind you, when you reach those interesting installation in the black flooring area, Learn-X is the second room at your left"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace), async = false)
                delay(2000)
            }
            "inform"-> {

                furhat.say(async = true) {
                    random {
                        +"To reach the Inform, go through the hallway behind you, head to the PlayGround with black flooring, and find Inform, the room that is on your right"
                        +"see that hallway behind you? past through it and when you reach that area with black flooring, Inform is the only room at your right"
                        +"Go past through the hallway behind you, when you reach those interesting installation in the black flooring area, Inform is the room at your right"
                    }
                }
                delay(2000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace), async = false)
                delay(2000)

            }
            "invite"-> {

                furhat.say(async = true) {
                    random {
                        +"Head downstairs using the stairway that can be found through the first door on your left in the hallway behind you. INVITE is the last room in the downstairs hallway before the exit door"
                        +"To go to Invite you need to go downstairs and then turn left in the hallway, Invite is the last room on your right"
                        +"Just go downstairs using the stairway behind you, Invite is the last room in the left side of the hallway"
                    }

                }
                delay(200)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointDown), async = false)
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointLeft), async = false)
            }
            "connect"-> {

                furhat.say(async = true) {
                    random {
                        +"Head downstairs using the stairway that can be found through the first door on your left in the hallway behind you. CONNECT is the first room you see in the downstairs hallway"
                        +"To go to Invite you need to go downstairs and then the first room you'll see is Connect"
                        +"Just go downstairs using the stairway behind you, connect is the first room you see"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointDown), async = false)
                delay(4000)

            }
            "play"-> {

                furhat.say(async = true) {
                    random {
                        +"Head downstairs using the stairway that can be found through the first door on your left in the hallway behind you. PLAY is the room Infront of the downstairs’ kitchen area"
                        +"To go to Play you need to go downstairs and then the second room is play on your right side"
                        +"Just go downstairs using the stairway behind you, play is the room next to the kitchen there"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointDown), async = false)
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointRight), async = false)
                delay(2000)

            }
            "m-workshop"-> {

                furhat.say(async = true) {
                    random {
                        +"m-Workshop is directly on your left!"
                        +"See that workshop on your left? that's the m-Workshop"
                        +"look at your left, m-workshop is right over there"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointRight), async = false)
                delay(2000)

            }
            "e-workshop"-> {

                furhat.say(async = true) {
                    random {
                        +"For the e-Workshop, go left, past the work desks and monitors, and it will be on your right"
                        +"To go to e-Workshop, head to your left, it's right next to m-workshop"
                        +"Just go to your left and pass the m-Workshop, e-Workshop is next to it"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointRight), async = false)
                delay(2000)

            }
            "t-workshop"-> {

                furhat.say(async = true) {
                    random {
                        +"For the t-Workshop, go left, past the work desks and monitors, and it will be on your right"
                        +"To go to t-Workshop, head to your left, it's right next to m-workshop"
                        +"Just go to your left and pass the m-Workshop, t-Workshop is next to it"
                    }
                }
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointRight), async = false)
                delay(2000)

            }
            "d-workshop"-> {

                furhat.say(async = true) {
                    random {
                        +"Head downstairs using the stairway that can be found through the first door on your left in the hallway behind you.  d-Workshop is the last room on your left in the downstairs hallway before the exit door"
                        +"To go to the d-Workshop you need to go downstairs and then turn left, it is the first door to your left"
                        +"Just go downstairs using the stairway behind you, d-Workshop is the first door to your left in the left side of the hallway"
                    }
                }

                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointDown), async = false)
                delay(1000)
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointLeft), async = false)
                delay(2000)


            }
            "techteam-desk"-> {
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointRight), async=true)
                furhat.say {
                    random {
                        +"Techteam Desk is directly on your left next to the m-Workshop!"
                        +"Just look at your left, you'll see their desk next to the 3d printers"
                        +"see those 3d printers over there on your left side? Techteam Desk is there"
                    }
                }

            }
            "entrance"-> {
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointLeft), async=true)
                furhat.say {
                    random {
                        +"Main entrance is on your right via the stairway"
                        +"see the door on your right next to my desk? If you go downstairs there you'll see the main entrance door"
                        +"We have two entrances, the main one is right over here the door next to the desk, the other one is behind you through the hallway to the gallery"
                    }
                }



            }
            "exit"-> {
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointLeft))
                furhat.say {
                    random {
                        +"Main exit is on your right via the stairway"
                        +"You can exit DesignLab either from here on your right via the stairway or from going through the hallway behind you"
                        +"you can exit using the main exit right over here on your right, or going through the hallway behind you through the gallery"
                    }
                }


            }
            "kitchen"-> {
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointLeft), async = true)
                furhat.say {
                    random {
                        +"Just go a bit to your right and you’ll see the kitchen at your left."
                        +"The Kitchen is right next to ideate, on your right side"
                        +"You can find kitchen if you go a bit to your right"
                    }
                }


            }
            "lounge"-> {
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.PointBehind), async = true)
                furhat.say {
                    random {
                        +"The lounge area is just behind the exhibition table right behind me, I think you can see the couches already from here."
                        +"you want to rest? the lounge area is right behind my desk, it's perfect for chilling"
                        +"see those couches over there behind my desk? that is the Lounge area"
                    }
                }


            }
            "toilet"-> {
                furhat.gesture(CustomGestures().get_gesture(ANIMATIONS.KindFace), async=true)
                furhat.say {
                    random {
                        +"Go through the hallway behind you, the toilets will be at your right."
                        +"the toilets are everywhere in the hallway behind you, the colsest one is right infront of the stairway behind you"
                        +"you can find the toilet in the hallway behind you, on your right"
                    }
                }
                delay(200)



            }
            "a room" -> {
                val room_name = furhat.askFor<Room>("Which room are you looking for?")
                goto(Directions(room_name))
            }

        }
        delay(3000)
        goto(Bye)
    }


}