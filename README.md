# Party_Manager
Final Project For Coders Campus
A tool for GM's and Players of TTRPGS to manage their parties, gold, expierence, and alignment.
A one stop shop for top level party management. 

# The Tech

This is a full stack Java application with a MySQL DB. It uses Spring Security for user authentication, Hibernate ORM for DB communication, JS for a few front end tasks, and Spring MVC with Thymeleaf template engine for conditional view display based off the user's role (DM/Player), application mode (demo or full), and party status (in party or not).  

 # Try it out
To run the application simply [click here](https://partymanager-production.up.railway.app/login)

 
No need for any downloading of dependencies or pulling in other applications, this app functions by itself.
 
# Demo mode

You can use demo mode to try out the app without creating an account. You can see what it looks like as both a player and a DM with a set of parties created for demo mode. Demo mode has a few functionalities removed (deleting account/parties) but is otherwise the full application. 

# How to use

If you are a DM register and Select the DM Role. This will allow you to create parties that your players can join. From there you can modify their XP and Coins, as well as remove players from the parties, and keep notes pertaining to your campaing. If you select the view to see all your parties you can delete existing parties from that page.

If you are a Player register and select the Player Role. This will allow you to see all currently active parties, simply click on the party your DM created and join it. From there you can create your character in the party and adjust your own XP and Coins. If you go to the screen to see all of your characters you can delete them on that page. If you leave a party without deleting your character and then rejoin the party your character should be pulled back in. You can click the green see your parties button to see all of the parties you are currently in. 
