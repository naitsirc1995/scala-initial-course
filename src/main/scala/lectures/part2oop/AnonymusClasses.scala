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

    println("This is a hello from a class named after a hacker group")

    /*
    1. Generic trait MyPredicate[-T] with a method test(T) => Boolean
    (it has a method to test wether a value of type T passes a condition)
    2. Generic trait MyTransformer[-A,B] with a method transform(A) => B
    (it has a method to convert a value of type A to a value of type B)
    [Every subclass of myTransformer will have a different implementation 
        of my transforme]
    3. MyList:
        -map(transformer) => MyList
        -filter(MyPredicate) => MyList
        -flatMap(transformer from A to MyList[B] => MyList[B])


        class EvenPredicate extends MyPredicate[Int] 
        class StringToIntTransformer extends MyTransformer[String,Int]

        [1,2,3].map(n*2) = [2,4,6]
        [1,2,3,4].filters(n%2) = [2,4]
        [1,2,3].flatmap(n => [n,n+1]) => [1,2,2,3,3,4]
    */


    


}