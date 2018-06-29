# Journal  App
This app is created in other to complete the Andela Learning Community (ALC) #7DaysofCodeChallenge.
The challenge aims to make an application where users can pen down their thoughts and feelings. [Download the apk file](https://link.to.apk/)

## What you need
  The Journal  App uses the following dependencies:
  * **Firebase database** 'com.google.firebase:firebase-database:16.0.1'
  * **Firebase Authentification** 'com.google.firebase:firebase-auth:16.0.1'
  * **Google authentification**  'com.google.android.gms:play-services-auth:15.0.1'
     
  * **ViewModel and LiveData** "android.arch.lifecycle:extensions:$lifecycle_version"
  * **ViewModel and LiveData** for JAVA 8  "android.arch.lifecycle:common-java8:$lifecycle_version"
  * **Room Database** "android.arch.persistence.room:runtime:$room_version"
  * **Room Anototion** "android.arch.persistence.room:compiler:$room_version"
  
  ## Designing the app
  The App consists of six UI screens
  * Launcher- Displayed only when the app is first time open
  * Login - Use to login in the app
  * SignUp -Use to create a new user
  * DiaryEntries -Use to manage the list of diary note
  * AddEditDiary - use to create or to edit a diary note
  * DiaryDetail - use to read or to delete a diary note
  
  As the app is created following the mvp architecture,each screen is implemented using the following classes and interfaces:
  
  * A contract class which defines the connection between the view and the presenter.
  * An Activity which creates fragments and presenters.
  * A Fragment which implements the view interface.
  * A presenter which implements the presenter interface in the corresponding contract.
  
  A presenter typically hosts business logic associated with a particular feature, and the corresponding view handles the Android UI work. The view contains almost no logic; it converts the presenter's commands to UI actions, and listens for user actions, which are then passed to the presenter.
  ## Built With
  * Android Studio 3.0.1
  * Java 8
  * Gradle 3.0.1
  ## Author
  **David Kathoh** [Fiind me here](https://twitter.com/DavidKathoh)
  

  
   
     

    


 
