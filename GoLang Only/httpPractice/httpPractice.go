package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"time"
)

func main() {

	//use time.Now().Date() to get the month and day for today.
	_, month, today := time.Now().Date()

	//we have to create a url to hit the endpoint, so we'll use fmt.Sprintf() to concat the strings
	response, err := http.Get(fmt.Sprintf("http://numbersapi.com/%d/%d/date", today, month))

	//always nullcheck the http requests
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(0)
	}

	//always nullcheck the response
	resp, err := ioutil.ReadAll(response.Body)
	if err != nil {
		log.Fatal(err) //log it
	}

	fmt.Println(string(resp)) //output the code.
}
