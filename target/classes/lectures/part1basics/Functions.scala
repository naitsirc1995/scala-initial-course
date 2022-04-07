package lectures.part1basics

object Functions extends App
{
    def aFunction(a:String,b:Int):String = 
        {
            a + " " + b
            
        }

    println(aFunction("Hello",3))


    def aParameterlessFunction():Int = 42 

    println(aParameterlessFunction)


    def aRepeatedFunction(aString:String,n:Int):String = 
        {
            if (n==1) aString
            else aString + aRepeatedFunction(aString,n-1)
        }

    println(aRepeatedFunction("hello",3))

    // WHEN YOU NEED LOOPS, USE RECURSION



    def aFunctionWithSideEffects(aString:String):Unit = println(aString)


    def aBigFunction(n:Int):Int = 
        {
            def aSmallerFunction(a:Int,b:Int):Int = a+b

            aSmallerFunction(n,n-1)

        }


    /*
    1. A greeting function (name,age) => "Hi, my name is $name and I am $age years old"
    2. A factorial function 1*2*3*..*n
    3. A fibonacci function 
    4. Tests if a number is prime 
    */

    def greetingFunction(name:String,age:Int):String =
        s"Hi, my name is $name and I am $age years old"
    
    println(greetingFunction("Cristian",26))

    def factorialFunction(n:Int):Int = 
        {
            if(n<=0) 1 
            else n*factorialFunction(n-1)
        }
    
    println(s"factorial of 4 is ${factorialFunction(4)}")


    def fibonacciFunction(n:Int):Int = 
        {
            if (n <=1 ) 1 
            else fibonacciFunction(n-1) + fibonacciFunction(n-2)
        }

    println(s"The fibonacci function of 6 would be ${fibonacciFunction(6)}")
    // 1  1 2 3 

    def testPrime(n:Int):Boolean = 
        {
            def isPrimeUntil(t:Int):Boolean = 
                {
                    if (t <= 1) true 
                    else n % t != 0 && isPrimeUntil(t-1)
                }
                
            
            isPrimeUntil(n/2)
        }

    println(testPrime(37))       
    println(testPrime(2003))
    println(testPrime(37*17))
}