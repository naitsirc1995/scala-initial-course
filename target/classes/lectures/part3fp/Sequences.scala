package lectures.part3fp

import java.util.Random

object Sequences extends App
{
    // Seq 
    // A (very) general interface for data structures that 
    // - have a well defined order 
    // - can be indexed 

    // Support various operations 
    // apply,iterator,length,reverse for indexing and iterating
    // concatenation, appending, prepending
    // a lot of others: grouping, sorting, zipping, searching, slicing

    val aSequence = Seq(1,3,2,4)
    println(aSequence)
    println(aSequence.reverse)
    println(aSequence(2))
    println(aSequence ++ Seq(5,6,7))
    println(aSequence.sorted)


    // Ranges 
    val aRange:Seq[Int] = 1 to 10 
    aRange.foreach(println)

    (1 to 10).foreach(x=>println("Hello"))

    // List 
    // A LinearSeq immutable linked list 
    // - head, tail, isEmpty methods are fast:O(1)
    // - most operations are O(n):length, reverse

    // Sealed - has two subtypes 

    val aList = List(1,2,3)

    val prepended = 42 +: aList :+ 89
    println(prepended)

    val apples5 = List.fill(5)("apple")
    println(apples5)
    println(aList.mkString("-|-"))


    // Array 
    // The equivalent of simple Java arrays 
    // - can be manually constructed with predefined lengths 
    // - can be mutated (updated in place)
    // - are interoperable with Java's T[] arrays
    // - indexing is fast

    val numbers = Array(1,2,3,4)
    val threeElements = Array.ofDim[Int](3)
    threeElements.foreach(println)

    // mutation 
    numbers(2) = 0 // syntax sugar for numbers.update(2,0)
    println(numbers.mkString(" "))

    // arrays and seq 

    val numberSeq:Seq[Int] = numbers  // implicit conversions
    println(numberSeq)


    // Vector 
    // The default implementation for immutable sequences 
    // - effectively constant indexed read and write: O(log32(n))
    // - fast element addition : append/prepend
    // - implemented as a fixed-branch trie (branch factor 32)
    // - good performance for large sizes 


    val vector:Vector[Int] = Vector(1,2,3)
    println(vector)

    // vectors vs lists 
    val maxRuns = 1000
    val maxCapacity = 1000000
    def getWriteTime(collection:Seq[Int]):Double = 
        {
            val r = new Random
            val times = for {
                it <- 1 to maxRuns
            } yield {
                val currentTime = System.nanoTime()
                collection.updated(r.nextInt(maxCapacity),r.nextInt())
                // operation 
                System.nanoTime() - currentTime
            }            
            times.sum*1.0/maxRuns            
        }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    

    // keeps reference to tails 
    // updating an element in the middle takes a long time
    println(getWriteTime(numbersList))

    // depth of the tree is small
    // need to replace an entire 32-element chunk
    println(getWriteTime(numbersVector))
    

    
}