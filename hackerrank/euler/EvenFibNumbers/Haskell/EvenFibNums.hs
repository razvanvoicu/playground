import Control.Monad
import Data.Functor

getLn = read <$> getLine :: IO Integer

fibs :: [Integer]
fibs = 1:2: zipWith (+) fibs (tail fibs)

evenFibs = filter even . flip takeWhile fibs . (>)

main = do
  testCount <- fromIntegral <$> getLn
  testLimits <- replicateM testCount getLn
  forM testLimits $ print . sum . evenFibs
  