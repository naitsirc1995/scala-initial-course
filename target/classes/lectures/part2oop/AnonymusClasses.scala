package lectures.part2oop

object AnonymusClasses extends App 
{
    abstract class Animal 
    {
        def eat:Unit 
    }

    // anonymous class
    val funnyAnimal:Animal = new Animal 
    {
        override def eat: Unit = println("hahahahahahahaha")
    }


    println(funnyAnimal.getClass)


    class Person(name:String)
    {
        def sayHi:Unit = println(s"Hi, my name is $name, how can I help?")
    }


    val jim = new Person("Jim") 
    {
        override def sayHi: Unit = 
            println(s"Hi, my name is jim, how can I be of service?")
    }
}