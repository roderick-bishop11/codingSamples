#programmer: Roderick E. Bishop
#Date: 10/21/2018
#program: The program takes in 3 numbers from the user and tells which number is the greatest
print("Type in 3 numbers and the program will tell you which one is greatest")
num1 = 0
num2 = 0
num3 = 0
num1 = int(input("Enter number 1 here "))
num2 = int(input("Enter number 2 here "))
num3 = int(input("Enter number 3 here "))

if(num1 == num2 or num1 == num3 or num2 == num3):
    print("You entered numbers that are equal")
if(num1<0 or num2<0 or num3<0):
    print("You entered one or more negative numbers")
else:
    if(num1 > num2 and num1 > num3):
        print(num1," (number 1) is the greatest number ")
    if(num2 > num1 and num2 > num3):
        print(num2," (number 2) is the greatest number ")
    if(num3 > num1 and num3 > num2):
        print(num3," (number 3) is the greatest number ")
