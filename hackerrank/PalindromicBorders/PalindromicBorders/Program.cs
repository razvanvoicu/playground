using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

using System.Collections;
using System.IO;
class Solution
{
    static readonly long modulo = 1000000007L;
    static char[] input = null;
    static string inputString = null;
    static Dictionary<string, HashSet<long>>[] cache = new Dictionary<string, HashSet<long>>[] 
    { 
        new Dictionary<string, HashSet<long>>(), 
        new Dictionary<string, HashSet<long>>() 
    };
    static Dictionary<string, HashSet<long>> newCache = new Dictionary<string, HashSet<long>>();
    static long count;

    static void Main(String[] args)
    {
        inputString = Console.ReadLine();
        input = inputString.ToCharArray();

        HashSet<long> s = new HashSet<long>();
        for (int i = 0; i <= input.Length; i++)
        {
            s.Add(i);
        }
        cache[0][""] = s;
        cache[1][""] = s;

        countPalindromes();

        Console.WriteLine(count);
    }

    static void countPalindromes()
    {
        newCache = new Dictionary<string, HashSet<long>>();
        foreach (var p in cache[1].Values)
            foreach (var shorterStart in p)
            {
                long start = shorterStart - 1;
                long end = start;
                if (start >= 0 && end < input.Length && input[start] == input[end])
                {
                    string pal = inputString.Substring(Convert.ToInt32(start), 1);
                    if (!newCache.ContainsKey(pal)) newCache[pal] = new HashSet<long>();
                    newCache[pal].Add(start);
                }
            }
        if (newCache.Count == 1)
        {
            handleSingle();
        }
        else
        {
            updateCount();
            cache[1] = newCache;
            for (int len = 2; len < input.Length; len++)
            {
                newCache = new Dictionary<string, HashSet<long>>();
                foreach (var p in cache[len % 2].Values)
                    foreach (var shorterStart in p)
                    {
                        long start = shorterStart - 1;
                        long end = start + len - 1;
                        if (start >= 0 && end < input.Length && input[start] == input[end])
                        {
                            int startHash = Convert.ToInt32(len < 2000 ? start : start + len / 2 - 999) ;
                            int endHash = len < 2000 ? len / 2 + 1 : 1000;
                            string pal = inputString.Substring(startHash, endHash);
                            if (!newCache.ContainsKey(pal)) newCache[pal] = new HashSet<long>();
                            newCache[pal].Add(start);
                        }
                    }
                updateCount();
                cache[len % 2] = newCache;
            }
        }
    }

    static void updateCount()
    {
        foreach (var p in newCache.Values)
        {
            long c = p.Count;
            count += c * (c - 1) / 2;
            count %= modulo;
        }
    }

    static void handleSingle()
    {
        count = 0;
        for (var i = 1L; i <= inputString.Length; i++)
        {
            count += i * (i - 1) / 2;
            count %= modulo;
        }
        count %= modulo;
    }

}