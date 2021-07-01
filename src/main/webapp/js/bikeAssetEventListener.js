$(document).ready(function () {
    console.log("Jquery Loaded");
    //Button -> Event => methodName
    $("#submitBtn").on('click', getAsset); // simplified 
});

//let submitButton = document.querySelector("#submitBtn");
//submitButton.addEventListener('click', getAsset );
function getAsset() {
    let url = "/motorcycleapp/v1/auth/bike/asset";
    axios.get(url).then(res => {
        let asset = res.data;
        console.log(asset);
        $("#asset").html("Rs." + asset + "/-");
    })
}
