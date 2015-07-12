{-
    http://rosalind.info/problems/fibd/
    Problem

    Fibonacci's rabbits if they die after three months.
    Recall the definition of the Fibonacci numbers from “Rabbits and Recurrence Relations”, 
	which followed the recurrence relation Fn=Fn−1+Fn−2 and assumed that each pair of rabbits 
	reaches maturity in one month and produces a single pair of offspring (one male, one female) 
	each subsequent month.

    Given: Positive integers n≤100 and m≤20.

    Return: The total number of pairs of rabbits that will remain after the n-th month 
	        if all rabbits live for m months.

    Sample Dataset

        6 3

	Sample Output

        4
-}

import System.Environment
import Data.Functor
import Data.Char
import Control.Monad

main :: IO ()
main = do
    [n,k] <- map read <$> getArgs :: IO [Integer]
    putStrLn $ show $ fib n k

fibstr :: Integral a => a -> [a]
fibstr m = fibo stream
    where stream = trans stream
          trans  = join $ (.) prefix . flip (.) delay . zipWith (-) . fibo
          prefix = (1 :) . (0 :)
          fibo   = join $ zipWith (+) . tail
          zeroes = repeat 0
          intm   = fromIntegral $ pred m
          delay  = (++) $ take intm zeroes

fib :: Integral a => a -> a -> a
fib n m = fibstr m !! (fromIntegral n - 1)

