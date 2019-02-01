# Author: Roderick Bishop
# Date: 2/1/2019
# Program: codingMadLibProgram.py; the python version of the coding mad lib program that takes a users input and makes
# a story out of it

print("Welcome to the python coding madLib!\n")
print("First, read the story, then insert your own words to make the story as cool as you like! \n")
print("This morning I got up and decided to go to the {place} to {verb1} and get my {noun} {verb2}.")
print("Then I went to {verb3} with my {person} because I have't {verb3} with {pronoun} in a little while.")
print("After a long day of {adjective} fun, I decided to go home and {verb4} until I fell asleep. \n")

place = input("Input place ")
verb1 = input("Input verb 1 ")
noun = input("Input noun ")
verb2 = input("Input verb 2 ")
verb3 = input("Input verb 3 ")
person = input("Input person ")
pronoun = input("Input pronoun ")
adjective = input("Input adjective ")
verb4 = input("Input verb 4 ")

print("Here is your finished madLib! \n \n")
print("This morning I got up and decided to go to the", place, "to", verb1, "and get my", noun, verb2, "\b. ")
print("Then I went to", verb3, "with my", person,  "because I have't", verb3, "\bed with", pronoun, "in a little while.")
print("After a long day of", adjective, "fun, I decided to go home and", verb4,  "until I fell asleep.")

