<%-- 
    Document   : signUp
    Created on : Mar 10, 2023, 6:59:01 PM
    Author     : hoangvmdeptrai
--%>

<!DOCTYPE html>
<%@ page session="false" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>
    <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
    <link rel="stylesheet" href="assets/css/signUp.css">
</head>

<body>
    <div class="main">
        <div class="screen">
            <img class="screen__image" src="assets/images/Simon-Wonder.gif" />
        </div>
        <div class="login">
            <h1 class="login__header">Sign Up</h1>
            <form action="login" name="insert" method="POST">
                <div>
                    <input class="login__input" placeholder="Name" name="username" type="text" />
                </div>

                <div>
                    <input class="login__input" placeholder="Password" name="password" type="password" />
                </div>

                <div>
                    <input class="login__input" placeholder="Email" name="email" type="email" />
                </div>

                <div>
                    <input class="login__submit lg-btn" type="Submit" value="SIGN UP" />
                </div>
                <input name="action" value="insert" type="hidden">
            </form>

            <div>
                <span class ="login__account">Already had an account? <a class="ount" href="login">Sign In</a></span>
            </div>
        </div>
    </div>

</body>

</html>