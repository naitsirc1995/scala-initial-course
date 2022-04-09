package lectures.part2oop

object MethodNotationsExercises extends App
{
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

    class Person(val name:String,favoriteMovie:String, val age:Int)
    {
        def likes(movie:String):Boolean = movie == favoriteMovie            


        def hangOutWith(person:Person):String = 
            s"${this.name} is hanging out with ${person.name}"

        def +(person:Person) =
            s"${this.name} is hanging out with ${person.name}"

        def +(nickName:String):Person = 
            new Person(s"${this.name} (the $nickName)",favoriteMovie,age)

        def unary_! : String = s"$name, what the heck?!"

        def unary_+ : Person = new Person(name,favoriteMovie,(age+1))
            


        def isAlive:Boolean = true 

        def learns(thing:String) = s"$name is learning $thing"

        def learnsScala = this learns "Scala"

        def apply():String = s"Hi, my name is $name and I like $favoriteMovie"


        def apply(n:Int):String = 
            {
                if (n <= 0) this()
                else s"$name watched $favoriteMovie $n times"
            }
    }

    /*
    1. Overload the + operator 
        mary + "the rockstar" =>  new person "Mary (the rockstart)"
    */

    val cristian = new Person("Cristian","Good Bye Lennin",26)

    println((cristian + "Chavitos").name)


    /*
    2. Add an age to the Person class => 
        Add a unary + operator => new person with the age + 1 
        +mary => mary with the age incremented 
    */

    println(cristian.unary_+.age)
    println((+cristian).age)


    /*
    3. Add a "leanrs" method in the Person class => "Mary learns Scala"
       Add a learnsScala method, calls learns method with "Scala"
       Use it in postfix notation
    */
    
    println(cristian learnsScala)


    /*
    4. Overload the apply method
        oveload the apply method mary.apply(2) => "Mary watched Inception 2 times"
    */

    println(cristian(-2))
    println(cristian(50))

}