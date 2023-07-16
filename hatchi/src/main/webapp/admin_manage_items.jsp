<%@page import="hatchi.model.Simon.SimonDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/admin_manage.css">
        <link rel="stylesheet" href="./assets/css/admin_manage_items.css">
        <title>Admin manage items</title>
    </head>
    <body>
        <div class="main"> 
            <jsp:include page="menuAdmin.jsp" flush="true" />
            <!-- Main part -->
            <div class="container">
                <div class="container-header">
                    <div class="header-user"></div>
                    <div class="header-title">
                        <p>Items Management</p>
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
                                <form class="modal-search-form" action=item method="">
                                    <button type="submit"><img src='./assets/images/icon-search.png'/></button>
                                    <input type="hidden" name="action" value="search"/>
                                    <input type="text" name="searchValue" value="${searchValue}" placeholder="Search Name"/>
                                </form>
                            </div>
                            <button id="myBtnAdd" class="feature-add-btn" type="">Add Item</button>
                        </div>
                    </div>
                    <div class="body-table-item">
                        <table class="table-item">
                            <tr class="table-item-title">
                                <th style="width: 100px;">ID</th>
                                <th style="width: 300px;">Name</th>
                                <th style="width: 400px;">Link</th>
                                <th>Price</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr


                            <!-- JSP here -->

                            <%! List<SimonDTO> list;
                                int i = 1;
                            %>
                            <% list = (List<SimonDTO>) request.getAttribute("list");
                                if (list != null) {
                                    for (SimonDTO item : list) {
                                        out.print("<tr class='table-item-record'><td>" + item.getId() + "</td> "
                                                + "<td>" + item.getName() + "</td> "
                                                + "<td><a href=" + item.getItemLink() + ">" + item.getName() + "</a></td> "
                                                + "<td>" + item.getPrice() + "</td>"
                                                + "<td><input type=hidden name=action value=edit"
                                                + "<input type=hidden name=id value=" + item.getId() + ">"
                                                + "<input onclick='openModalEdit(" + item.getId() + ")' type=image  src='./assets/images/icon-edit.png'"
                                                + "onMouseOver=\"this.src='./assets/images/icon-edit-hover.png'\""
                                                + "onMouseOut=\"this.src='./assets/images/icon-edit.png'\">"
                                                + "</td>"
                                                + "<td><input type=hidden name=action value=delete"
                                                + "<input type=hidden name=id value=" + item.getId() + ">"
                                                + "<input onclick='openModalRemove(" + item.getId() + ")' type=image  src='./assets/images/icon-remove.png'"
                                                + "onMouseOver=\"this.src='./assets/images/icon-remove-hover.png'\""
                                                + "onMouseOut=\"this.src='./assets/images/icon-remove.png'\">"
                                                + "</td>");

                                        i++;
                                    }
                                    i = 1;
                                }
                            %>
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
                            <form  action="item" method="post">
                                <div class="modal-content-form">
                                    <div class="content-left">
                                        <label for="id">ID</label>
                                        <label for="name">Name</label>
                                        <label for="link">Link</label>
                                        <label for="price">Price</label>
                                    </div>
                                    <div class="content-mid">
                                        <div> : </div>
                                        <div> : </div>
                                        <div> : </div>
                                        <div> : </div>
                                    </div>
                                    <div class="content-right">
                                        <input type="text" readonly="" name="id" id="editValue">
                                        <input type="text" required="" name="name">
                                        <input type="text" required="" name="link">
                                        <input type="text" required="" name="price">
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button class="btn-close-modal" type="">Cancel</button>
                                    <div style="display: inline">
                                        <input name="action" value="edit" type="hidden">
                                        <input class="modal-footer-btn close" type="submit" value="Save">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="body-modal-add" id="myModalAdd" style="display: none;">
                    <div class="modal-edit-content">
                        <div class="edit-header">
                            <div class="edit-title">
                                <p>Add</p>
                            </div>
                        </div>
                        <div class="edit-body">
                            <form>
                                <div class="modal-content-form">
                                    <div class="content-left">
                                        <label for="name">Name</label>
                                        <label for="link">Link</label>
                                        <label for="price">Price</label>
                                    </div>
                                    <div class="content-mid">
                                        <div> : </div>
                                        <div> : </div>
                                        <div> : </div>
                                    </div>
                                    <div class="content-right">
                                        <input type="text" name="name" value="">
                                        <input type="text" name="link" value="">
                                        <input type="text" name="price" value="">
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <div style="display: inline">
                                        <button class="btn-close-modal" type=>Cancel</button>
                                    </div>

                                    <div style="display: inline">
                                        <input name="action" value="add" type="hidden">
                                        <input class="modal-footer-btn close" type="submit" value="Add">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="body-modal-remove" id="myModalRemove" style="display: none;">
                    <div class="modal-remove-body">
                        <p>Are you sure to delete<br>this tag?</p>
                    </div>
                    <div class="modal-btn">
                        <button class="btn-discard" type="">No</button>
                        <form class="modal-btn-form" action=item>
                            <input type=hidden name=action value=delete>
                            <input type=hidden id="removeValue" name=id>
                            <input class="btn-agree" type=submit value=Yes> 
                        </form>
                    </div>
                </div>

                <%! String mess;%>
                <% mess = (String) request.getAttribute("mess");
                    if (mess != null) {
                        out.print("<div class='body-modal-remove' id='myModalMess' style='display: inline'>"
                                + "<div class='modal-remove-body' style='padding: 30px 50px 0px 50px'>"
                                + "<p>" + mess + "</p></div>"
                                + "<button class='btn-close-mess'>Close</button>"
                                + "</div>");
                    }
                %>

                <div class="container-footer">
                    <div class="footer-btn">

                        <div class='pagination--list--items page-item ${tagActive == 1?"disabled":""}'>
                            <form action="item" method="GET">
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
                            <form action="item" method="GET">
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