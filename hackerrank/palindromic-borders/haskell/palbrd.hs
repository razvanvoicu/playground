{-# OPTIONS_GHC -O2 #-}
{-# LANGUAGE RankNTypes, KindSignatures, FlexibleContexts #-}

import Control.Monad
import Data.Functor
import Data.Array.MArray
import Data.Array.IO
import Data.Int
import Data.Char
import Data.IORef

type CountType = Int

data Node = Nil 
          | Node { 
              nodeNexts :: IOArray Int Node, 
              nodeSuffixRef :: IORef Node, 
              nodeCountRef :: IORef Int, 
              nodeLength :: Int 
            } deriving Eq

moduloConst :: CountType
moduloConst = 1000000007

makeNodeFromLength :: Int -> IO Node
makeNodeFromLength length = makeNodeFromLengthAndSuffix length Nil

makeNodeFromLengthAndSuffix :: Int -> Node -> IO Node
makeNodeFromLengthAndSuffix length suffix = do
  nexts <- newListArray (0,7) $ replicate 8 Nil :: IO (IOArray Int Node)
  suffixRef <- newIORef suffix
  countRef <- newIORef 0
  let node = Node { 
    nodeNexts = nexts, 
    nodeSuffixRef = suffixRef, 
    nodeCountRef = countRef, 
    nodeLength = length 
  }
  when (suffix == Nil) $ writeIORef (nodeSuffixRef node) node
  return node

getNodeRefLength :: IORef Node -> IO Int
getNodeRefLength = fmap nodeLength . readIORef

getNodeCount :: Node -> IO CountType
getNodeCount = readIORef . nodeCountRef 

updateNodeCount :: Node -> (CountType -> CountType) -> IO ()
updateNodeCount node f = flip modifyIORef' f $ nodeCountRef node

getNodeRefSuffix :: IORef Node -> IO Node
getNodeRefSuffix nodeRef = do
  suffixRef <- nodeSuffixRef <$> readIORef nodeRef
  readIORef suffixRef

getNodeSuffix :: Node -> IO Node
getNodeSuffix = readIORef . nodeSuffixRef

getNodeRefNext :: IORef Node -> Int -> IO Node
getNodeRefNext nodeRef index = do
  array <- nodeNexts <$> readIORef nodeRef
  readArray array index

setNodeRefNext :: IORef Node -> Int -> Node -> IO ()
setNodeRefNext nodeRef index newNode = do
  array <- nodeNexts <$> readIORef nodeRef
  writeArray array index newNode

makeCharArray :: String -> IO (IOArray Int Int8)
makeCharArray input = newListArray bounds $ 8 : map charToInt8 input :: IO (IOArray Int Int8)
  where bounds = (0,length input)
        charToInt8 = fromIntegral . (flip (-) $ ord 'a') . ord

getCurrentElementInCharArray :: IOArray Int Int8 -> Int -> IO Int
getCurrentElementInCharArray charArray idx = fromIntegral <$> readCharArray charArray idx

readCharArray :: IOArray Int Int8 -> Int -> IO Int8
readCharArray charArray index = readArray charArray $ index + 1

makeNodeArray :: String -> IO (IOArray Int Node)
makeNodeArray input = newArray (0,length input) Nil



notPal :: Int -> IOArray Int Int8 -> IORef Node -> IO Bool
notPal index charArray nodeRef = do
  length <- getNodeRefLength nodeRef
  left <- readCharArray charArray $ index - length - 1
  right <- readCharArray charArray index
  return $ left /= right
  
moveOn :: IORef Node -> IO ()
moveOn nodeRef = do
  suf <- getNodeRefSuffix nodeRef
  writeIORef nodeRef suf

traversePrefixesAndFindPalindrome :: Int -> IOArray Int Int8 -> IORef Node -> IO ()
traversePrefixesAndFindPalindrome i cs node = whileM_ (notPal i cs node) $ moveOn node


makeRoot :: IO (Node, IORef Node)
makeRoot = do
  rNeg <- makeNodeFromLength (-1)
  root <- makeNodeFromLengthAndSuffix 0 rNeg
  curr <- newIORef root
  return (root,curr)


computeNextSuffix
  :: Node 
  -> IORef Node 
  -> IOArray Int Int8 
  -> Int 
  -> Int 
  -> IO (IORef Node)
computeNextSuffix root currentNode charArray currentElementInCharArray idx = do
  isCurrentNodeADummyNode <- (== -1) <$> getNodeRefLength currentNode
  if (isCurrentNodeADummyNode)
  then
    newIORef root
  else do
    suffixRef <- newIORef Nil
    writeIORef suffixRef =<< getNodeRefSuffix currentNode
    traversePrefixesAndFindPalindrome idx charArray suffixRef
    writeIORef suffixRef =<< getNodeRefNext suffixRef currentElementInCharArray
    return suffixRef


countPalindromes
  :: forall (a :: * -> * -> *) 
  .  MArray a Node IO 
  => IORef Int 
  -> a Int Node 
  -> IO CountType
countPalindromes nodeCounterRef nodes = do
  palindromeCountRef <- newIORef 0 :: IO (IORef CountType)
  nodeCounter <- readIORef nodeCounterRef :: IO Int

  forM_ [nodeCounter-1, nodeCounter-2 .. 0] $ \nodeCounter -> do
    n <- readArray nodes nodeCounter :: IO Node
    c <- getNodeCount n :: IO CountType
    suf <- getNodeSuffix n :: IO Node
    updateNodeCount suf (+c)
    modifyIORef' palindromeCountRef (+ (c * (c-1) `div` 2))
    modifyIORef' palindromeCountRef $ flip mod moduloConst
  readIORef palindromeCountRef


buildPalindromeSuffixes 
  :: forall a (a1 :: * -> * -> *) s.
     (MArray a1 Node IO, Ix s, Num s) 
  => [a]
  -> IOArray Int Int8
  -> a1 s Node
  -> Node
  -> IORef Node
  -> IORef s
  -> IO ()
buildPalindromeSuffixes input charArray nodes root currentNode nodeCounterRef = 
  forM_ [0..length input-1] $ \i -> do
    traversePrefixesAndFindPalindrome i charArray currentNode
    currentElementInCharArray <- getCurrentElementInCharArray charArray i
    currentNextIsNil <- (== Nil) <$> getNodeRefNext currentNode currentElementInCharArray
    when (currentNextIsNil) $ do
      nextSuffix <- computeNextSuffix root currentNode charArray currentElementInCharArray i
      l <- (+2) <$> getNodeRefLength currentNode
      n <- makeNodeFromLengthAndSuffix l =<< readIORef nextSuffix
      setNodeRefNext currentNode currentElementInCharArray n
      nodeCounter <- readIORef nodeCounterRef
      writeArray nodes nodeCounter n
      writeIORef nodeCounterRef $ nodeCounter + 1
    newNode <- getNodeRefNext currentNode currentElementInCharArray
    updateNodeCount newNode succ
    writeIORef currentNode newNode

main :: IO ()
main = do
  input <- getLine
  charArray <- makeCharArray input
  nodes <- makeNodeArray input
  (root,currentNode) <- makeRoot
  nodeCounterRef <- newIORef 0

  buildPalindromeSuffixes input charArray nodes root currentNode nodeCounterRef

  print =<< countPalindromes nodeCounterRef nodes


whileM_
  :: (Monad m) 
  => m Bool 
  -> m a 
  -> m ()
whileM_ p f = go
    where go = do
            x <- p
            if x
                then f >> go
                else return ()
