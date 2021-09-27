## How to make a Toast or any class Lifecycle aware?

We can make any class lifecycle aware by passing the `Lifecycle` from the activity to the required class.

**Example**

```
class TimerClass(
    private val context: Context,
    private val lifecycle: Lifecycle
) : LifecycleObserver {

    private lateinit var countDownTimer: CountDownTimer

    init {
        /**
         * Attaching the observer with the activity lifecycle so that we will be notified whenever
         * any of the lifecycle method gets called
         */
        lifecycle.addObserver(this)
    }

    /**
     * Starts a timer from 60 secs
     */
    fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                /*
                Showing the toast only if the activity is started
                 */
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                    Toast.makeText(
                        context,
                        "Time ${millisUntilFinished / 1000}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
            }

            override fun onFinish() {
            }

        }
    }


    /**
     * Whenever the Activity is in/ goes to onStart state we will restart the timer ( we cannot pause/resume count down timer)
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        countDownTimer.start()
    }

    /**
     * Whenever the activity goes to stop state, we will cancel the timer
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        countDownTimer.cancel()
    }
}
```

