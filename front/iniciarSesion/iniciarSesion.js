localStorage.removeItem('tipoDocumento');
localStorage.removeItem('numeroDocumento');

var appInicirSesionVue = new Vue(
    {
        el: '#InicirSesionVue',
        data: {
            nombreUsuario: "",
            contrasenia: "",
            tipoDeUsuario: "usuarios",
        },
        methods: {
            iniciarSesion: async function () {
                const datos = await this.solicitudInicioSesion({
                    nombreUsuario: this.nombreUsuario,
                    contrasenia: this.contrasenia
                }, "http://localhost:8080/" + this.tipoDeUsuario + "/autenticar")

                if (datos.idSesion === "-1") {
                    alert("Usuario o contraseña invalido");
                } else {
                    if (this.tipoDeUsuario === "usuarios") {
                        localStorage.setItem("idSesion", datos.idSesion);
                        window.location.href = "../inicio/home.html";
                    } else if (this.tipoDeUsuario === "administradores") {
                        localStorage.setItem("idAdmin", datos.idSesion);
                        window.location.href = "../panelAdministrador/panelAdministrador.html";
                    } else if (this.tipoDeUsuario === "voluntarios") {
                        localStorage.setItem("idVoluntario", datos.idSesion);
                        window.location.href = "../panelVoluntario/panelVoluntario.html";
                    } else {
                        alert("Error inesperado");
                    }
                }
            },

            solicitudInicioSesion: async function (unaSolicitud, input) {
                return fetch(input,
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
                        return datos
                    })
            },
        }
    }
)
