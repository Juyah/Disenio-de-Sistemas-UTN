const tipoDocumento = localStorage.getItem("tipoDocumento");
const numeroDocumento = localStorage.getItem("numeroDocumento");


var appRegistrarUsuarioVue = new Vue({
    el: '#RegistrarUsuarioVue',
    data: {
        usuario: '',
        contrasenia: '',
        repeticionContrasenia: ''
    },
    methods: {
        enviarDatos() {
            if (this.contrasenia !== this.repeticionContrasenia) {
                alert("Las contraseñas no coinciden");
                return;
            }
            const solicitud = {
                nombreUsuario: this.usuario,
                contrasenia: this.contrasenia
            };
            fetch("http://localhost:8080/registrar/usuario", {
                method: "POST",
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: JSON.stringify(solicitud)
            }).then(response => {
                switch (response.status) {
                    case 400:
                        alert("contraseña debil")
                        return;
                    case 404:
                        alert("usuario ya registrado")
                        return;
                    case 200:
                        break;
                    default:
                        alert("error desconocido")
                        return;
                }
            })

            if (numeroDocumento) {
                const solicitud = {
                    nombreUsuario: this.usuario,
                    tipoDocumento: tipoDocumento,
                    numeroDocumento: numeroDocumento
                };
                fetch("http://localhost:8080/persona/registrarleUsuario", {
                    method: "POST",
                    headers:
                        {
                            'Content-Type': 'application/json'
                        },
                    body: JSON.stringify(solicitud)}).then(response => {
                        if(response.status === 200){
                            localStorage.removeItem("tipoDocumento");
                            localStorage.removeItem("numeroDocumento");
                            location.href = "../inicio/home.html";
                        } else {
                            alert("error desconocido");
                        }
                    })
            } else {
                localStorage.setItem('usuario', this.usuario);
                location.href = "../registrarPersona/registrarPersona.html";
            }
        },
    }
})