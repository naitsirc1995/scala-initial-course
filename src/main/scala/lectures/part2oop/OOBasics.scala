package lectures.part2oop

object OOBasics extends App 
{
    val person = new Person("Cristian",26)
    println(person.age)
    println(person.x)
    println(person.greet("Daniel"))
    println(person.greet())

    val someWriter = new Writer("Paul","Dickinson",1982)

    println(someWriter.fullName)
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

/*

Novel and a Writer class 

Writer:first name, surname, year 
 - method fullname 


Novel : name, year of release, author 

  - authorAge 
  - isWrittenBy(author)
  - copy  (new uear of release) = new instance of Novel



Counter class 
 - recieves an int value 
 - method current count 
 - method to increment/decrement => new counter 
 - overload inc/dec to receive an amount 

*/



// class parameters are NOT FIELDS 


class Writer(firstName:String, surname:String,year:Int)
{
    def fullName = firstName + " " + surname
}


class Novel(authorAge:Int,isWrittenBy:String)
{
    
}