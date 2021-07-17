# Programmer: Roderick Bishop
# Date: 10/24/2018
# Program: Program inpts a student name and a student average. Program then tells the user the letter equivalent
name = input("Enter student's name: ")
avg = int(input("Enter student's grade average: "))
if(avg>=90):
        print(name.capitalize(),"your average is: A")
elif(avg<90 and avg>=80):
        print(name.capitalize(), "your average is: B")
elif(avg<80 and avg>=70):
        print(name.capitalize(), "your average is: C")
elif(avg<70 and avg>=60):
        print(name.capitalize(), "your average is: D")
elif(avg<60 and avg>=00):
        print(name.capitalize(), "your average is: F")
