# SuperHero for Adesso Mobile Solutions Germany
As I have an upcoming interview in approx. 5 days, I decided to familiarize myself with the Marvel API that might be a topic during the interview. 
In this regard I will recreate a SuperHero App  that shows a list of Marvel Heroes in a Grid. After clicking on one grid element the detail page will open and a description of the hero will show. I only have three screenshots of the app, so the functionalities are decided by myself

This app is using Retrofit and Moshi to handle the API.

I do not own any Marvel content shown within the app. 
Data provided by Marvel. © 2022 MARVEL

## Update 11th October 2022

I've implemented the main functionality of the App. The app is requesting all the heroes from the marvel server on first start. Afterwards all heroes are saved inside a room database. Only heroes with a description and an image are used. the others are filtered out before inserting into the database.

There are still some things I was not able to finish due to time: Get most dominant color of the hero-image to set the background color of the detail page. 
The view to show the comics is not styled. other than that: app styling, documentation and testing is missing as of now.

### Known Issues

The app won't work on first start if there is no internet connection.
Landscape mode is not tested at all. It should work with visibility impediments.

## Test the app on appetize.com (link will expire on 11th october 2022)
https://appetize.io/app/b26iqoofcxdk6bvygnevllq4ei
