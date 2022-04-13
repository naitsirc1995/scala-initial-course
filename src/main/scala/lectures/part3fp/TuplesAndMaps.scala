package lectures.part3fp

object TuplesAndMaps extends App 
{
    // tuples = finite ordered "lists"

    val aTuple = (2,"Hello Scala") // Tuple2[Int,String] = (Int,String)

    println(aTuple._1)
    println(aTuple._2)
    println(aTuple.copy(_2 = "goodbye Java!"))
    println(aTuple.swap) // ("hello Scala",2)

    // Maps - keys -> values
    val aMap:Map[String,Int] = Map()

    val phonebook = Map(("Jim",555),"Daniel"->789).withDefaultValue(-1)
    // a-> b is sugar for (a,b)
    println(phonebook)

    // map ops
    println(phonebook.contains("Jim"))
    println(phonebook("Jim"))
    println(phonebook("Mary"))


    val newPairing = "Mary" -> 678
    val newPhonebook = phonebook + newPairing

    println(newPhonebook)


    // Functionals on maps 
    // map, flatMap, filter 

    println(phonebook.map(pair=>pair._1.toLowerCase() -> pair._2))

    // filterKeys 
    println(phonebook.filterKeys(_.startsWith("J")))

    // mapValues 
    println(phonebook.mapValues(number => number*10))
    println(phonebook.mapValues(number => "0245-"+number ))


    // conversions to other collections 

    println(phonebook.toList)


    println(List(("Daniel",555)).toMap)

    val names = List("Bob","James","Angela","Mary","Daniel","Jim")
    println(names.groupBy(name => name.charAt(0)))


    /*
        1. What would happen if I had two original entres "Jim" -> 555 and "JIM" -> 900
        2. Overly simplified social network based on maps 
            Person = String
            - add a person to the network
            - remove
            - friend (mutual) Person adds a person as a friend
            - unfriend
            - number of friends of a person
            - person with most friends
            - how many people have NO friends
            - if there is a social connection between two people (direct or not)
    */


    object myImplementation
    {
        val aMap:Map[String,Int] = 
            Map("Jim"->555 , "JIM"->999)

        val myNetwork:Map[String,List[String]] = 
            Map(
                "Cristian"->List("Sebas","Vale"),
                "Vale"->List("Cristian","Sebas","Nicole","Andres","Catalina","Luisa"),
                "Sebas"->List("Vale","Cristian","Nicole"),
                "Andres"->List("Valentina"),
                "Nicole"->List("Sebas","Vale","Cristian"),
                "Andres"->List("Vale"),
                "Catalina"->List("Vale"),
                "Luisa"->List("Vale","Sebas","Cristian")
            )
            .withDefaultValue(List("Not Found"))


        def addPerson(
            person:String,
            network:Map[String,List[String]]
        ):Map[String,List[String]] =
            {
                val newPair = (person,List[String]())
                network + newPair
            }

        def removePerson(
            person:String,
            network:Map[String,List[String]]
        ):Map[String,List[String]] = 
            {
                                                                
            }
    }


    println(myImplementation.aMap.map(x=>x._1.toUpperCase()->x._2))

    
    
    println(
        myImplementation
        .addPerson(
            "Xavier",
            myImplementation.myNetwork
        )
    )
    
    
}