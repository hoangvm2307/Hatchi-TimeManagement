

<%@page import="hatchi.Tag.TagDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/admin_manage.css">
    <link rel="stylesheet" href="./assets/css/admin_manage_tags.css">
    <title>Admin manage tags</title>
</head>
<body>
    <div class="main"> 
        <jsp:include page="menuAdmin.jsp" flush="true" />
        <!-- Main part -->
        <div class="container">
            <div class="container-header">
                <div class="header-user"></div>
                <div class="header-title">
                    <p>Tags Management</p>
                </div>
                <div class="header-user">
                    <img class="header-user-ava" src="./assets/images/user-ava.png" alt="">
                    <div class="header-user-info">
                        <p>ADMIN</p>
                        <p> ${sessionScope.usersession.getUsername()} </p>
                    </div>
                </div>
            </div>
            <div class="container-body" style="height: 85% !important;">
                <div class="body-features">
                    <div class="features-container"></div>
                    <div class="features-container" style="margin-right: 20px;">
                        <button id="myBtnAdd" class="feature-add-btn" type="">Add Tag</button>
                    </div>
                </div>
                <div class="body-info">
                    <div class="body-table-tag">
                        <table class="table-tag">
                            <tr class="table-tag-title">
                                <th>ID</th>
                                <th>Name</th>
                                <th>Color</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            
                            <%! List<TagDTO> list;
                                int i = 1;
                            %>
                            <% list = (List<TagDTO>) request.getAttribute("list"); 
                                if( list != null){
                                    for (TagDTO tag : list){
                                        out.print("<tr class='table-tag-record'><td>" + i + "</td> " 
                                                + "<td>" + tag.getName() + "</td> " 
                                                + "<td>" + tag.getColor() + "</td> " 
                                                + "<td><input type=hidden name=action value=edit"
                                                    + "<input type=hidden name=id value=" + tag.getId() +">"
                                                    + "<input onclick='openModalEdit("+tag.getId()+")' type=image  src='./assets/images/icon-edit.png'"
                                                    +"onMouseOver=\"this.src='./assets/images/icon-edit-hover.png'\""
                                                    +"onMouseOut=\"this.src='./assets/images/icon-edit.png'\">"
                                                + "</td>"
                                                + "<td><input type=hidden name=action value=delete"
                                                    + "<input type=hidden name=id value=" + tag.getId() +">"
                                                    + "<input onclick='openModalRemove("+tag.getId()+")' type=image  src='./assets/images/icon-remove.png'"
                                                    +"onMouseOver=\"this.src='./assets/images/icon-remove-hover.png'\""
                                                    +"onMouseOut=\"this.src='./assets/images/icon-remove.png'\">"
                                                + "</td>");
                                        i++;
                                    }
                                    i = 1;
                                }
                            %>
                        </table>
                    </div>
                            
                    <div class="body-modal" id="myModal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-title">
                                    <p>Detail</p>
                                </div>
                            </div>
                            <div class="modal-body">
                              <div class="modal-body-content">
                                <form>
                                    <div class="modal-content-form">
                                        <div class="content-left">
                                            <label for="id">ID</label>
                                            <label for="name">Name</label>
                                            <label for="color">Color</label>
                                        </div>
                                        <div class="content-mid">
                                            <div> : </div>
                                            <div> : </div>
                                            <div> : </div>
                                        </div>
                                        <div class="content-right"></div>
                                    </div>
                                    
                                    <div class="modal-footer">
                                        <input class="modal-footer-btn" readonly="" value="Save">
                                    </div>
                                </form>
                              </div>
                            </div>
                        </div>
                    </div>
                        
                    <div class="body-modal" id="myModalEdit" style="display: none;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-title">
                                    <p>Edit</p>
                                </div>
                            </div>
                            <div class="modal-body">
                              <div class="modal-body-content">
                                  <form action="tag" method="post">
                                    <div class="modal-content-form">
                                        <div class="content-left">
                                            <label for="id">ID</label>
                                            <label for="name">Name</label>
                                            <label for="color">Color</label>
                                        </div>
                                        <div class="content-mid">
                                            <div> : </div>
                                            <div> : </div>
                                            <div> : </div>
                                        </div>
                                        <div class="content-right">
                                            <input type="number" required="" name="id" id="editValue" readonly="">
                                            <input type="text" required="" name="name" value="">
                                            <input type="text" required="" name="color" value="">
                                        </div>
                                    </div>
                                    
                                    <div class="modal-footer">
                                        <button class="btn-close-modal" type="">Cancel</button>
                                        <div style="display: inline">
                                            <input name="action" value="edit" type="hidden">
                                            <input class="modal-footer-btn" type="submit" value="Save"  readonly="">
                                        </div>
                                    </div>
                                </form>
                              </div>
                            </div>
                        </div>
                    </div>
                        
                    <div class="body-modal" id="myModalAdd" style="display: none;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-title">
                                    <p>Add tag</p>
                                </div>
                            </div>
                            <div class="modal-body">
                              <div class="modal-body-content">
                                <form action="tag" method="post">
                                    <div class="modal-content-form">
                                        <div class="content-left">
                                            <!--<label for="id">ID</label>-->
                                            <label for="name">Name</label>
                                            <label for="color">Color</label>
                                        </div>
                                        <div class="content-mid">
                                            <!--<div> : </div>-->
                                            <div> : </div>
                                            <div> : </div>
                                        </div>
                                        <div class="content-right">
                                            <input type="text" name="name" value="">
                                            <input type="text" name="color" value="">
                                        </div>
                                    </div>
                                    
                                    <div class="modal-footer">
                                        <div style="display: inline">
                                            <button class="btn-close-modal" type=>Cancel</button>
                                        </div>
                                        
                                        <div style="display: inline">
                                            <input name="action" value="add" type="hidden">
                                            <input class="modal-footer-btn" type="submit" value="Add" readonly="">
                                        </div>
                                    </div>
                                </form>
                              </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            <div class="body-modal-remove" id="myModalRemove" style="display: none;">
                <div class="modal-remove-body">
                    <p>Are you sure to delete<br>this tag?</p>
                </div>
                <div class="modal-btn">
                    <button class="btn-discard" type="">Cancel</button>
                    <form class="modal-btn-form" action=tag>
                        <input type=hidden name=action value=delete>
                        <input type=hidden id="removeValue" name=id>
                        <input class="btn-agree" type=submit value=Yes> 
                    </form>
                </div>
            </div>
                        
            <%! String mess;%>
            <% mess = (String) request.getAttribute("mess");
                if(mess != null){
                    out.print("<div class='body-modal-remove' id='myModalMess' style='display: inline'>"
                            + "<div class='modal-remove-body' style='padding: 30px 50px 0px 50px'>"
                            + "<p>" + mess + "</p></div>"
                            + "<button class='btn-close-mess'>Close</button>"
                            + "</div>");
                }
            %>
    </div>

    <script src="./assets/js/admin_modal.js"></script>
</body>
</html>