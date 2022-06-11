const tipoDocumento = localStorage.getItem("tipoDocumento");
const numeroDocumento = localStorage.getItem("numeroDocumento");
const idUsuario = localStorage.getItem('idSesion');

var appDarEnAdopcionVue = new Vue({
    el: '#DarEnAdopcionVue',
    data: {
        mascotaElegida: 'elegirMascota',
        mascotas: [{
            nombre: '',
            organizacion: '',
            foto: ''
        }],
        preguntasGenerales: [],
        preguntasOrganizacion: [],
        preguntas: {},
        numeroDocumento: '',
        tipoDocumento: ''
    },
    methods: {
        cambioMascota(){
            this.preguntasOrganizacion = [];
            fetch("http://localhost:8080/organizacion/" + this.mascotaElegida.organizacion.toString() + "/preguntasDarEnAdopcion")
                .then(response => response.json())
                .then(preguntas =>
                    preguntas.forEach(pregunta =>
                        this.preguntasOrganizacion.push({name: pregunta, value: ""})));
        },
        enviarDatos(){
            const carPregsGrls = this.preguntasGenerales.map(caracteristica => caracteristica.name);
            const carRtasGrls = this.preguntasGenerales.map(caracteristica => caracteristica.value);
            carPregsGrls.forEach((key, i) => this.preguntas[key] = carRtasGrls[i]);

            const carPregsEsp = this.preguntasOrganizacion.map(caracteristica => caracteristica.name);
            const carRtasEsp = this.preguntasOrganizacion.map(caracteristica => caracteristica.value);
            carPregsEsp.forEach((key, i) => this.preguntas[key] = carRtasEsp[i]);
            const solicitud = {
                tipoDocumento: this.tipoDocumento,
                numeroDocumento: this.numeroDocumento,
                nombreMascota: this.mascotaElegida.nombre,
                preguntasRespuestas: this.preguntas
            };
            console.log(solicitud);
            fetch("http://localhost:8080/persona/publicarMascotaEnAdopcion", {
                method: "POST",
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: JSON.stringify(solicitud)
            }).then(response => {
                if (response.status === 201) {
                    location.href = "../inicio/home.html";
                } else {
                    alert("Hubo un error en el API")
                }
            })
        },
        async obtenerDocumento(){
            if (idUsuario) {
                await fetch('http://localhost:8080/persona/documento', {headers: {"Authorization": idUsuario}}).then(response => response.json()).then(json => {
                    this.numeroDocumento = json.numero;
                    this.tipoDocumento = json.tipo;
                })
            } else if (numeroDocumento) {
                this.numeroDocumento = numeroDocumento;
                this.tipoDocumento = tipoDocumento;
            } else {
                location.href = "../registrarPersona/registrarPersona.html";
            }
        }
    },
    async created() {
        await this.obtenerDocumento();
        fetch('http://localhost:8080/duenio/mascotas', {
            headers:
                {
                    "numero": this.numeroDocumento,
                    "tipo": this.tipoDocumento
                },
        })
            .then(response => response.json())
            .then(mascotas => {
                this.mascotas = mascotas;
            })
        fetch('http://localhost:8080/organizacion/preguntasGenerales/darEnAdopcion')
            .then(response => response.json())
            .then(preguntas => preguntas.forEach(pregunta =>
                this.preguntasGenerales.push({name: pregunta, value: ""})))

    }
})