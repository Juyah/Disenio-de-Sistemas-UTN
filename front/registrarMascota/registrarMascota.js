const tipoDocumento = localStorage.getItem("tipoDocumento");
const numeroDocumento = localStorage.getItem("numeroDocumento");
const idUsuario = localStorage.getItem('idSesion');

var appRegistrarMascotaVue = new Vue({
    el: '#RegistrarMascotaVue',
    data: {
        orgElegida: 'info',
        organizaciones: [],
        caracteristicas: [],
        nombre: '',
        apodo: '',
        edad: '',
        tipoAnimalSeleccionado: '',
        sexoAnimalSeleccionado: '',
        tamanioAnimalSeleccionado: '',
        descripcionFisica: '',
        fotos: [],
        tiposAnimal: [],
        sexosAnimal: [],
        tamaniosAnimal: [],
        numeroDocumentoDuenio: '',
        tipoDocumentoDuenio: ''
    },
    methods: {
        cambioOrg() {
            this.caracteristicas = [];
            fetch("http://localhost:8080/organizacion/" + this.orgElegida.toString() + "/caracteristicas")
                .then(response => response.json())
                .then(unasCaracteristicas =>
                    unasCaracteristicas.forEach(caracteristica =>
                        this.caracteristicas.push({name: caracteristica, value: ""})));
        },
        async guardarDatos() {
            const solicitud = {
                organizacion: this.orgElegida,
                numeroDocumento: this.numeroDocumentoDuenio,
                tipoDocumento: this.tipoDocumentoDuenio,
                nombre: this.nombre,
                tipoAnimal: this.tipoAnimalSeleccionado,
                apodo: this.apodo,
                edad: this.edad,
                sexo: this.sexoAnimalSeleccionado,
                tamanio: this.tamanioAnimalSeleccionado,
                descripcionFisica: this.descripcionFisica,
                fotos: this.fotos,
                caracteristicas: {}
            };
            const carPregs = this.caracteristicas.map(caracteristica => caracteristica.name);
            const carRtas = this.caracteristicas.map(caracteristica => caracteristica.value);
            carPregs.forEach((key, i) => solicitud.caracteristicas[key] = carRtas[i]);
            return solicitud;
        },
        async enviarDatos() {
            const solicitud = await this.guardarDatos();
            fetch("http://localhost:8080/registrar/mascota", {
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
        agregarFotos: function (event) {
            var files = event.target.files
            Array.from(files).forEach(unFile => this.getBase64(unFile).then(foto => {
                this.fotos.push(foto)
            }))
        },
        getBase64: function (file) {
            return new Promise((resolve, reject) => {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    resolve(reader.result)
                };
                reader.onerror = function (error) {
                    reject('Error: ', error);
                }
            })
        }
    },
    created() {
        fetch('http://localhost:8080/organizaciones/nombres')
            .then(response => response.json()).then(json => {
            this.organizaciones = json;
        })
        fetch('http://localhost:8080/datos/mascota/animal')
            .then(response => response.json()).then(json => {
            this.tiposAnimal = json;
        })
        fetch('http://localhost:8080/datos/mascota/sexo')
            .then(response => response.json()).then(json => {
            this.sexosAnimal = json;
        })
        fetch('http://localhost:8080/datos/mascota/tamanio')
            .then(response => response.json()).then(json => {
            this.tamaniosAnimal = json;
        })
        if (idUsuario) {
            fetch('http://localhost:8080/persona/documento', {headers: {"Authorization": idUsuario}}).then(response => response.json()).then(json => {
                this.numeroDocumentoDuenio = json.numero;
                this.tipoDocumentoDuenio = json.tipo;
            })
        } else if (numeroDocumento) {
            this.numeroDocumentoDuenio = numeroDocumento;
            this.tipoDocumentoDuenio = tipoDocumento;
        } else {
            location.href = "../registrarPersona/registrarPersona.html";
        }
    }
})