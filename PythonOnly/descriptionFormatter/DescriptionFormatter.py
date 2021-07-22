import datetime


def createString(s, t, d, m, ser):
    res = ""
    s[0] = " ".join(s).title()
    t[0] = " ".join(t).title()
    m[0] = " ".join(m).title()
    ser[0] = " ".join(ser).title()
    res = f"{t[0]} {s[0]} delivers the message titled \"{m[0]}\" in the amazing \"{ser[0]}\" series. This sunday service was recorded on {d}. Join us each Sunday at 10:00 AM and worship with us!"
    return res


# speakerName= input("Enter the name of the speaker's full name ex. Aaron: ").split()
# speakerTitle = input("Enter the name of the Speaker's tite ex. Rev, pastor: ").split()
# date = datetime.datetime.now().strftime("%x")
# messageTitle = input("Enter the name of the message title: ").split()
# seriesTitle = input("Enter the name of the series title:Aaron B ").split()

# print(createString(speakerName, speakerTitle, date, messageTitle, seriesTitle))
