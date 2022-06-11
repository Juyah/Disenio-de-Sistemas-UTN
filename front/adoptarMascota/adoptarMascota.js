const tipoDocumento = localStorage.getItem("tipoDocumento");
const numeroDocumento = localStorage.getItem("numeroDocumento");
const idUsuario = localStorage.getItem('idSesion');

var appAdoptarMascotaVue = new Vue({
    el: '#AdoptarMascotaVue',
    data: {
        numeroDocumentoAdoptante: '',
        tipoDocumentoAdoptante: '',
        publicaciones: [],
        publicacionElegida: {},
        organizaciones: [],
        orgElegida: 'info'
    },
    methods: {
        cambioOrg() {
            this.publicaciones = [];
            fetch("http://localhost:8080/organizacion/" + this.orgElegida.toString() + "/publicacionesMascotaAdopcion")
                .then(response => response.json())
                .then(unasPublicaciones =>
                    unasPublicaciones.forEach(publicacion =>
                        this.publicaciones.push(publicacion)));
            console.log(this.publicaciones);
        },
        enviarDatos(publicacion) {
            const solicitud = {
                numeroDocDuenio: publicacion.numeroDocDuenio,
                tipoDocumentoDuenio: publicacion.tipoDocDuenio,
                nombreMascota: publicacion.nombreMascota,
                numeroDocAdoptante: this.numeroDocumentoAdoptante,
                tipoDocumentoAdoptante: this.tipoDocumentoAdoptante
            };
            console.log(solicitud);
            fetch("http://localhost:8080/adoptante/adoptarMascotaPublicacion", {
                method: "POST",
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: JSON.stringify(solicitud)
            }).then(response => {
                if (response.status === 200) {
                    location.href = "../inicio/home.html";
                } else {
                    alert("Hubo un error en el API")
                }
            })
        },
    },
    created() {
        fetch('http://localhost:8080/organizaciones/nombres')
            .then(response => response.json()).then(json => {
            this.organizaciones = json;
        })
        if (idUsuario) {
            fetch('http://localhost:8080/persona/documento', {headers: {"Authorization": idUsuario}}).then(response => response.json()).then(json => {
                this.numeroDocumentoAdoptante = json.numero;
                this.tipoDocumentoAdoptante = json.tipo;
            })
        } else if (numeroDocumento) {
            this.numeroDocumentoAdoptante = numeroDocumento;
            this.tipoDocumentoAdoptante = tipoDocumento;
        } else {
            location.href = "../registrarPersona/registrarPersona.html";
        }

    }
})