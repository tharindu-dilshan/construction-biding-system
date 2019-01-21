# Construction Biding System

![IMAGE](https://i.ibb.co/Vt9pzgX/1.png)

## Introduction
According to the given scenario, management software must be implemented for the construction
field. And, application platform must be able to collaborate and work with those parties like
contractor, architect, quantity surveyor, engineer, consultants. The application will be more
suitable for small scale construction works such as house constructions. In Sri Lanka 
houses are built by individuals and is supervised by the house owner himself. But continuous
supervision is very difficult due to their official works and the busy work schedules. 
This leads to requirement mismatch between the contractor and the house owner. Sometimes 
contractor need to get the opinion from the house owner for various activities, but house 
owner must get advises from other parties such as engineer or architect due to his less 
experience. But this is not possible in a quick time, but contractor cannot waste his labor
time till the decision is made. In this application contractor can take a photograph of the 
place where problem exists and upload it with a problem description. So, house owner can
write his opinion, or he can connect the right party/parties to the post, so he/they can 
write his/their advice.

**Identified Problems**

• There was no proper communication between the client and contractor.
• The client must find other parties for their job category.
• Difficult to maintain the supervising.
• Service providers unable to do their advertisements.
• Opinion discussing between two parties.


**Main Functionalities of the System**

• Project Posting and bidding system.
• Client – service provider chatting system.
• Supplier management process
• Project delivering
• Payment handling

**Use Case Diagram**

![IMAGE](https://i.ibb.co/TKdqCGF/8.png)


**MVC (Model View Controller Diagram)**

• Model - Model represents an object or JAVA POJO carrying data. It can also have logic to
 update controller if its data changes.
• View - View represents the visualization of the data that model contains.
• Controller - Controller acts on both model and view. It controls the data flow into model 
object and updates the view whenever data changes. It keeps view and model separate.

#System #Design

#The system has a three-tier architecture.
• Front Tier
        o This tier designs interfaces.
        o NetBeans and modern UI designs were used to design interfaces.
• Middle Tier
        o This layer makes all instructions.
        o MVC design pattern was used to coding the system.
• Recourse Tier
        o This tier stores data.
        o MySQL Database Management System was used to store data.
• MVC Design pattern.

**Interfaces Used in the Web application**

#Index Page

![IMAGE](https://i.ibb.co/Vt9pzgX/1.png)

This is the first page of the web site. It has included five main site buttons. any user can 
view posts by posting the clients. And sign in page redirect the web page to login screen. 
Sign-up button let us to create a new account user for the web site. About us button include 
information regarding the organization.

#Sign-in page

![IMAGE](https://i.ibb.co/M5Yq1CX/2.png)

This Page facilitate the users to login into the web page. Main Login for the system.


#Sign-up Form


![IMAGE](https://i.ibb.co/YNqyQMf/3.png)

For new users they must have to fill those fields and sign up to the web site. but he/she 
provide validate information to the system otherwise he/she was unable to sign up to the system.

#Main Form After Login/Sign-up


![IMAGE](https://i.ibb.co/CPd7J8G/4.png)

After Sign up/Login this page will be automatically redirect to the user. This has additionally
 included three buttons. new post, my post and logout.


 #All Post Form

![IMAGE](https://i.ibb.co/K27tgph/5.png)

This form has include all the post of other users posted, user can view them and able to send 
proposal for them.


#New Post form

![IMAGE](https://i.ibb.co/V0G2YkN/6.png)

User Can Post the projects using this form, but there was an validation part for this. Bcause 
some users can provide fake details to the post. Therefore user data validation is required 
for thr system.

#My Post Form


![IMAGE](https://i.ibb.co/YN7g2D8/7.png)

In this form included the user posts that he was post to the public. And also, those post 
indicates if some one apply to them. It will appear as screen shows.
