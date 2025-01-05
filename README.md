# Phase 3 #

## Instructions on how to run the project ##
In order to run the project, make sure to run the ChatServer first and then the Client. It will then prompt you to log in or create a new account, to which you would have to create a new account. From there, you can either message, view friends, view blocks, go public, or exit the app. To add a friend, create another user and click on view friends. From there you can see all users and are now able to friend them. If you click on direct messages, you can only message people who you are friends with, unless you click on the Go Public button in the options screen. When you enter the chat room, you can type anything and press the send button. The other person has to click on the refresh button to see it. Also, if you want to delete a message, type /d[message] into the chat, and it'll delete the message as long as you're the one who sent it. You can also block users, which also removes them from your friend list. They are also not able to add you as a friend back until you unblock them.
### Demo: [click here to watch](https://youtu.be/Y5FoG9Gjpi0)

## Classes ##

### ChatServer.java ###

This class initiates a server for users to chat with other users on the network. The class outlines the attributes that come with a messaging server. It also includes the methods to show the different attributes, as well as adding different attributes within those attributes. This class implements ChatServerInterface.java.

### ChatServerClientTest.java ###

This class runs any test cases to check whether the classes related to server and client work correctly.

### ChatServerInterface.java ###

This interface is implemented in ChatServer.java, and it defines the methods listed within ChatServer.java.

### Client.java ###

This allows a user to connect to the network, where they can interact with other users. This also includes the GUI functionalities, allowing the user to use the application in a user-friendly way.

### ClientHandler.java ###

This class handles the clients initiated when a user starts the application. This includes a run method so multiple threads (users) can use the application at the same time. It also includes the methods to show the different attributes, as well as adding different attributes within those attributes. This class implements Runnable, which helps to run the threads.

### ClientHandlerInterface.java ###

This interface is implemented in ClientHandler.java, and it defines the methods listed within ChatHandler.java.

### ClientInterface.java ###

This interface is implemented in Client.java, and it defines the methods listed within Client.java.

### Message.java ###

This class allows the opportunity to create a Message object, which will allow a user to send test messages to another user. The class outlines the attributes that come with a text message. It also includes the methods to show the different attributes, as well as adding different attributes within those attributes. This class implements MessageInterface.java.

### MessageDatabase.java ###

This class allows the ability for a user to send messages through a server. While the server has not been created, this class would allow two users to interact with each other through direct messaging. This class implements MessageDatabaseInterface.java.

### MessageDatabaseInterface.java ###

This interface is implemented in MessageDatabase.java, and it defines the methods listed within MessageDatabase.java.

### MessageInterface.java ###

This interface is implemented in Message.java, and it defines the methods listed within Message.java.

### Profile.java ###

This class allows the opportunity to create a Profile object, which allows a user be on the social media. The class outlines the attributes that come with a text message. It also includes the methods to show the different attributes, as well as adding different attributes within those attributes. This classs implements ProfileInterface.java.

### ProfileInterface.java ###

This interface is implemented in Post.java, and it defines the methods listed within Post.java.

### RunLocalTest.java ###

This class runs any test cases to check whether the classes work correctly.

### UserDatabase.java ###

This class holds the users on the app, while also allowing users to do certain actions (e.g. add or block other users). This class implements MessageDatabaseInterface.java.

### UserDatabaseInterface.java ###

This interface is implemented in UserDatabase.java, and it defines the methods listed within UserDatabase.java.
