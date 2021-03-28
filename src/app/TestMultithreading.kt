package app

import java.util.concurrent.Executors

class TestMultithreading {

    fun test() {
        val counter = Counter()
        val thread = Thread {
//        counter.count = 1
            counter.count()
            println("Thread 1, count = ${counter.count}")
        }
        thread.start()
        Thread {
//        counter.count = 100
            counter.count()
            println("Thread 2, count = ${counter.count}")
        }.start()

        val executor = Executors.newCachedThreadPool()
        executor.execute {
            counter.count()
            println("Executor, count = ${counter.count}")
        }
    }

    class Counter {

        @Volatile
        var count: Int = 0
            private set

        fun count() {
            println("Thread #${Thread.currentThread().name} came to count()")
            synchronized(this) {
                for (index in 1..100) {
                    Thread.sleep(10)
                    count++
                }
            }
        }


    }

}