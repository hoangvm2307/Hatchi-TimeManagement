<div class="menu">
        <ul class="menu__list">
            <%! String userClassName;%>
            <% userClassName = (String) request.getAttribute("userClassName");
                if(userClassName == null) userClassName = "menu__list--item";
                out.print("<li class='" + userClassName + "'>");
            %>
                <form action="login" method="POST">
                    <button onclick="openModalAdminMenu(0)" type="submit">User Accounts</button>
                    <input name="action" value="edit" type="hidden">
                </form>
            </li>
            
            <%! String itemClassName;%>
            <% itemClassName = (String) request.getAttribute("itemClassName");
                if(itemClassName == null) itemClassName = "menu__list--item";
                out.print("<li class='" + itemClassName + "'>");
            %>
                <form action="item" method="POST">
                    <button onclick="openModalAdminMenu(1)" type="submit">Store</button>
                    <input name="action" value="list" type="hidden">
                </form>
            </li>
            
            <%! String taglassName;%>
            <% taglassName = (String) request.getAttribute("tagClassName");
                if(taglassName == null) taglassName = "menu__list--item";
                out.print("<li class='" + taglassName + "'>");
            %>
                <form action="tag" method="POST">
                    <button onclick="openModalAdminMenu(2)" type="submit" >Tag</button>
                    <input name="action" value="list" type="hidden">
                </form>
            </li>
        </ul>
        <div class="btn-log-out">
            <form action="login">
                <input type="image" src="./assets/images/icon-logOut.png" 
                    onMouseOver="this.src='./assets/images/icon-logOut-hover.png'" 
                    onMouseOut="this.src='./assets/images/icon-logOut.png'">
            </form>
            <p>Log out</p>
        </div>
</div>