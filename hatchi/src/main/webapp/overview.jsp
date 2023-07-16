<%-- 
    Document   : overview
    Created on : Mar 9, 2023, 9:30:37 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overview</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
        <link rel="stylesheet" href="assets/css/GlobalStyles/grid.css">
        <link rel="stylesheet" href="assets/css/overview.css">
        <link rel="stylesheet" href="assets/css/menu.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@4.2.1/dist/chart.umd.min.js"></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="menu.jsp" flush="true"/>

            <div class="wrap" id="wrap">
                <div class="container">
                    <div class="container__header">
                        <div class="container__header__left w-33"></div>
                        <div class="container__header__middle w-33">
                            <div class="header__title">Overview</div>
                        </div>
                        <div class="container__header__right w-33"></div>
                    </div>
                    <div class="nav__container">
                        <form action="overview" class="nav__title__container">
                                <input type="hidden" name="activeNav" value="Day"/>
                                <input class="${sessionScope.activeNav eq "Day" ? "nav__title__active" : "nav__title"}" type="submit" value="Day"/>
                        </form>
                        <form action="overview" class="nav__title__container">
                                <input type="hidden" name="activeNav" value="Week"/>
                                <input class="${sessionScope.activeNav eq "Week" ? "nav__title__active" : "nav__title"}" type="submit" value="Week"/>
                        </form>
                        <form action="overview" class="nav__title__container">
                                <input type="hidden" name="activeNav" value="Month"/>
                                <input class="${sessionScope.activeNav eq "Month" ? "nav__title__active" : "nav__title"}" type="submit" value="Month"/>
                        </form>
                    </div>
                    <div class="body__container">
                        <c:choose>
                            <c:when test="${sessionScope.activeNav == 'Day'}">
                                <jsp:include page="overviewDay.jsp" flush="false"/>
                            </c:when>
                            <c:when test="${sessionScope.activeNav == 'Week'}">
                                <jsp:include page="overviewWeek.jsp" flush="false"/>
                            </c:when>
                            <c:when test="${sessionScope.activeNav == 'Month'}">
                                <jsp:include page="overviewMonth.jsp" flush="false"/>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
