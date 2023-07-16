<%@page import="java.util.ArrayList"%>
<%@page import="hatchi.model.Simon.SimonDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
        <link rel="stylesheet" href="assets/css/GlobalStyles/grid.css">        
        <link rel="stylesheet" href="assets/css/design_storeOld.css">
        <link rel="stylesheet" href="assets/css/menu.css">
        <link rel="stylesheet" href="assets/css/userSign.css">

        <title>Store</title>
    </head>

    <body>
        <div class="main">
            <jsp:include page="menu.jsp" flush="true" />

            <div class="wrap">
                <div class="container">
                    <div class="container__header">

                        <!-- fix sau  -->

                        <div class="container__header__left w-33">
                            <%-- search --%>
                            <form action="store" method="get">
                                <input placeholder="Search Simon" type="text" name="searchValue" value="${searchValue}"/>
                                <input class="search-btn" type="submit" value="Search"/>

                            </form>
                        </div>
                        <div class="container__header__middle w-33">
                            <div class="header__title">Store</div>
                        </div>

                        <jsp:include page="userSign.jsp" flush="true" />

                    </div>

                    <div class="container__body">
                        <div class="list__item row">
                            <%!ArrayList<SimonDTO> listOwnItem;%>
                            <%listOwnItem = (ArrayList<SimonDTO>) request.getAttribute("listItem");%>
                            <c:forEach var="item" items="<%=listOwnItem%>">

                                <c:set var="status">
                                    <c:choose>
                                        <c:when test='${item.enable == "OWNED"}'>
                                            own-btn
                                        </c:when>

                                        <c:when test='${item.enable == "BUY"}'>
                                            buy-btn
                                        </c:when>
                                    </c:choose>
                                </c:set>
                                <c:set var="nameValue">
                                    <c:choose>
                                        <c:when test='${item.enable == "OWNED"}'>Owned</c:when>

                                        <c:when test='${item.enable == "BUY"}'>Buy</c:when>
                                    </c:choose>
                                </c:set>

                                <div class="list__item--item col c-6">
                                    <div class="list__item--item--wrap">
                                        <img class="list__item--items--photo f-width"
                                             src="<c:out value="${item.itemLink}"/>" />

                                        <div class="list__item--items--header">
                                            <div class="w-33"></div>
                                            <div class="w-33">
                                                <h3 class="opacity-background">${item.name}</h3>
                                            </div>
                                            <div class="w-33 list__item--items--header--coin">
                                                <span>${item.price}</span>
                                                <img src="assets/images/coin__icon.png" class="coin" />
                                            </div>
                                        </div>

                                        <div class="list__item--items--footer">

                                            <form action="BuyController" method="POST">
                                                <input name="itemID" value="${item.id}" type="hidden"/>

                                                <input 
                                                    type="submit" 
                                                    value="${nameValue}" 
                                                    class='${status}'
                                                    />
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>


                    <div class="container__footer">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-end">

                                <li class="pagination--list--items">
                                    <h2>${tagActive} / ${endPage}</h2>
                                </li>

                                <li class='pagination--list--items page-item ${tagActive == 1?"disabled":""}'>
                                    <form action="store" method="GET">
                                        <input type="hidden" name="searchValue" value="${searchValue}"/>
                                        <input type="hidden" name="index" value="${tagActive - 1}"/>
                                        <input class="page-link pagination-button"  type="submit" value="<"/>
                                    </form>
                                </li>
                                <li class='pagination--list--items page-item ${tagActive == endPage?"disabled":""}'>
                                    <form action="store" method="GET">
                                        <input type="hidden" name="searchValue" value="${searchValue}"/>
                                        <input type="hidden" name="index" value="${tagActive + 1}"/>
                                        <input class="page-link pagination-button"  type="submit" value=">"/>
                                    </form>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</body>

</html>