using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


/* Author: Roderick Bishop
 * Date:1/28/2019
 * Program: simpleInterest.cs; The C# version of the original simpleInterest.java created in 2017 
 */
namespace ConsoleApp1
{
    class simpleInterest
    {
        static void Main(string[] args)
        {
            double period, simpleInterest, rate, principle;

            Console.WriteLine("Welcome to the C# simple interest Calculator!");

            Console.WriteLine("Enter in the amount that you will save");
            principle = Convert.ToDouble(Console.ReadLine());
            //principle is the amount that you will save

            Console.WriteLine("Enter in the time period that you will be saving in months");
            period = Convert.ToDouble(Console.ReadLine());
            // period is the time that you will save over

            Console.WriteLine("Enter in the rate of interest per month");
            rate = Convert.ToDouble(Console.ReadLine());
            rate = rate / 100;
            // rate of interest per month


            simpleInterest = principle * (1 + (rate * period));
            // simple interest follows the formula A = p(1+(rt))

            Console.WriteLine("After saving " + principle + " for " + period + " months at a " + rate * 100 + "% interest rate per month, your account value would be " + simpleInterest);

            Console.ReadLine();
            // final Console.ReadLine(); to keep window open whent he program is run

        }
    }
}
