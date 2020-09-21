/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: roderickbishop
 *
 * Created on September 17, 2019, 8:17 AM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */

int main(int argc, char** argv) {
    void fibonacci_iterative();
    int fibonacci_recursive(int);
    int numTerms;
    
    printf("Enter in the number of fibonacci terms you want to see\t");
    scanf("%d", &numTerms);
    printf("Here is the first 7 fibonacci numbers interatively\n");
    fibonacci_iterative();
    printf("\nHere is the first %d fibonacci numbers recursively\n", numTerms);
    for(int i = 0; i<= numTerms; i++){
    printf(fibonacci_recursive(numTerms));
    }
    return (EXIT_SUCCESS);
}

void fibonacci_iterative(){
    int prev1, prev2, num;
    for(int i = 0; i <= 7; i++){
       
        if(i == 0){
            prev1 = i;
            prev2 = i;
        }
        if(i == 1){
            prev2++;
        }
        num = prev1+prev2;
        printf("%d, ", num);
        prev2 = prev1;
        prev1 = num;
    }
}

int fibonacci_recursive(int num){
    if(num == 1 || num == 0){
        return num;
    }
    else{
        return fibonacci_recursive(num-1) + fibonacci_recursive(num-2);
    }
}

