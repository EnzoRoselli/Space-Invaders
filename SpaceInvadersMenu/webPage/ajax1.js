console.log('funca');
function ajaxGetJson(){
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){

        if(request.readyState == 4 && request.status == 200){
            var datos = request.responseText;
            console.log('adentro');
            console.log(datos);
        }
    }
    request.open("GET","players.json",true);
    request.send();
}