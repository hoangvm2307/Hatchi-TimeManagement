<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="./assets/css/admin_manage.css">
        <link rel="stylesheet" href="./assets/css/admin_user.css">
        <title>Admin manage users</title>
    </head>
    <body>
        <div class="main"> 
            <jsp:include page="menuAdmin.jsp" flush="true" />
            <!-- Main part -->
            <div class="container">
                <div class="container-header">
                    <div class="header-user"></div>
                    <div class="header-title">
                        <p>Users Management</p>
                    </div>
                    <div class="header-user">
                        <img class="header-user-ava" src="./assets/images/user-ava.png" alt="">
                        <div class="header-user-info">
                            <p>ADMIN</p>
                            <p> ${sessionScope.usersession.getUsername()} </p>
                        </div>
                    </div>
                </div>
                <div class="container-body">
                    <div class="body-features">
                        <div class="features-container"></div>
                        <div class="features-container">
                            <div class="feature-search">
                                <form class="modal-search-form" action=edit method="">
                                    <button type="submit"><img src='./assets/images/icon-search.png'/></button>
                                    <input type=hidden name=action value=search>
                                    <input type="hidden" /> 
                                    <input type="text" name="searchValue" value="${searchValue}" placeholder="Search Name">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="body-table-user">
                        <table class="table-user">
                            <tr class="table-user-title">
                                <th>ID</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Email</th>
                                <th>Money</th>
                                <th>Time</br>(minutes)</th>
                                <th>CreateAt</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <!-- JSP here -->
                            <c:forEach items="${requestScope.users}" var="user">
                                <tr class="table-user-record">
                                    <td>${user.getUserID()}</td>
                                    <td>${user.getUsername()}</td>
                                    <td>${user.getPassword()}</td>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.getMoney()}</td>
                                    <td>${user.getTime()}</td>
                                    <td>${user.getCreateAt()}</td>
                                    <td><input type=hidden name=action value=edit"
                                               <input type=hidden name=id value="${user.getUserID()}">
                                        <input onclick="openModalEdit(${user.getUserID()})" type=image  src='./assets/images/icon-edit.png'
                                               onMouseOver="this.src = './assets/images/icon-edit-hover.png'"
                                               onMouseOut="this.src = './assets/images/icon-edit.png'">
                                    </td>
                                    <td><input type=hidden name=action value=delete
                                               <input type=hidden name=id value="${user.getUserID()}">
                                        <input onclick='openModalRemove(${user.getUserID()})' type=image  src='./assets/images/icon-remove.png'
                                               onMouseOver="this.src = './assets/images/icon-remove-hover.png'"
                                               onMouseOut="this.src = './assets/images/icon-remove.png'">
                                    </td>
                                </tr>
                            </c:forEach>
                            <!-- --- -->
                        </table>
                    </div>
                </div>

                <div class="body-modal-edit" id="myModalEdit" style="display: none;">
                    <div class="modal-edit-content">
                        <div class="edit-header">
                            <div class="edit-title">
                                <p>Edit</p>
                            </div>
                        </div>
                        <div class="edit-body">
                            <form action="edit">
                                <div class="modal-content-form">
                                    <div class="content-left">
                                        <label for="id">ID</label>
                                        <label for="username">Username</label>
                                        <label for="password">Password</label>
                                        <label for="email">Email</label>
                                        <label for="money">Money</label>
                                        <!--<label for="time">Time</label>-->
                                    </div>
                                    <div class="content-mid">
                                        <div> : </div>
                                        <div> : </div>
                                        <div> : </div>
                                        <div> : </div>
                                        <div> : </div>
                                        <!--<div> : </div>-->
                                    </div>
                                    <div class="content-right">
                                        <input type="text" readonly="" name="id" id="editValue">
                                        <input type="text" name="username" value="">
                                        <input type="text" name="password" value="">
                                        <input type="text" name="email" value="">
                                        <input type="text" name="money" value="">
<!--                                        <input type="text" name="time" value="">-->
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn-close-modal">Cancel</button>
                                    <div style="display: inline">
                                        <input class="modal-footer-btn close" type="submit" value="Save">
                                        <input type=hidden id="editValue" name=id>
                                        <input type="hidden" name="action" value="update">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="body-modal-remove" id="myModalRemove" style="display: none;">
                    <div class="modal-remove-body">
                        <p>Are you sure to delete<br>this user?</p>
                    </div>
                    <div class="modal-btn">
                        <button class="btn-discard" type="">No</button>
                        <form class="modal-btn-form" action=edit>
                            <input type=hidden name=action value=delete>
                            <input type=hidden id="removeValue" name=id>
                            <input class="btn-agree" type=submit value=Yes> 
                        </form>
                    </div>
                </div>

                <c:set var="mess" value="${requestScope.mess}" />
                <c:if test="${not empty mess}">
                    <div class='body-modal-remove' id='myModalMess' style='display: inline'>
                        <div class='modal-remove-body' style='padding: 30px 50px 0px 50px'>
                            <p>${mess}</p>
                        </div>
                        <button class='btn-close-mess'>Close</button>
                    </div>
                </c:if>

                <div class="container-footer">
                    <div class="footer-btn" >
                        <div class='pagination--list--items page-item ${tagActive == 1?"disabled":""}'>
                            <form action="edit" method="GET">
                                <input type="hidden" name="action" value="search"/>
                                <input type="hidden" name="searchValue" value="${searchValue}"/>
                                <input type="hidden" name="index" value="${tagActive - 1}"/>
                                <!--<input class="page-link pagination-button"  type="submit" value="<"/>-->
                                <button type="" class="page-link pagination-button ">
                                    <img src="./assets/images/icon-page-left.png" alt="">
                                </button>
                            </form>

                        </div>

                        <div class='pagination--list--items page-item ${tagActive == endPage?"disabled":""}'>
                            <form action="edit" method="GET">
                                <input type="hidden" name="action" value="search"/>
                                <input type="hidden" name="searchValue" value="${searchValue}"/>
                                <input type="hidden" name="index" value="${tagActive + 1}"/>
                                <button type="" class="page-link pagination-button ">
                                    <img src="./assets/images/icon-page-right.png" alt="">
                                </button>
                            </form> 
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="./assets/js/admin_modal.js"></script>
    </body>
</html>