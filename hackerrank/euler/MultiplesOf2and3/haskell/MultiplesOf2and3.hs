import Control.Monad

sumMult :: Integer -> Integer -> Integer
sumMult k limit = (k * limK * (limK+1)) `div` 2
  where limK = (limit-1) `div` k

sumMult35 limit = sumMult 3 limit + sumMult 5 limit - sumMult 15 limit
  
main = do
  testCount <- readLn :: IO Int
  inputs <- replicateM testCount readLn :: IO [Integer]
  forM inputs $ print . sumMult35 
