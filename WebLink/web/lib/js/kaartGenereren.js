/**
 * Created by Renzie on 18/05/2016.
 */

$(document).ready(function () {
    //maakKaarten();
    run_query();
});

var maakKaarten = function () {
    

};

function run_query() {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null) {
        alert("This browser does not support HTTP Request");
        return;
    }
}// end if