<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Not Enough Money</title>
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
                            <h1>Not enough money!</h1>
                        </div>

                        <div class="confirm--display--body">
                            <div class="confirm--display--body--header">
                                <div class="w-45"></div>
                                <h3 class="confirm--display--body--header--title">${simonDTO.name}</h3>
                                <div class="confirm--display--body--header--body w-45">
                                    <h3>${simonDTO.price}</h3>
                                    <img src="assets/images/coin__icon.png" class="coin" />
                                </div>
                            </div>
                            <img class="f-width confirm--display--body--image" src="${simonDTO.itemLink}"/>
                        </div>

                        <div class="confirm--display--footer">
                            <h2>You Need ${needCoin} Coin More</h2>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <script>
            const mainElement = document.querySelector(".main");
            mainElement.addEventListener("click", () => {
                window.location.href = "store";
            });
        </script>
    </body>

</html>