var form = document.getElementById("form-id");
var dateFinal = "";

document.getElementById("confirm").addEventListener("click", function () {
    form.submit();
});


function goBack() {
    window.history.back();
}

function toggleGoAhead() {
    document.getElementById("notAvail").style.display = "none";
    document.getElementById("goAhead").style.display = "block";
}

function toggleNotAvail() {
    document.getElementById("notAvail").style.display = "block";
    document.getElementById("goAhead").style.display = "none";
}
function removeNOtAvail() {
    document.getElementById("notAvail").style.display = "none";
}
function removeNOtAvailAndGoAhead() {
    document.getElementById("notAvail").style.display = "none";
    document.getElementById("goAhead").style.display = "none";
}
function removeCalendar() {
    document.getElementById("customisedCalendar").style.display = "none";
    document.getElementById("datePicked").style.display = "block";

    var e = document.getElementById("ddlViewBy");
    var numOfPersons = e.options[e.selectedIndex].value;
    form.elements["numOfPersonsSel"].value = numOfPersons;
    form.elements["datePickedVal"].value = dateFinal;

    console.log(form.elements["numOfPersonsSel"].value);
    console.log(form.elements["datePickedVal"].value);

    document.getElementById("numOfPersonsSelected").innerHTML = numOfPersons + " person(s)";

}









document.getElementById("goAhead").addEventListener("click", removeCalendar);
var currentMsg = document.getElementById('landing_field').innerHTML;
var previousMsg = currentMsg;
var previousMonth = "*";
var currentMonth = "*";
var reservedDatesSplit = document.getElementById('reservedDates').innerHTML.split(',');
var daysOffSplit = document.getElementById('daysOff').innerHTML.split(',');

var reservedDatesLongSplit = [];

for (var i = 0; i < reservedDatesSplit.length; ++i) {
    reservedDatesLongSplit[i] = (new Date(reservedDatesSplit[i])).toString().substring(0, 15);
    console.log(reservedDatesLongSplit[i]);
}



function checkDatePicked() {
    currentMsg = document.getElementById('landing_field').innerHTML;
    if (currentMsg !== previousMsg) {
        previousMsg = currentMsg;
        let msgSplit = currentMsg.split(" ");

        if (msgSplit.length >= 4) {

            let weekDay = msgSplit[0];
            var myDate = new Date(currentMsg);
            let myDateISO = myDate.toISOString().toString();
            let myDateISOSplit = myDateISO.split("T");
            console.log(myDateISOSplit[0]);
            console.log("napja" + weekDay);

            document.getElementById('datePickedText').innerHTML = "Date Selected: " + myDateISOSplit[0];
            dateFinal = myDateISOSplit[0];

            for (i = 0; i < daysOffSplit.length; ++i) {
                if (daysOffSplit[i] === weekDay) {
                    console.log("dayoff");
                    toggleNotAvail();
                    setTimeout(removeNOtAvail, 2500);
                    return;
                }
            }

            for (i = 0; i < reservedDatesSplit.length; ++i) {
                if (reservedDatesSplit[i] === myDateISOSplit[0]) {
                    toggleNotAvail();
                    setTimeout(removeNOtAvail, 2500);
                    return;
                }
            }

            toggleGoAhead();
        }

    }
}


function checkFields() {

    currentMonth = document.getElementsByClassName('vcal-header__label')[0].innerHTML;

    if (currentMonth !== previousMonth) {
        removeNOtAvailAndGoAhead();
        previousMonth = currentMonth;

        let parentDOM = document.getElementById("v-cal");

        let monthlyCal = parentDOM.getElementsByClassName("vcal-date vcal-date--active");
        let tmp = "";

        for (i = 0; i < monthlyCal.length; ++i) {
            tmp = (monthlyCal[i].dataset.calendarDate).toString().substring(0, 15);


            for (j = 0; j < reservedDatesLongSplit.length; ++j) {
                if (reservedDatesLongSplit[j] === tmp) {

                    monthlyCal[i].style.backgroundColor = "red";
                    //	monthlyCal[i].setAttribute("class", "vcal-date vcal-date--disabled");
                }

            }
        }


    }
}

var xxx = setInterval(checkDatePicked, 500);
var xxxx = setInterval(checkFields, 400);

