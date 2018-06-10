(function () {


    const config = {
        apiKey: "AIzaSyBB8EraCTeD-jveL67aafGjLrYJ1IZk8ws",
        authDomain: "hackaton-project-423f3.firebaseapp.com",
        databaseURL: "https://hackaton-project-423f3.firebaseio.com",
        storageBucket: "hackaton-project-423f3.appspot.com",
    };

    firebase.initializeApp(config);

    const preObject = document.getElementById('object');

    const dbRefObjects = firebase.database().ref().child('COP');

    dbRefObjects.on('value', snap => {
        preObject.innerText = JSON.stringify(snap.val(), null, 3);
    });

    return dbRefObjects;

}());




//(function () {


//    const config = {
//        apiKey: "AIzaSyBB8EraCTeD-jveL67aafGjLrYJ1IZk8ws",
//        authDomain: "hackaton-project-423f3.firebaseapp.com",
//        databaseURL: "https://hackaton-project-423f3.firebaseio.com",
//        //projectId: "hackaton-project-423f3",
//        storageBucket: "hackaton-project-423f3.appspot.com",
//        //messagingSenderId: "362321889984"
//    };


//    firebase.initializeApp(config);

//    const preObject = document.getElementById('object');




//    document.getElementById("btn-getdata").addEventListener("click", function () {
//        var dataName = document.getElementById('data-name').value;
//        var dbRefObject = firebase.database().ref().child(dataName);

//        dbRefObject.on('value', snap => {
//            preObject.innerText = JSON.stringify(snap.val(), null, 3);
//        });

//    });




//}());