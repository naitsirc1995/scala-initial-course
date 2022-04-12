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
    
}