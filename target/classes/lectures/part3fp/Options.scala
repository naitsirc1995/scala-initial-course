package lectures.part3fp

import java.util.Random

object Options extends App
{
    val myFirstOption:Option[Int] = Some(5)
    val noOption : Option[Int] = None 

    println(myFirstOption)

    // unsafe APIs
    def unsafeMthod():String = null
    //val result = Some(unsafeMthod()) // WRONG 

    val result = Option(unsafeMthod()) // Some or None
    
    // chained methods
    def backupMethod():String = "A valid result"
    
    val chainedResult = Option(unsafeMthod()).orElse(Option(backupMethod()))

    // DESIGN unsafe APIs 
    def betterUnsafeMethod():Option[String] = None 
    def betterBackupMethod():Option[String] = Some("A valid result")

    def betterChainedResult = betterBackupMethod() orElse betterBackupMethod()


    // functions on Options 
    println(myFirstOption.isEmpty)
    println(myFirstOption.get)  // UNSAFE - DO NOT USE THIS

    // map, flatMap, filter 

    println(myFirstOption.map(_*2))
    println(myFirstOption.filter(_ > 10))
    println(myFirstOption.flatMap(x => Option(x*10)))

    // for-comprehensions 

    /*
        Exercise.
    */

    val config: Map[String,String] = Map(
        // fetched from elsewhere
        "host" -> "176.45.36.1",
        "port"->"80"
    )

    class Connection 
    {
        def connect = "Connected" // connect to some server         
    }

    object Connection {
        val random = new Random(System.nanoTime())

        def apply(host:String,port:String):Option[Connection] = 
            if (random.nextBoolean()) Some(new Connection)
            else None 
    }

    // try to establish a connection, if so-print the connect method
    val host = config.get("host")
    val port = config.get("port")
    /*
        if (h!=null)
            if (p!=null)
                return Connection(h,p)

        return null 
    */
    val connection = host.flatMap( h => port.flatMap( p => Connection(h,p)) )

    /*
        if (c!=null)
            return c.connect
        return null
    */

    val connectionStatus = connection.map(c => c.connect)
    // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))

    println(connectionStatus)
    connectionStatus.foreach(println)
    /*
    if (status != null)
        println
    None 
    */
        
    config
    .get("host")
    .flatMap(
        host =>
            config.get("port")
            .flatMap(port => Connection(host,port))
            .map(connection => connection.connect )            
    )
    .foreach(
        println
    )


    // for-comprehensions 

    val forConnectionStatus = for {
        host <- config.get("host")
        port <- config.get("port")
        connection <- Connection(host,port)
    } yield connection.connect

    forConnectionStatus.foreach(println)

    // Wow, this topic almost breakes my mind !!  It was crazy 
}