# Sante Coffee Merchants

An Application to aid Sante Coffee Merchants keep track of their farmers' details.

## Set up and Run
###### Application
- Clone the repository: `git clone https://github.com/RutaleIvanPaul/sante-coffee-merchants.git`
- Open the project in Android Studio and Run on an Emulator.

###### Tests
Use the command `./gradlew connectedAndroidTest`.  
Find the reports in `app/build/reports` if your IDE does not automatically open the file.

**NB:**
- It is best to use a device where the UI Animations are disabled for best results.
- Also, make sure your device is running at optimal speed and you have a steady internet connection.

Your tests should then play out like below:
> <img src="https://user-images.githubusercontent.com/30496434/100745104-d6ddea00-33ef-11eb-89dc-1b88c71a1d16.gif" alt="Testing GIF" width="200"/>

## How it Works(Features)
###### Log in
Google sign in is enabled since all the users will have google accounts.

<img src="https://user-images.githubusercontent.com/30496434/100543640-5178ff00-3262-11eb-8e9e-666ab30f8411.png" alt="Log IN" width="200"/>

###### Home - View Farmers' List
The app shows both farmers from the endpoint provided and the local room database.  
Clicking any of the items in the list would open the **Edit Farmer** functionality to allow the user change the details of a particular farmer.

<img src ="https://user-images.githubusercontent.com/30496434/100543902-a10bfa80-3263-11eb-8970-07f42afbb5c5.png" alt="Home" width ="200"/>

###### Add Farmer
Users can add new farmers to the database that will then be reconciled with the remote database when there's an internet connection.

<img src ="https://user-images.githubusercontent.com/30496434/100544021-1f689c80-3264-11eb-9794-be4b9287a688.png" alt="Home" width ="200"/>

**NB:** For Birth Certificates, initially, they will be uploaded to a separate host as images and the URL stored in the application. These images will be loaded in the app with either Glide or Picasso.

###### View Audits
Editing a farmer's details adds a record to the audit that is viewed on this page. Each record shows the farmer's name, the user that last updated their record and the timestamp

<img src ="https://user-images.githubusercontent.com/30496434/100544301-baae4180-3265-11eb-934d-ae0b1de7081c.png" alt="Home" width ="200"/>

######  Navigation
The application provides a Navigation Drawer for users to move from feature to feature.

<img src ="https://user-images.githubusercontent.com/30496434/100544129-b2093b80-3264-11eb-845d-a49ab787dc28.png" alt="Home" width ="200"/>

## Technology
- Google Auth
- Kotlin
  - Room DB
  - Retrofit
  - Dagger HILT
  - Coroutines
