package lectures.part3fp

import scala.util.Success
import scala.util.Failure
import scala.util.Try
import java.util.Random

object HandlingFailure extends App 
{
    val aSuccess = Success(3)
    val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

    println(aSuccess)
    println(aFailure)


    def unsafeMethod():String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

    // Try objects via the apply method 
    val potentialFailure = Try(unsafeMethod())
    println(potentialFailure)

    val anotherPotentialFailure = Try {

    }

    // utilities
    println(potentialFailure.isSuccess)

    // orElse 

    def backupMethod():String = "A valid result"

    val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())

    println(fallbackTry)


    // IF you design the API

    def betterUnsafeMethod():Try[String] = Failure(new RuntimeException)
    def betterBackupMethod():Try[String] = Success("A valid result")

    val betterFallback = betterUnsafeMethod() orElse  betterBackupMethod()


    // map, flatMap, filter 

    println(aSuccess.map(_*2))
    println(aSuccess.flatMap( x => Success(x*10)))
    println(aSuccess.filter(_ > 10))


    /*
        Exercise
    */

    val hostname = "localhost"
    val port = "8080"
    def renderHTML(page:String) = println(page)

    class Connection {
        def get(url:String):String = {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("Connection interrupted")            
        }        

        def getSafe(url:String):Try[String] = Try(get(url))
    }

    object HttpService
    {
        val random = new Random(System.nanoTime())
        
        def getConnection(host:String,port:String):Connection = 
            {
                if (random.nextBoolean()) new Connection
                else throw new RuntimeException("Someone else took the port")
            }

        def getSafeConnection(host:String,port:String):Try[Connection] = 
            Try(getConnection(host,port))
        
    }

    // if you get the HTML page from the connectoion, print it to the console i.t call renderHTML
    // This was MY SOLUTION
    println("I am about to use my awesome flatmap thing")

    println("First thing used")
    Try(
        HttpService
        .getConnection(hostname,port)
    )
    .flatMap(
        connection => Try(connection.get("google")) 
    )
    .foreach(renderHTML)
    
    val possibleConnection = HttpService.getSafeConnection(hostname,port)
    val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))

    println("Second thing used")
    possibleHTML.foreach(renderHTML)

    // shorthand version 

    HttpService
    .getSafeConnection(hostname,port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

    // for comprehension (MY FOR COMPREHENSION)
    println("Here goes my extremely nice for comprehension")
    for {
        connection <- HttpService.getSafeConnection(hostname,port)
        possibleHTML <- connection.getSafe("/home")
    } yield renderHTML(possibleHTML)


    println("Now the one from the instructor")
    for {
        connection <- HttpService.getSafeConnection(hostname,port)
        html <- connection.getSafe("/home")
    } renderHTML(html)

    println("This is exactly the same thing I did myself !!! ")
}