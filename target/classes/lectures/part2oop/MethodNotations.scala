package lectures.part2oop

object MethodNotations extends App 
{
    class Person(val name:String,favoriteMovie:String)
    {
        def likes(movie:String):Boolean = movie == favoriteMovie            


        def hangOutWith(person:Person):String = 
            s"${this.name} is hanging out with ${person.name}"

        def +(person:Person) =
            s"${this.name} is hanging out with ${person.name}"

        def unary_! : String = s"$name, what the heck?!"


        def isAlive:Boolean = true 

        def apply():String = s"Hi, my name is $name and I like $favoriteMovie"
    }


    val mary = new Person("Mary","Inception")
    println(mary.likes("Inception"))
    println(mary likes "Inception") // equivalent 
    // infix notation = operator notation  (syntactic sugar)


    // "operators" in Scala 
    val tom:Person = new Person("Tom","Fight Club")

    println(mary hangOutWith tom)

    println(mary + tom)
    println(mary.+(tom))

    println(1 + 2)
    println(1.+(2))

    // ALL OPERATORS ARE METHODS.
    // Akka actors have ! ? 

    // prefix notation
    val x = -1 // equivalente with 1.unary_-
    val y = 1.unary_-
    // unary_prefix only wors with - + ~ ! 


    println(!mary)

    println(mary.unary_!)


    // postfix notation Only for methods without parameters 

    println(mary.isAlive)
    println(mary isAlive)

    // apply 
    println(mary.apply())
    println(mary()) // equivalent 


    val cristian:Person = new Person("Cristian","Good Bye Lennin")

    println(cristian())
    

    /*
    1. Overload the + operator 
        mary + "the rockstar" =>  new person "Mary (the rockstart)"


    2. Add an age to the Person class => 
        Add a unary + operator => new person with the age + 1 
        +mary => mary with the age incremented 


    3. Add a "leanrs" method in the Person class => "Mary learns Scala"
       Add a learnsScala method, calls learns method with "Scala"
       Use it in postfix notation

    4. Overload the apply method
        oveload the apply method mary.apply(2) => "Mary watched Inception 2 times"
    */

    
}