Detailed Specification

1. Requirements:
    - Name of system: Hatchi
    - Inspiration: 
      The Pomodoro method is a productivity technique developed by Francesco Cirillo in the 1980s. It involves breaking work into focused, 25-minute intervals known as 'Pomodoros', separated by short breaks. After four Pomodoros, a longer break is taken. The method is designed to help users manage their time more effectively, stay focused and avoid distractions, and maintain a consistent level of productivity throughout the day.
    - Purpose: Help users 
        + Users can develop time managment and planing skill to optimize their tasks.
        + Create good habits of planning and organizing work.
        + Improve morale and concentration in study and work.
        + Arange all tasks very scientifically through time limits. The job was quickly completed.
    - User: Everyone, mostly students
    - Features:
        + SignIn: login account with username and password. If user has role as 'US', the web will move to simon page. If user has role as 'AD', the web will move to admin page. 
        + SignUp: create new account with username, password and email.
        + Timer page: implement the Pomodoro timer feature - allow users to set a timer for 25 minutes and start a countdown. When the timer ends, pause the timer and display a message to the user to take a break, along with offering rewards. Besides, user can set a timer wit the time they want.
        + Implement the reward system: allow users to select a reward that they would like to earn after completing a task. The rewards could be in the form of points, or virtual items.
        + The virtual shop: Users should be able to make the purchase using the virtual currency they have earned.
        + Analysis time user system: Use this data to provide users with insights and analysis about their productivity. For example, display graphs or charts that show the user's work patterns over time, or provide recommendations for improving their productivity based on their data.
        + Admin page: manage users, items in shop and tags: show the list and edit, delete, add, search items in list.
        
2. Wireframe 
  Sitemap
![diagram](https://user-images.githubusercontent.com/60658750/227496906-c3e7dbcb-ace4-4da7-8769-9763b1f365aa.png)


 
  Client wirefram
   .SignUp
    ![signUp](https://user-images.githubusercontent.com/110725621/222964293-5d573967-1fce-47da-8a03-a4de0b527e5d.jpg)

   .SignIn
   ![signIn](https://user-images.githubusercontent.com/110725621/222964300-7f52d731-a137-4fbd-b968-9769b5a2c066.jpg)

   - User:
  
   .Timer
   ![timer](https://user-images.githubusercontent.com/110725621/224495455-08e7acad-e54b-4dd6-be3b-49f0b5a07422.jpg)

   .Simon
    ![simon](https://user-images.githubusercontent.com/110725621/224495481-6d5c7be3-051e-4c75-9c58-1a39815748b6.jpg)

   .Store
    ![store01](https://user-images.githubusercontent.com/110725621/224495520-f97de812-4955-4e66-8c19-db0f464669a1.jpg)
       ![store02](https://user-images.githubusercontent.com/110725621/224495522-6a25b239-8ec3-4f0e-a756-e8b012a7fbbe.jpg)

   .Analysis
    ![image](https://user-images.githubusercontent.com/60658750/227521135-c04bb169-9402-4bd3-af81-58e9d377c65a.png)
    ![image](https://user-images.githubusercontent.com/60658750/227520783-7020f739-8aa1-4226-98c2-01e2e88e1139.png)
    ![image](https://user-images.githubusercontent.com/60658750/227520841-1c27ce3b-73fa-438c-960b-db29e7f8a232.png)
    
   .Setting
   ![setting](https://user-images.githubusercontent.com/110725621/222964336-5c626b86-d31e-41eb-91fe-a63a8a66bff8.jpg)

   - Admin:
     .User management
    ![admin_userManage01](https://user-images.githubusercontent.com/110725621/224495613-3aa4e92c-f124-4f5b-965e-f5ca5c9c08f3.jpg)
    ![admin_userManage02](https://user-images.githubusercontent.com/110725621/224495614-2f61cae7-8028-4c76-adfe-47506e831db7.jpg)
     ![admin_userManage03](https://user-images.githubusercontent.com/110725621/224495611-87a8ab6a-9ac0-4972-ba79-1f7b2afbd149.jpg)

     .Item management
    ![admin_productManage01](https://user-images.githubusercontent.com/110725621/224495647-3966fc53-2658-4534-93fc-dd1feadf6c26.jpg)
    ![admin_productManage02](https://user-images.githubusercontent.com/110725621/224495649-fb89def3-4106-4e72-90a5-ca0269c83c88.jpg)
    ![admin_productManage03](https://user-images.githubusercontent.com/110725621/224495641-2595d3f6-ce9e-48d5-963e-bccd6e1e4a55.jpg)

     .Tags management
     ![admin_tagManage01](https://user-images.githubusercontent.com/110725621/224495687-f7c5ceb5-5454-46ab-aa05-cdd794b39bfa.jpg)
     ![admin_tagManage02](https://user-images.githubusercontent.com/110725621/224495689-74c636c3-6479-432c-9065-86eb4cb28b81.jpg)
     ![admin_tagManage03](https://user-images.githubusercontent.com/110725621/224495683-3c76a480-e6d8-4e88-94bd-2ef88ca60c20.jpg)



3. Database:
![Untitled](https://user-images.githubusercontent.com/60658750/227090700-e2f890d3-de26-48db-bc9c-b34d85635ae4.png)
4. Conclusion:
- What we learnt:
    - Working in a team when building a web app, dividing tasks to minimize conflicts
    - Working in a team with Agile & Scrum model to achieve the planned progress
    - Understanding how a web app works and its layout.
    - Understanding MVC Model 2 to make web app maintenance easier.
    - Learned additional programming techniques:
        + Pagination to divide a list into smaller lists and navigate between pages
        + Ajax to avoid reloading the page
        + Quartz to create a scheduler for the chart component.
- What we failed:
    - Haven't implemented password hashing
    - There is no error notification when logging in or creating an account
    - Missing a chart before the reporting date. However, it was completed on March 24th.
    - No restrain when deleting tags
"# Hatchi-TimeManagement" 
