using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MusicBandSupporter2
{

    public class MusicBandSupporter
    {
        private string name = "";
        private int numBoxesSold;

        public MusicBandSupporter(string name)
        {
            this.name = name;
            numBoxesSold = 0;
        }

        public string getName()
        {
            return this.name;
        }

        public void updateSales(int num)
        {
            this.numBoxesSold += num;
        }
        public override string ToString()
        {
            return String.Format("{0}:\t {1} boxes of bonbons sold.", name, numBoxesSold);
        }
    }
}
