using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

namespace test
{
    class Program
    {
        static void Main(string[] args)
        {
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Reset();
            stopwatch.Start();
            Console.WriteLine(squareEachDigit(Convert.ToInt32(16)));
            Console.WriteLine(squareEachDigit(Convert.ToInt32(9119)));
            Console.WriteLine(squareEachDigit(Convert.ToInt32(81)));
            Console.WriteLine(squareEachDigit(Convert.ToInt32(4444)));
            Console.WriteLine(squareEachDigit(Convert.ToInt32(Int32.MaxValue)));
            stopwatch.Stop();
            long ts = stopwatch.ElapsedMilliseconds;
            Console.WriteLine("Time: {0} Milliseconds", ts);
            Console.WriteLine(Int32.MaxValue);
            Console.ReadLine();
        }

        public static Int64 squareEachDigit(Int32 nums)
        {
            Int64 newNum = 0000;
            string result = "";
            Stack<Int32> numStack = new Stack<Int32>();
            while (nums != 0)
            {
                int lastDigit = nums % 10;
                numStack.Push(lastDigit);
                nums /= 10;
            }
            Int32[] arr = numStack.ToArray<Int32>();
            for (int i = 0; i < arr.Length; i++)
            {
                arr[i] *= arr[i];
                result += arr[i].ToString();
            }
            Int64 number;
            bool success = Int64.TryParse(result, out number);
            if (success)
            {
                newNum = number;
            }
            return newNum;
        }
    }

}

