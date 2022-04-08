package lectures.part2oop

object OOBasics extends App 
{
    val person = new Person("Cristian",26)
    println(person.age)
    println(person.x)
    println(person.greet("Daniel"))
    println(person.greet())
}

// constructor 
class Person(name:String, val age:Int) 
{
    // body 
    // 
    val x = 2 

    println(1 + 3)

    def greet(name:String):Unit =  println(s"${this.name} says: Hi, $name")


    // overloading
    def greet():Unit = println(s"Hi, I am $name")


    def this(name:String) = this(name,0)
}


// class parameters are NOT FIELDS 