# Programmer: Roderick E. Bishop
# Program: simpleInterest.py; the python version of the original simpleInterest.java created in 2017. This is a much
# simpler version.
# Created: 1/28/2019


print("Welcome to the simple interest calculator!")

principle = float(input("Enter in the amount that you will be saving "))
# principle is the amount of money that will be saved

period = int(input("Now enter the period that you will be saving for in months "))
# period is the amount of time the interest will be accrued over

rate = float(input("Enter in the rate of interest per month "))
rate = rate/100
# rate is the percent of interest. It is divided by 100 to go from percent to decimal

accountValue = principle*(1+(rate*period))
# the total value of the account after saving

print("After", period, "months of", '${:,.2f}'.format(principle), "accruing", rate*100, "percent interest, your current account value is",
      '${:,.2f}'.format(accountValue))
# final print statement that prints out the total account value after saving
