package lectures.part2oop

object AbstractDataTypes extends App
{
    // abstract 
    abstract class Animal 
    {
        val creatureType:String = "wild"
        def eat:Unit 
    }


    class Dog extends Animal 
    {
        override val creatureType: String = "Canine"

        override def eat: Unit = println("Crunch crunch")
    }

    // traits 

    trait Carnivore 
    {
        def eat(animal:Animal):Unit 

        val preferredMeal:String = "fresh meat"
    }



    trait ColdBlooded 

    class Crocodile extends Animal with Carnivore with ColdBlooded
    {
        override val creatureType:String = "Croc"

        def eat:Unit = 
            println("nomnomnom")
        def eat(animal:Animal):Unit = 
            println(s"I'm a croc and I am eating ${animal.creatureType}")
        
    }


    val dog = new Dog 
    val croc = new Crocodile

    croc.eat(dog)
    croc.eat


    // traits vs abstract classes 
    // 1 - traits do not have constructor parameters 
    // 2 - multiple traits may be inherited by the same class 
    // 3 - trait = behavior, abstract class = "thing"
    
}