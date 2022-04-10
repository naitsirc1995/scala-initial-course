package lectures.part2oop

object CaseClases extends App 
{
    /*
    equals,hashCode,toString
    */

    case class Person(name:String,age:Int)

    // 1. class param,eters are fields 
    val jim = new Person("Jim",34)
    println(jim.name)

    // 2. sensible toString
    println(jim.toString())
}