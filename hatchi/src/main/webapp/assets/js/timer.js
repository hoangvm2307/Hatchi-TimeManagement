var request;
var running = false;
var timerWorker;


function increaseTime() {
    var url = "time?action=increase";

    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }

    try {
        request.onreadystatechange = renderTimeSetAndMoney;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}

function decreaseTime() {
    var url = "time?action=decrease";

    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }

    try {
        request.onreadystatechange = renderTimeSetAndMoney;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}


function getRemainingTime() {
    var url = "time?action=gettime";
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }

    try {
        request.onreadystatechange = renderTimeRemaining;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}

function startClock() {
    if (!running) {
        var selections = document.getElementById("tagname");
        var tagName = selections.options[selections.selectedIndex].text;
        var url = "database?action=start&tagname=" + tagName;
        
        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }

        try {
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }
        
        url = "time?action=start";

        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }

        try {
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }

        timerWorker = new Worker('assets/js/timerWorker.js');

        timerWorker.onmessage = getRemainingTime;
    }
}

function stopClock() {
    if (running) {
        running = false;
        var url = "database?action=stop";
        
        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }

        try {
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }
        
        var url = "time?action=stop";
        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }

        try {
            request.onreadystatechange = renderTimeSetAndMoney;
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }
        timerWorker.terminate();
    }
}

function insertIntoDatabase() {
    if (!running) {
        running = true;
        var selections = document.getElementById("tagname");
        var tagName = selections.options[selections.selectedIndex].text;
        url = "database?action=insert&tagname=" + tagName;

        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        try {
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }
    }
}

function updateDatabase() {
    if (running) {
        var selections = document.getElementById("tagname");
        var tagName = selections.options[selections.selectedIndex].text;
        url = "database?action=update&tagname=" + tagName;

        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        try {
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }
    }
}

function renderTimeSetAndMoney() {
    if (request.readyState === 4) {
        var val = request.responseText;
        var parser = new DOMParser();
        var doc = parser.parseFromString(val, 'text/html');
        document.getElementById('timer').innerHTML = doc.getElementById('timer').innerHTML;
        document.getElementById('coinamount').innerHTML = doc.getElementById('coin').innerHTML;
        if (document.getElementById('money').innerHTML !== doc.getElementById('money').innerHTML) {
            document.getElementById('money').innerHTML = doc.getElementById('money').innerHTML;
        }
    }
}

function renderTimeRemaining() {
    if (request.readyState === 4) {
        var val = request.responseText;
        var parser = new DOMParser();
        var doc = parser.parseFromString(val, 'text/html');
        var timerString = doc.getElementById('timer').innerHTML;
        if (timerString === '00:00') {
            updateDatabase();
            stopClock();
        } else 
        document.getElementById('timer').innerHTML = timerString;
    }
}