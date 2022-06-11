//TODO: Actualizar los enlaces a la version deploy del cliente pesado!

const idUsuario = localStorage.getItem('idSesion');
const idAdmin = localStorage.getItem('idAdmin');
const idVol = localStorage.getItem('idVoluntario');

if(idUsuario || idAdmin || idVol){
    document.getElementById("botonIniciarSesion").style.display = "none";
    document.getElementById("botonRegistrarse").style.display = "none";
    document.getElementById("elementoCerrarSesion").style.display = "block";
}

function redireccionamiento(input){
    if(idUsuario !== null){
        location.href=input
    } else {
        location.href="../registrarPersona/registrarPersona.html"
    }
}

function cerrarSesion()
{
    realizarDesconexion();
    localStorage.removeItem('idSesion');
    localStorage.removeItem('idAdmin');
    localStorage.removeItem('idVoluntario');
    location.href = "../../home"
}

function desconectar(auth) {
    fetch('http://localhost:8080/usuarios/desconectar',
        {headers: {"Authorization": auth}}).then(response => {
        if (response.status !== 200) alert("Algo salio mal!!")
    })
}

function realizarDesconexion()
{
    if (idUsuario) {
        this.desconectar(idUsuario);
    }
    if (idAdmin) {
        this.desconectar(idAdmin);
    }
    if (idVol) {
        this.desconectar(idVol);
    }
}
