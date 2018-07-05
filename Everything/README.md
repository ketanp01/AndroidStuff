# Everything

It is a blogging application that has the functionality to send the post as text. Users need to login in order to use the app.

## Project Description

'Everything' is the name of the app. I created it as a learning project to know about some important concepts of Android such as fragments, swipe tabs, and viewpager.
Viewpager is one of the most popular Widgets in the Android library. It allows the user to swipe left or right to see an entirely new screen. In a sense, it's just a nicer way to show the user multiple tabs.
I have used viewpager in the signUp and signIn activity and also in the mainactivity to make the user experience more dynamic.

### Files Included(Essential Ones)

- signIn.java: The Fragment that provides the user with the UI to enter into the app via valid credentials.
- signUp.java: Same as signIn.java. Rather it has the functionality for a new user to sign up.
- TabsPagerAdapter: Extends the fragmentPagerAdapter class to attach the signIn and signUp fragments to the layout manager.
