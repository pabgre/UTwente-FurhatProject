package furhatos.app.dlfrontdesk

import furhatos.app.dlfrontdesk.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class DlfrontdeskSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
