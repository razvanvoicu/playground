-- Enter your code here. Read input from STDIN. Print output to STDOUT
import Control.Monad
import Data.Functor

sumMult :: Integer -> Integer -> Integer
sumMult k limit = (k * limK * (limK+1)) `div` 2
  where limK = (limit-1) `div` k

main = do
  tests <- read <$> getLine :: IO Integer
  forM_ [1..tests] $ \_ -> do
    limit <- read <$> getLine :: IO Integer
    putStrLn $ show $ sumMult 3 limit + sumMult 5 limit - sumMult 15 limit
