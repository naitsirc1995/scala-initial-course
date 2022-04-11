package exercises

abstract class MyList[+A] 
{
    /*
        method head = first element of this list 
        method tail = remainder of the lsit
        isEmpty = is this list empty 
        add(int) => new list with this element added 
        toString => a string representation of the list 
    */

    def head:A
    def tail:MyList[A] 
    def isEmpty:Boolean
    def add[B >: A](element:B):MyList[B]    

    def printElements:String 
    override def toString: String = "["+ printElements +  "]"


    def map[B](transformer:A => B):MyList[B]
    def flatMap[B](transfomer:A => MyList[B]):MyList[B]
    def filter(predicate:A => Boolean):MyList[A]

    //concatenation
    def ++[B>:A](list:MyList[B]):MyList[B]

}


case object Empty extends MyList[Nothing]
{
    def head:Nothing = throw new NoSuchElementException
    def tail:MyList[Nothing] = throw new NoSuchElementException
    def isEmpty:Boolean = true 
    def add[B>:Nothing](element:B):MyList[B]  = new Cons(element,Empty)

    def printElements: String = ""

    // Higher order functions 
    def map[B](transformer:Nothing => B):MyList[B] = Empty 
    def flatMap[B](transfomer:Nothing => MyList[B]):MyList[B] = Empty 
    def filter(predicate:Nothing=>Boolean):MyList[Nothing] = Empty 

    def ++[B>:Nothing](list:MyList[B]):MyList[B] = list
}


case class Cons[+A](h:A,t:MyList[A]) extends MyList[A]
{ 
    def head:A = h
    def tail:MyList[A] = t
    def isEmpty:Boolean = false 
    def add[B>:A](element:B):MyList[B] = new Cons(element,this)
    def printElements: String = 
        if (t.isEmpty) "" + h 
        else h + " " + t.printElements


    def filter(predicate: A=>Boolean): MyList[A] = 
        if (predicate(h)) new Cons(h,t.filter(predicate))
        else t.filter(predicate)

    def map[B](transformer: A=>B): MyList[B] = 
        new Cons(transformer(h),t.map(transformer))

    

    def ++[B>:A](list:MyList[B]):MyList[B] = new Cons(h,t ++ list)

    def flatMap[B](transfomer: A => MyList[B]): MyList[B] = 
        transfomer(h) ++ t.flatMap(transfomer)
}





object ListTest extends App
{
    val listOfIntegers:MyList[Int] = new Cons(1 , new Cons(2, new Cons(3,Empty)))
    val cloneListOfIntegers:MyList[Int] = new Cons(1 , new Cons(2, new Cons(3,Empty)))
    val anotherListOfIntegers:MyList[Int] = new Cons(4, new Cons(5,Empty))
    val listOfStrings:MyList[String] = new Cons("Hello",new Cons("Scala",Empty))
    
    println(listOfIntegers.toString)
    println(listOfStrings.toString)
    println(
        listOfIntegers
            .map(_*2)
            .toString
    )

    println(
        listOfIntegers
                .filter( _ %2 == 0)
                .toString
    )

    println(
        (listOfIntegers ++ anotherListOfIntegers).toString
    )


    println(
        listOfIntegers
                    .flatMap(elem => new Cons(elem,new Cons(elem+1,Empty)))
                    .toString
    )

    println(cloneListOfIntegers == listOfIntegers)
    
}