//TODO: Actualizar los enlaces a la version deploy del cliente pesado!
localStorage.removeItem('tipoDocumento');
localStorage.removeItem('numeroDocumento');

function solicitudInicioSesion(unaSolicitud) {
    return fetch("../usuarios/autenticar",
        {
            method: "POST",
            headers:
                {
                    'Content-Type': 'application/json'
                },
            body: JSON.stringify(unaSolicitud)
        })
        .then(response => response.json())
        .then(datos => {
            return datos;
        })
}

function realizarSolicitud()
{
    let tipoDeUsuario = document.getElementById("tipoDeUsuario").value;
    let unaSolicitud = {
        nombreUsuario: document.getElementById("nombreUsuario").value,
        contrasenia: document.getElementById("contrasenia").value
    }
    solicitudInicioSesion(unaSolicitud).then(datos => {
        if(datos.idSesion === "-1" || datos.idSesion == undefined)
            alert("Usuario o contraseña invalido");
        else
        {
            switch (tipoDeUsuario) {
                case "usuarios":
                    localStorage.setItem("idSesion", datos.idSesion);
                    window.location.href = "../home";
                    break;
                case "administradores":
                    localStorage.setItem("idAdmin", datos.idSesion);
                    window.location.href = "../panelAdministrador/panelAdministrador.html";
                    break;
                case "voluntarios":
                    localStorage.setItem("idVoluntario", datos.idSesion);
                    window.location.href = "../panelVoluntario/panelVoluntario.html";
                    break;
                default:
                    alert("Error inesperado");
                    break;
            }
        }
    });
}
