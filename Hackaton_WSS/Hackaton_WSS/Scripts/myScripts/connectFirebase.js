function selectAgentes() {


    const config = {
        apiKey: "AIzaSyBB8EraCTeD-jveL67aafGjLrYJ1IZk8ws",
        authDomain: "hackaton-project-423f3.firebaseapp.com",
        databaseURL: "https://hackaton-project-423f3.firebaseio.com",
        storageBucket: "hackaton-project-423f3.appspot.com",
    };

    firebase.initializeApp(config);
    const preObject = document.getElementById('object');
    var dbRefObject;
    var dataName = 'AGENTES';

    dbRefObject = firebase.database().ref("COP").child(dataName);

    dbRefObject.on('value', snap => {
        preObject.innerText = JSON.stringify(snap.val(), null, 3);

    });

    return dbRefObject;

}
