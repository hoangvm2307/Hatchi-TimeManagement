<%@page import="java.util.ArrayList"%>
<%@page import="hatchi.model.Simon.SimonDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=deviice-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
        <link rel="stylesheet" href="assets/css/GlobalStyles/grid.css">
        <link rel="stylesheet" href="assets/css/ownPets.css">
        <link rel="stylesheet" href="assets/css/menu.css">
        <link rel="stylesheet" href="assets/css/userSign.css">

        <title>Simon</title>
    </head>
    <body>
        <div class="main"> 
            <jsp:include page="menu.jsp" flush="true" />


            <div class="wrap">

                <div class="container">
                    <div class="container__header">

                        <!-- fix sau  -->
                        <div class="container__header__left w-33">

                        </div>
                        <div class="container__header__middle w-33">
                            <div class="header__title">Simon</div>
                        </div>

                        <jsp:include page="userSign.jsp" flush="true" />

                    </div>

                    <div class="top-container">

                        <%
                            ArrayList<SimonDTO> listOwnedItem = (ArrayList<SimonDTO>) request.getAttribute("listOwnedItem");
                            String index = request.getParameter("index");
                            if (index == null) {
                                index = "1";
                            }
                        %>


                        <ul class="top-container__list">
                            <c:forEach items="<%=listOwnedItem%>" var="item">
                                <li class="top-container__list--item">


                                    <a href='simon?indexImage=${item.id}&index=<%=index%>'>
                                        <img class="square" src="<c:out value="${item.itemLink}"/>" alt="example GIF">
                                    </a>
                                </li>
                            </c:forEach>




                            <%--Pagination--%>
                        </ul>

                        <div class="f-width">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end">
                                    <li class="pagination--list--items">

                                    </li>

                                    <%
                                        String indexImage = request.getParameter("indexImage");
                                    %>

                                    <li class='pagination--list--items page-item ${tagActive == 1?"disabled":""}'>
                                        <form action="simon" method="POST">
                                            <input type="hidden" name="indexImage" value="<%=indexImage%>"/>
                                            <input type="hidden" name="index" value="${tagActive - 1}"/>
                                            <input class="page-link pagination-button"  type="submit" value="<"/>
                                        </form>
                                    </li>
                                    <li class='pagination--list--items page-item ${tagActive == endPage?"disabled":""}'>
                                        <form action="simon" method="POST">
                                            <input type="hidden" name="indexImage" value="<%=indexImage%>"/>
                                            <input type="hidden" name="index" value="${tagActive + 1}"/>
                                            <input class="page-link pagination-button"  type="submit" value=">"/>
                                        </form>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>


                    <div class="bottom-container">

                        <div class="list__item--items--header">

                            <h3 class="opacity-background">
                                <c:if test="${empty requestScope.selected.name}">
                                    ${sessionScope.listOwnedItem[sessionScope.listOwnedItem.size() - 1].name}
                                </c:if>

                                <c:if test="${not empty requestScope.selected.name}">
                                    ${requestScope.selected.name}
                                </c:if>
                            </h3>

                        </div>
                        <img class="bottom-container__gif" 
                             src="

                             <c:if test="${empty requestScope.selected.itemLink}">
                                 ${sessionScope.listOwnedItem[sessionScope.listOwnedItem.size() - 1].itemLink}
                             </c:if>

                             <c:if test="${not empty requestScope.selected.itemLink}">
                                 ${requestScope.selected.itemLink}
                             </c:if>
                             "
                             alt="example GIF"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>