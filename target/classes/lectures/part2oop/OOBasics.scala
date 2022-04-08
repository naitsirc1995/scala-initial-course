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


    val hundredLonliness:Novel = 
        new Novel(80,1980,"Gabriel")

    println(hundredLonliness.isWrittenBy("Gabrielito"))

    val newVersion:Novel = hundredLonliness.copy(2007)

    println(newVersion.isWrittenBy("Gabriel"))


    val myCounter:counter = new counter(10)

    println(myCounter.getCurrentCount)

    println(myCounter.increment.increment(10).getCurrentCount)


    val instructorCounterInstance:InstructorCounter = 
        new InstructorCounter

    
    instructorCounterInstance.inc.print


    


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


class Novel(authorAge:Int,yearOfRelease:Int,author:String)
{
    def isWrittenBy(author:String):Boolean = this.author == author

    def copy(yearOfRelease:Int):Novel = new Novel(this.authorAge,yearOfRelease,this.author)
}



/*
Counter class 
 - recieves an int value 
 - method current count 
 - method to increment/decrement => new counter 
 - overload inc/dec to receive an amount 
*/




class counter(n:Int)
{
    

    def getCurrentCount:Int = n

    def increment:counter = new counter(n+1)  // immutability

    def increment(m:Int):counter = new counter(n+m)
        
    def decrement = new counter(n-1)

    def decrement(m:Int):counter = new counter(n-m)
        

}



// Here is the counter class implemented by the instructor. 

// I find it really interesting, that is why I am using it here. 



class InstructorCounter(val count:Int = 0)
{
    def inc = 
        {
            println("incrementing")
            new InstructorCounter(count+1)
        }

    def dec = 
        {
            println("decrementing")
            new InstructorCounter(count - 1)
        }

    def inc(n:Int):InstructorCounter = 
        {
            if (n<=0) this 
            else inc.inc(n-1)
        }

    def dec(n:Int):InstructorCounter = 
        {
            if (n<=0) this 
            else dec.dec(n-1)
        }


    def print = println(count)
}





