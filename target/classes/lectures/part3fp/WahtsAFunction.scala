package lectures.part3fp

object WahtsAFunction extends App 
{
    // DREAM use funcitons as first class elements
    // problem: oop
    val doubler = new MyFunction[Int,Int] {
        override def apply(element: Int): Int = element*2
    }

    println(doubler(2))

    // function types = Function1[A,B] 

    val stringToIntConverter = new Function1[String,Int]{
        override def apply(string: String): Int = string.toInt
    }

    println(stringToIntConverter("3")+4)

    val adder:((Int,Int)=>Int) = new Function2[Int,Int,Int]{
        override def apply(a: Int, b: Int): Int = a+b
    }

    // Function types Function2(A,B,R) === (A,B) => R 

    // ALL SCALA FUNCTIONS ARE OBJECTS 


    /*
    1. a function which takes 2 strings and concatenates them 
    2. transform the MyPredicate and MyTransformer into function types 
    3. define a function which takes an int and returns another function which takes and int and returns and int 
        - what's the type of this function 
        - how to do it 
        - 
    */


    object mySolution 
    {
        val stringConcat:((String,String)=>String) = new Function2[String,String,String]{
            override def apply(a: String, b: String): String = a+b
        }

        def functionReturner(a:Int):((Int)=>Int) = 
            {
                def returnedFunction(b:Int):Int = 
                    {
                        a + b 
                    }

                return returnedFunction
            }            
    }


    println(mySolution.functionReturner(3)(2)) 


    // Instructor's solution 

    object instructoSolution
    {
        def concatenator:(String,String)=>String = new Function2[String,String,String]{
            override def apply(a: String, b: String): String = a+b
        }

        // Function1[Int,Function1[Int,Int]]

        val superAdded:Function1[Int,Function1[Int,Int]] = 
            new Function1[Int,Function1[Int,Int]]
            {
                override def apply(x: Int): Int => Int = 
                    new Function1[Int,Int] 
                    {
                        override def apply(y: Int): Int = x+y
                    }
            }
    }

    println(instructoSolution.concatenator("Helo ","Scala"))


    val adder3 = instructoSolution.superAdded(3)

    println(adder3(4))
    println(instructoSolution.superAdded(3)(4)) // curried function 
    
}


trait MyFunction[A,B]
{
    def apply(element:A):B
}