<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
        <link rel="stylesheet" href="assets/css/settings.css">
        <link rel="stylesheet" href="assets/css/menu.css">
        <link rel="stylesheet" href="assets/css/userSign.css">

        <title>Settings</title>
    </head>

    <body>
        <div class="main">
            <c:import url="menu.jsp"/>

            <div class="wrap">

                <div class="container">
                    <div class="container__header">

                        <!-- fix sau  -->

                        <div class="container__header__left w-33">

                        </div>
                        <div class="container__header__middle w-33">
                            <div class="header__title">Settings</div>
                        </div>

                        <jsp:include page="userSign.jsp" flush="true" />

                    </div>

                    <div class="container__setting">
                        <div class="container__setting--left">
                            <h2 class="text--header">Account</h2>

                            <c:choose>
                                <c:when test="${sessionScope.usersession != null}">
                                    <div>${sessionScope.usersession.getUsername()}</div>
                                    <a href="login">Log out</a>

                                </c:when>
                                <c:otherwise>
                                    <a href="login">Sign Up and Login</a>
                                </c:otherwise>
                            </c:choose>




                        </div>
                        <div class="container__setting--right">
                            <div class="container__setting--right--timer">
                                <h2 class="text--header">Timer</h2>
                                <p>Owned Scenes: ${sessionScope.listOwnedItem.size()}/${sessionScope.allItems}</p>
                                <p>Spent Time: 2 Days 15 Hours</p>
                            </div>
                            <div class="container__setting--right--aboutus">
                                <h2 class="text--header">About Us</h2>
                                <p>Facebook: <a href="#">Lofi&Chill</a></p>
                            </div>
                        </div>
                    </div>

                    <div class="container__background">
                        <img src="assets/images/Simon-CatchMouse.gif" class="full-width" />
                    </div>

                </div>
            </div>

        </div>
    </body>

</html>