
    <ul class="menu__list">
        <li class="">
            <form action="simon" method="POST">
                <input class="menu__list--item ${sessionScope.MenuAction eq "Simon"?"menu--active":""}" type="submit" name="MenuAction" value="Simon">
            </form> 
        </li>
        <li class="">
            <form action="time" method="POST">
                <input class="menu__list--item ${sessionScope.MenuAction eq "Timer"?"menu--active":""}" type="submit" name="MenuAction"  value="Timer">
            </form>
        </li>
        <li class="">
            <form action="store" method="POST">
                <input class="menu__list--item ${sessionScope.MenuAction eq "Store"?"menu--active":""}" type="submit" name="MenuAction"  value="Store">
            </form>
        </li>
        <li class="">
            <form action="overview" method="POST">
                <input class="menu__list--item ${sessionScope.MenuAction eq "Overview"?"menu--active":""}" type="submit" name="MenuAction"  value="Overview">
            </form>
        </li>
        <li class="">
            <form action="settings" method="POST">
                <input class="menu__list--item ${sessionScope.MenuAction eq "Settings"?"menu--active":""}" type="submit" name="MenuAction"  value="Settings">
            </form>
        </li>
    </ul>