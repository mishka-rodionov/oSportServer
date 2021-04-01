package app

import java.util.concurrent.Executors

class TestMultithreading {

    //    val json = Gson().toJson(UserRequest(sportRanks = hashMapOf(
//            Sport(sportId = "123", name = "Orienteering").toString() to SportRank.MASTER_OF_SPORT.name,
//            Sport(sportId = "123", name = "Cross Country").toString() to SportRank.HONORED_MASTER_OF_SPORT.name
//    )))
//    println("json = $json")
//    val user = Gson().fromJson<UserRequest>(json)
//    println("user = $user")

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