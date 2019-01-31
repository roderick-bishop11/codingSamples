using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/* Author: Roderick Bishop
 * Date: 1/30/2019
 * Program: codingMadLibProgram.cs; This is the C# version of the codingMadLibProgram. This program allows the user to make up their own story by inputting parts of speechto make a story. This is a very straightforward
 *                                   way of doing the mad lib and will be revisited with more advanced text formatting, story, etc. 
 * 
 * */
namespace codingMadLibProgram
{
    class Program
    {
        static void Main(string[] args)
        {
            string name1, name2, place, occasion, verb1, verb2, gerund, adjective; 
            Console.WriteLine("This is a basic coding madLib. First, read the story and then insert the parts of speech to fill them in: \n");
            Console.WriteLine("{name 1} and {name 2} went to {place} for their {occasion} vacation. \n{name 1} suggested that they go {verb 1/gerund} but {name 2} thought that was too scary crazy so {name 2} " +
                "decided that they should {verb 2/gerund} instead. \n They ended up {gerund} and had a {adjective} time. \n" );


            Console.WriteLine("Insert the name 1 that you would like to use");
            name1 = Console.ReadLine();
            name1 = char.ToUpper(name1[0]) + name1.Substring(1);
            //capitalizes first char of name so that it can be capitalized in the first letter of the sentence.

            Console.WriteLine("Insert the name 2 that you would like to use");
            name2 = Console.ReadLine();

            Console.WriteLine("Insert the place that you would like to use");
            place = Console.ReadLine();

            Console.WriteLine("Insert the occasion that you would like to use");
            occasion = Console.ReadLine();

            Console.WriteLine("Insert the verb 1/gerund 1 that you would like to use");
            verb1 = Console.ReadLine();

            Console.WriteLine("Insert the verb 2/gerund that you would like to use");
            verb2 = Console.ReadLine();

            Console.WriteLine("Insert the gerund that you would like to use");
            gerund = Console.ReadLine();

            Console.WriteLine("Insert the adjective that you would like to use");
            adjective = Console.ReadLine();

            Console.WriteLine("\n Here is your finished mad lib: \n");
            Console.WriteLine( name1 + " and " + name2 + " went to " + place +" for their " + occasion + " vacation. \n" + name1 + " suggested that they go " + verb1 + " but "  + name2 +  " thought that was too scary so " + name2  +
                " decided that they should " + verb2 + " instead. \n They ended up " + gerund + " and had a " + adjective+  " time. ");
           
            Console.ReadLine();
            }
        }

    }
