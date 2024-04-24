package furhatos.app.dlfrontdesk.utils

import furhatos.gestures.Gesture
import furhatos.gestures.Gestures.getResourceGesture

enum class ANIMATIONS{
    PointRight,
    PointLeft,
    PointDown,
    Nod,
    Bow,
    Question1,
    Question2,
    Question3,
    Affirmation1,
    KindFace,
    Confused1,
    Confused2,
    SideEye,
    PointBehind
}

class CustomGestures {
    val anims = mapOf(
        ANIMATIONS.PointRight to getResourceGesture("/Gestures/0.POINT-RIGHT.json"),
        ANIMATIONS.PointLeft to getResourceGesture("/Gestures/1.POINT-LEFT.json"),
        ANIMATIONS.PointDown to getResourceGesture("/Gestures/2.POINT-DOWN.json"),
        ANIMATIONS.Nod to getResourceGesture("/Gestures/3.CASUAL-NOD.json"),
        ANIMATIONS.Bow to getResourceGesture("/Gestures/4.APOLOGETIC-BOW.json"),
        ANIMATIONS.Question1 to getResourceGesture("/Gestures/5.QUESTION-1.json"),
        ANIMATIONS.Question2 to getResourceGesture("/Gestures/6.QUESTION-2.json"),
        ANIMATIONS.Question3 to getResourceGesture("/Gestures/7.QUESTION-3.json"),
        ANIMATIONS.Affirmation1 to getResourceGesture("/Gestures/8.AFFIRMATION-1.json"),
        ANIMATIONS.KindFace to getResourceGesture("/Gestures/9.KIND-FACE.json"),
        ANIMATIONS.Confused1 to getResourceGesture("/Gestures/10.CONFUSED-1.json"),
        ANIMATIONS.Confused2 to getResourceGesture("/Gestures/11.CONFUSED-2.json"),
        ANIMATIONS.SideEye to getResourceGesture("/Gestures/12.SIDE-EYE.json"),
        ANIMATIONS.PointBehind to getResourceGesture("/Gestures/13.POINT_BEHIND.json")
        )

    fun get_gesture(anim : ANIMATIONS) : Gesture{
        return(anims[anim]!!)
    }

}