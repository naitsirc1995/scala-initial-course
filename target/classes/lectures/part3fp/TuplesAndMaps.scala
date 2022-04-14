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
                if (network(person) == List("Not Found"))
                {
                    throw new NoSuchElementException
                } else {
                    network
                    .filterKeys(
                        key => key != person
                    )
                    .mapValues{
                        x => 
                            if (x.contains(person)) x.filter(_ != person)
                            else x 
                    }
                }
            }

        def addFriend(
            sourceFriend:String,
            friendToAdd:String,
            network:Map[String,List[String]]
        ):Map[String,List[String]] = 
            {

                val nonExistentMember = List("Not Found")
                if (
                    network(sourceFriend) == nonExistentMember 
                    || network(friendToAdd) == nonExistentMember
                )  throw new NoSuchElementException
                else {
                    network
                    .map{x => 
                        if (x._1 == sourceFriend ) x._1 -> (x._2 :+ friendToAdd)
                        else if (x._1 == friendToAdd ) x._1 -> (x._2 :+ sourceFriend)
                        else x
                        }
                }
            }

        def removeFriend(
            sourceFriend:String,
            friendToAdd:String,
            network:Map[String,List[String]]
        ):Map[String,List[String]] = 
            {
                val nonExistentMember = List("Not Found")
                if (
                    network(sourceFriend) == nonExistentMember 
                    || network(friendToAdd) == nonExistentMember
                )  throw new NoSuchElementException
                else {
                    network
                    .map{x =>              
                        if (x._1 == sourceFriend) x._1 -> x._2.filter(_!= friendToAdd).toList
                        else if (x._1 == friendToAdd) x._1 -> x._2.filter(_!= sourceFriend).toList
                        else x 
                        }
                }
                
            }

        def numberOfFriends(
            member:String,
            network:Map[String,List[String]] 
        ):Int = 
            {
                if (network(member) == List("Not Found")) throw new NoSuchElementException
                else network(member).size
            }


        def mostFriends(
            network:Map[String,List[String]]
        ):List[String] =
            {
                network
                .filter(
                    x => x._2.size == network.map(x => x._1 -> x._2.size).values.max
                )
                .keySet
                .toList                
            }

        def noFriends(
            network:Map[String,List[String]]
        ):List[String] =
            {
                network
                .filter(
                    x => x._2.size == 0
                )
                .keySet
                .toList                
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

    println("Now the removal of a person")
    println(
        myImplementation
        .removePerson(
            "Nicole",
            myImplementation.myNetwork
        )
    )

    println("Now the add friend")
    println(
        myImplementation
        .addFriend(
            "Cristian",
            "Catalina",
            myImplementation.myNetwork
        )
    )

    println("Now the remove friend option")
    println(
        myImplementation
        .removeFriend(
            "Cristian",
            "Vale",
            myImplementation.myNetwork
        )
    )

    // println("Now the sum of all friends")
    // println(
    //     myImplementation
    //     .numberOfFriends(
    //         "Vale",
    //         myImplementation.myNetwork
    //     )
    // )

    // println(
    //     myImplementation
    //     .mostFriends(
    //         myImplementation.myNetwork
    //     )
    // )

    // println(
    //     myImplementation
    //     .noFriends(
    //         myImplementation.myNetwork
    //     )
    // )


    // Now the instructor's solution 
    def add(network:Map[String,Set[String]],person:String):Map[String,Set[String]] = 
        network + (person -> Set())
    
    def friend(network:Map[String,Set[String]],a:String,b:String):Map[String,Set[String]] = 
        {
            val friendsA = network(a)
            val friendsB = network(b)

            network + (a-> (friendsA + b)) + ( b ->  (friendsB + a) )
        }
        
    def unFriend(network:Map[String,Set[String]],a:String,b:String):Map[String,Set[String]] = 
        {
            val friendsA = network(a)
            val friendsB = network(b)

            network + ( a -> (friendsA - b)) + ( b -> (friendsB - a))
        }

    
    
    
    def remove(network:Map[String,Set[String]],person:String):Map[String,Set[String]] = 
        {                        
            def removeAux(friends:Set[String],networkAcc:Map[String,Set[String]]):Map[String,Set[String]] = 
                if (friends.isEmpty) networkAcc
                else removeAux(friends.tail, unFriend(networkAcc,person,friends.head))

            val unfriend = removeAux(network(person),network)
            unfriend - person
        }

    
    val empty:Map[String,Set[String]] = Map()
    val network = add(add(empty,"Bob"),"Mary")    
    println(friend(network,"Bob","Mary"))
    println(network)

    println(friend(network,"Bob","Mary"))
    println(unFriend(friend(network,"Bob","Mary"),"Bob","Mary"))
    println(remove(friend(network,"Bob","Mary"),"Bob"))


    // Jim,Bob,Mary

    val people = add(add(add(empty,"Bob"),"Mary"),"Jim")
    val jimBob = friend(people,"Bob","Jim")
    val maryBob =friend(jimBob,"Bob","Mary")
    val testNet = friend(jimBob,"Bob","Mary")

    println(testNet)

    def nFriends(network:Map[String,Set[String]],person:String):Int = 
        if (!network.contains(person)) 0
        else network(person).size

    println(nFriends(testNet,"Bob"))

    def mostFriends(network:Map[String,Set[String]]):String = 
        network.maxBy(pair => pair._2.size)._1

    println(mostFriends(network))

    def nPeopleWithNoFriends(network:Map[String,Set[String]]):Int = 
        network.count(_._2.isEmpty)
        
    
    println(nPeopleWithNoFriends(testNet))


    def socialConnection(network:Map[String,Set[String]],a:String,b:String):Boolean = 
        {
            def bfs(target:String,consideredPeople:Set[String],discoveredPeople:Set[String]):Boolean = 
                {
                    if (discoveredPeople.isEmpty) false 
                    else {
                        val person = discoveredPeople.head
                        if ( person == target ) true 
                        else if (consideredPeople.contains(person)) bfs(target,consideredPeople,discoveredPeople.tail)
                        else bfs(target,consideredPeople + person,discoveredPeople.tail ++ network(person))
                    }
                }

            bfs(b,Set(),network(a)+a)
        }

    println(socialConnection(testNet,"Mary","Jim"))
    println(socialConnection(network,"Mary","Bob"))
}





