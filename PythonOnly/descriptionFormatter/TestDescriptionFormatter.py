import unittest
import DescriptionFormatter
import datetime


class KnownValues(unittest.TestCase):
    def test_formatter(self):
        name = ["Aaron", "Bishop"]
        title = ["pastor"]
        message = ["Stay", "In", "your", "lane"]
        series = ["stay", "sucker", "free"]
        date = datetime.datetime.now().strftime("%x")

        expected = "Pastor Aaron Bishop delivers the message titled \"Stay In Your Lane\" in the amazing \"Stay Sucker Free\" series. This sunday service was recorded on 07/21/21. Join us each Sunday at 10:00 AM and worship with us!"
        result = DescriptionFormatter.createString(name, title, date, message, series)
        print(result)
        self.assertEqual(expected, result)

if __name__  == '__main__':
    unittest.main()