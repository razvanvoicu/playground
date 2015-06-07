import Control.Monad
import Data.Functor

fib :: [Integer]
fib = 1:2: zipWith (+) fib (tail fib)

main = do
  count <- read <$> getLine :: IO Integer
  forM_ [1..count] $ \_ -> do
    limit <- read <$> getLine :: IO Integer
    putStrLn $ show $ sum $ filter even $ takeWhile (< limit) fib
  