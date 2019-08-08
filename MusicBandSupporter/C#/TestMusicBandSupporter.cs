using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MusicBandSupporter2;

namespace TestMusicBandSupporter3
{
    class TestMusicBandSupporter
    {
        static void Main(string[] args)
        {

            Console.WriteLine("Enter in the name of the first music band supporter");
            String first = Console.ReadLine();
            MusicBandSupporter student1 = new MusicBandSupporter(first);
            Console.WriteLine("Enter in the name of the second music band supporter");
            String second = Console.ReadLine();
            MusicBandSupporter student2 = new MusicBandSupporter(second);

            for (int i = 0; i < 3; i++)
            {
                int boxes;
                Console.WriteLine("Enter in the number of boxes sold by " + student1.getName() + " during week # " + (i + 1));
                boxes = Convert.ToInt32(Console.ReadLine());
                student1.updateSales(boxes);
                Console.WriteLine("Enter in the number of boxes sold by " + student2.getName() + " during week # " + (i + 1));
                boxes = Convert.ToInt32(Console.ReadLine());
                student2.updateSales(boxes);
            }
            Console.WriteLine("Results of supporting the Music Band are: ");
            Console.WriteLine(student1.ToString());
            Console.WriteLine(student2.ToString());
            Console.ReadLine();

        }
    }
}
