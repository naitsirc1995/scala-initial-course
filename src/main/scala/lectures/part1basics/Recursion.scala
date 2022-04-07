package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App 
{
    def factorial(n:Int):Int = 
        {
            if (n<=1) 1 
            else 
                {
                    println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
                    val result = n*factorial(n-1)

                    println("Computed factorial of " + n)
                    result
                }
                
        }

    println(factorial(10))
    

    def anotherFactorial(n:Int):BigInt = 
        {

            @tailrec
            def factHelper(x:Int,accumulator:BigInt):BigInt = 
                if (x<=1) accumulator
                else factHelper(x-1,x*accumulator) // TAIL RECURSION = use recursive call as the LAST expression

        factHelper(n,1)

        }

    /*
    anotherFactorial(10) = factHelper(10,1)
    = factHelper(9,10)
    = factHelper(8,9*10)
    = factHelper(7,8*9*10)
    = ...
    = factHelper(2,3*4*...*10)
    = factHelper(1,1*2*3*4*..*10)
    = 1*2*3*4*...*10
    */
    

    // WHEN YOU NEED LOOPS, USE _TAIL_RECURSION.


    /*
    Using tail recursion

        1. Concatenate a string n times 
        2. Is prime function tail recursive. 
        3. Fibonacci function, tail recursive. 
    */

    def stringConcat(n:Int,str:String):String = 
        {
            def stringHelper(counter:Int,inputString:String,accumulator:String):String = 
            {
                if (counter <=0 ) accumulator
                else stringHelper(counter - 1 , inputString,accumulator + inputString)
            }

        stringHelper(n,str,"")
        }


    println(stringConcat(9,"hello"))


    // Now, this prime implementation which will avoid a "for" is nothing else than 
    // the copied version I wrote by memory 

    def checkPrime(n:Int):Boolean = 
        {
            def primeUntil(t:Int):Boolean = 
                {
                    if (t <= 1 ) true 
                    else n%t != 0  && primeUntil(t-1)
                    
                }

            primeUntil(n/2)
        }


    println(checkPrime(14))


    // Now my own version for fibonacci using tail-recursion

    def fibonacciTailRecursion(n:Int):Int = 
        {
            def fibonacciHelper(
                x:Int,
                accumulator:Int,                
                sumCum:Int
            ):Int = 
                {
                    if (x<=0) sumCum
                    else fibonacciHelper(
                        x-1,
                        accumulator + sumCum, 
                        1 + accumulator
                    )
                }

            fibonacciHelper(n,0,0)
        }
        //f(1,0,0) = f(0,0+0,1+0) = 0
        //f(2,0,0) = f(1,0+0,)

    println(
        fibonacciTailRecursion(1),
        fibonacciTailRecursion(2),
        fibonacciTailRecursion(3),
        fibonacciTailRecursion(4),
        fibonacciTailRecursion(5),
        fibonacciTailRecursion(6)
    ) 

}