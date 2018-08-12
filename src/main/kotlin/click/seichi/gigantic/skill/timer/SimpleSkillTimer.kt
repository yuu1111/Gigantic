package click.seichi.gigantic.skill.timer

import click.seichi.gigantic.Gigantic
import click.seichi.gigantic.schedule.Scheduler
import click.seichi.gigantic.skill.Skill
import io.reactivex.Observable
import org.bukkit.Bukkit
import java.util.concurrent.TimeUnit

/**
 * @author tar0ss
 */
open class SimpleSkillTimer(skill: Skill) : SkillTimer {

    var isCancelled = false

    val coolTime: Long = skill.coolTime

    var remainTimeToFire: Long = 0L
        private set

    private var onStart: () -> Unit = {}

    fun onStart(starting: () -> Unit): SimpleSkillTimer {
        onStart = starting
        return this
    }

    private var onCooldown: (Long) -> Unit = {}

    fun onCooldown(cooling: (Long) -> Unit): SimpleSkillTimer {
        onCooldown = cooling
        return this
    }

    private var onCompleteCooldown: () -> Unit = {}

    fun onCompleteCooldown(completeCooling: () -> Unit): SimpleSkillTimer {
        onCompleteCooldown = completeCooling
        return this
    }

    override fun duringCoolTime() = remainTimeToFire != 0L

    override fun canStart() = !duringCoolTime()


    override fun start() {
        remainTimeToFire = coolTime
        onStart()
        if (isCancelled) {
            end()
            return
        }
        Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile {
                    it < coolTime && !isCancelled
                }.take(coolTime, TimeUnit.SECONDS)
                .observeOn(Scheduler(Gigantic.PLUGIN, Bukkit.getScheduler()))
                .subscribe({ elapsedSeconds ->
                    remainTimeToFire = coolTime.minus(elapsedSeconds + 1)
                    onCooldown(remainTimeToFire)
                }, {}, {
                    end()
                })
    }

    private fun end() {
        remainTimeToFire = 0L
        isCancelled = false
        onCompleteCooldown()
    }
}