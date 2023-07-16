<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Confirm</title>
        <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
        <link rel="stylesheet" href="assets/css/GlobalStyles/grid.css">
        <link rel="stylesheet" href="assets/css/confirm.css">
    </head>

    <body>
        <div class="main">
            <div class="confirm">
                <div class="confirm--background">
                    
                    <img src="assets/images/Simon_Point.gif" class="w-20 confirm--img"/>
                </div>

                <div class="confirm--display">
                    <div class="confirm--display--wrap">
                        <div class="confirm--display--header">
                            <h1>Wanna Buy?</h1>
                        </div>

                        <div class="confirm--display--body">
                            <div class="confirm--display--body--header">
                                <div class="w-45"></div>
                                <h3 class="confirm--display--body--header--title">${simonItem.name}</h3>
                                <div class="confirm--display--body--header--body w-45">
                                    <h3>${simonItem.price}</h3>
                                    <img src="assets/images/coin__icon.png" class="coin" />
                                </div>
                            </div>
                            <img class="f-width confirm--display--body--image" src="${simonItem.itemLink}"/>
                        </div>

                        <div class="confirm--display--footer">


                            <form action="ConfirmController" method="POST">
                                <input type="hidden" name="itemID" value="${simonItem.id}"/>
                                <input type="hidden" name="itemName" value="${simonItem.name}"/>
                                <input type="hidden" name="itemImage" value="${simonItem.itemLink}"/>
                                <input type="hidden" name="itemPrice" value="${simonItem.price}"/>
                                <input type="hidden" name=""/>
                                <input class="yes-btn" type="submit" value="YES" name="confirm"/>
                                <input class="no-btn" type="submit" value="NO" name="confirm"/>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>

</html>