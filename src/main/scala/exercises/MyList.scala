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

    // HOFs
    def foreach(unitFunction:A=>Unit):Unit
    def sort(sorting:(A,A)=>Int):MyList[A]
    def zipWith[B,C](list:MyList[B],zipFunction:(A,B)=>C):MyList[C]
    def fold[B](start:B,foldFunction:(B,A)=>B):B
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

    //Hofs 
    def foreach(unitFunction: Nothing => Unit): Unit = ()
    def sort(sorting: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

    def zipWith[B,C](list: MyList[B], zipFunction: (Nothing, B) => C): MyList[C] = 
        if (!list.isEmpty) throw new RuntimeException("Lists do not have the same lenght")
        else Empty



    def fold[B](start: B, foldFunction: (B, Nothing) => B): B = start

    
    
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



    // HOFs
    def foreach(unitFunction: A => Unit): Unit = 
        if (t.isEmpty) unitFunction(h)
        else {
            unitFunction(h)
            t.foreach(unitFunction)
        }
            

    def sort(sorting: (A, A) => Int): MyList[A] =         
        {
            def insert(x:A,sortedList:MyList[A]):MyList[A] = 
                {
                    if (sortedList.isEmpty) new Cons(x,Empty)
                    else if (sorting(x,sortedList.head)<=0) new Cons(x,sortedList)
                    else new Cons(sortedList.head,insert(x,sortedList.tail))
                }
            val sortedTail = t.sort(sorting)
            insert(h,sortedTail)
        }

    
    def zipWith[B,C](list: MyList[B], zipFunction: (A, B) => C): MyList[C] = 
        if (list.isEmpty) throw new RuntimeException("List do not have the same length")
        else new Cons(zipFunction(h,list.head),t.zipWith(list.tail,zipFunction))
        // if ( t.isEmpty && list.tail.isEmpty ) Cons(zipFunction(h,list.head),Empty)
        // else new Cons(zipFunction(h,list.head),t.zipWith(list.tail,zipFunction))


    def fold[B](start: B, foldFunction: (B, A) => B): B = 
        {

            def foldHelper(accumulator:B,element:MyList[A]):B = 
                {
                    if (element.tail.isEmpty) foldFunction(accumulator,element.head)
                    
                    else foldHelper(
                        foldFunction(accumulator,element.head),
                        element.tail
                    )
                }

            foldHelper(start,this)
        }
        
    }


// (1, new Cons(2, new Cons(3,Empty))).fold(2,(x,y)=>x+y)
// (1,)   


object ListTest extends App
{
    val listOfIntegers:MyList[Int] = new Cons(1 , new Cons(2, new Cons(3,Empty)))
    val unorderedListOfIntegers:MyList[Int] = new Cons(3,new Cons(512,new Cons(1, new Cons(-4, new Cons(45,Empty) ))))
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

    println("I am about to start working")
    listOfIntegers.foreach(println)   

    println("I am about to test the sort method")
    unorderedListOfIntegers.sort((x,y)=>y-x).foreach(println)
    
    println("Now my zip Function")
    anotherListOfIntegers
        .zipWith(
            new Cons(10,new Cons(10,Empty)),
            (x:Int,y:Int)=>x*y
        )
        .foreach(println)

    println("Now my foldHelper function")
    println(listOfIntegers.fold(0,(x:Int,y:Int)=>x+y))
    println(unorderedListOfIntegers.fold(0,(x:Int,y:Int)=>x+y))
        
    val combinations = for {
        n <- listOfIntegers
        m <- anotherListOfIntegers
    } yield n + m 

    println("I just got combinations")
    println(combinations)
    
}