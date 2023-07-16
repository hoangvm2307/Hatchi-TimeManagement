<%-- 
    Document   : signIn
    Created on : Mar 10, 2023, 2:37:11 PM
    Author     : hoangvmdeptrai
--%>

<!DOCTYPE html>
<%@ page session="false" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignIn</title>
    <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
    <link rel="stylesheet" href="assets/css/signIn.css">
</head>

<body>
    <div class="main">
        <div class="screen">
            <img class="screen__image" src="assets/images/Simon-Wonder.gif" />
        </div>
        <div class="login">
            <h1 class="login__header">Sign In</h1>
            <form action="login" name="" method="POST"> 
                <div>
                    <input class = "login__input"placeholder="Name" name="username" type="text" />
                </div>

                <div>
                    <input class = "login__input"placeholder="Password" name="password" type="password" />

                </div>

                <div>
                    <input class = "login__submit lg-btn" type="Submit" value="SIGN IN" />
                </div>
            </form>

            <div>
                <span><a class="login__account" href="signup?action=create">Create account</a></span>

            </div>
        </div>
    </div>

</body>

</html>