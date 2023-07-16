<!-- <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="assets/css/GlobalStyles/GlobalStyles.css">
    <link rel="stylesheet" href="assets/css/userSignOld.css">
</head>

<body> -->
    <div class="userSign">
        <div class="userSign--wrap">
            <div class="userSign--tag">
                <h2>${sessionScope.usersession.getUsername()}</h2>
                <h2>Owned: ${sessionScope.listOwnedItem.size()} / ${sessionScope.allItems}</h2>
                <h2>
                    <img class="coin" src="assets/images/coin__icon.png" />
                    <span id="money">${sessionScope.usersession.getMoney()}</span>
                </h2>
            </div>
        </div>
    </div>
<!-- </body>

</html> -->