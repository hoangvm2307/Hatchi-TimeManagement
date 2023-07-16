<%-- 
    Document   : timer
    Created on : Mar 11, 2023, 9:19:29 PM
    Author     : hoangvmdeptrai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
        <link rel="stylesheet" href="assets/css/GlobalStyles/grid.css">
        <link rel="stylesheet" href="assets/css/timer.css">
        <link rel="stylesheet" href="assets/css/menu.css">
        <link rel="stylesheet" href="assets/css/userSign.css">

        <script src="assets/js/timer.js"></script>
        <title>Timer</title>
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
                            <div class="header__title">Timer</div>
                        </div>

                        <jsp:include page="userSign.jsp" flush="true" />



                    </div>

                    <div class="container__clock">
                        <div class="container__clock--item container__clock--main">
                            <div class="container__clock--main-wrap">
                                <div class="container__clock--coin">
                                    <span id="coinamount">10</span>
                                </div>
                                <div class="container__clock--icon ">
                                    <img class="full-width clock__icon" src="assets/images/coin__icon.png"/>
                                </div>
                            </div>
                        </div>
                        <!--                    <div class="container__clock--item container__clock--tag m-btn" id="tagname">Study</div>-->
                        <select class ="container__clock--item container__clock--tag m-btn" name="study" id="tagname">
                            <c:forEach items="${sessionScope.allTags}" var="tag">
                                <option value="${tag.getName()}">${tag.getName()}</option>
                            </c:forEach>
                        </select>
                        <div class="container__clock--item container__clock--setTime" name="timer">
                            <button onClick="decreaseTime()" class="m-btn control-btn">-</button>
                            <span id="timer">10:00</span>
                            <button onClick="increaseTime()" class="m-btn control-btn">+</button>
                        </div>
                        <div class="container__clock--item container__clock--start"><button class ="s-btn container__clock--start--button" onclick="startClock(); insertIntoDatabase()">Start clock</button></div>
                        <div class="container__clock--item container__clock--stop"><button class ="s-btn container__clock--stop--button" onclick="updateDatabase(); stopClock()">Stop clock</button></div>
                    </div>

                    <div class="container__background">
                        <img src="assets/images/simon-waiting.gif" class="full-width"/>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>