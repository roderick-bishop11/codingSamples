
/* 
 * File:   main.c
 * Author: roderickbishop
 *
 * Created on September 11, 2019, 11:27 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
double min; // find a way to make these global variables, or make them functions
double max;

/*
 * Takes in a list of 4 numbers and finds min, max, and standard dev of the number set. 
 */
int main(int argc, char** argv) {
  double mean, sum, currentNum, num1, num2, num3, num4;
  checkMinMax(double);
  calcVarAndStdDev(double, double, double, double, double);
    sum = 0;
    printf("Welcome to the number calculator\n");
    printf("Enter in a number: \t");
    scanf("%lf", &currentNum);
    num1 = currentNum;
    min = num1;
    max = num1;
    printf("Enter in a number: \t");
    scanf("%lf", &currentNum);
    num2 = currentNum;
    checkMinMax(currentNum);
    printf("Enter in a number: \t");
    scanf("%lf", &currentNum);
    num3 = currentNum;
    checkMinMax(currentNum);
    printf("Enter in a number: \t");
    scanf("%lf", &currentNum);
    num4 = currentNum;
    checkMinMax(currentNum);
    
    sum= num1 + num2 + num3 + num4;
    mean = sum/4;
    printf("Min: %.3f\nMax: %.3f\nSum: %.3f\nMean: %.3f\n ", min, max, sum, mean);
    
    return (EXIT_SUCCESS);
}

void checkMinMax(double num){
    
    if(num > max)  max = num;
    if(num < min) min = num;
}

void calcVarAndStdDev(double mean, double num1, double num2, double num3, double num4){
    //variation is the squared difference between each number and the mean
    double variation;
    double stdDev;
    //variation is 
    variation += pow(num1 - mean,2);
    variation += pow(num2 - mean,2);
    variation += pow(num3 - mean,2);
    variation += pow(num4 - mean,2);
    stdDev = pow(variation, 0.5);
    printf("Variation:\t%.3f\nStandard Deviation:\t%.3f\n", variation, stdDev);
}
