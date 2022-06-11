const urlParams = new URLSearchParams(window.location.search);
let myParam = urlParams.get('id');

if (myParam !== "null") {
    document.getElementById("conChapita").style.display = "none";
    document.getElementById("botonConChapita").style.display = "none";
} else {
    myParam = null;
}

const tipoDocumento = localStorage.getItem("tipoDocumento");
const numeroDocumento = localStorage.getItem("numeroDocumento");
const idUsuario = localStorage.getItem('idSesion');


var appBuscarHogarTransitoVue = new Vue({
    el: '#BuscarHogarTransitoVue',
    data: {
        tipoAnimalSeleccionado: '',
        tamanioAnimalSeleccionado: '',
        tiposAnimal: [],
        tamaniosAnimal: [],
        tipoDocumento: '',
        numeroDocumento: '',
        hogares: []
    },
    methods: {
        enviarDatos: function() {
            if (myParam) {
                solicitud = {
                    tipoDocRescatista: this.tipoDocumento,
                    numDocRescatista: this.numeroDocumento,
                    idMascota: myParam
                }
                input = "http://localhost:8080/rescatista/HogarTransitoChapita"
            } else {
                solicitud = {
                    tipoDocRescatista: this.tipoDocumento,
                    numDocRescatista: this.numeroDocumento,
                    tamanio: this.tamanioAnimalSeleccionado,
                    tipo: this.tipoAnimalSeleccionado
                }
                input = "http://localhost:8080/rescatista/HogarTransitoSinChapita"
            }

            console.log(solicitud);

            fetch(input, {
                method: "POST",
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: JSON.stringify(solicitud)
            }).then(response => {
                if (response.status === 200) {
                } else {
                    alert("Hubo un error en el API")
                }
                let respuesta = response.json().then(hogares => {
                    this.hogares = hogares;
                });
            })
        },
        async obtenerDocumento() {
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
        },
        async obtenerHogares() {
            var solicitud = {
                tipoDocRescatista: this.tipoDocumento,
                numDocRescatista: this.numeroDocumento,
                idMascota: myParam
            }
            var input = "http://localhost:8080/rescatista/HogarTransitoChapita"

            await fetch(input, {
                method: "POST",
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: JSON.stringify(solicitud)
            }).then(response => response.json()).then(hogares => {
                this.hogares = hogares;
            })
        },
        test(){
            console.log(this.hogares)
        }
    },
    async created() {
        fetch('http://localhost:8080/datos/mascota/animal')
            .then(response => response.json()).then(json => {
            this.tiposAnimal = json;
        })
        fetch('http://localhost:8080/datos/mascota/tamanio')
            .then(response => response.json()).then(json => {
            this.tamaniosAnimal = json;
        })
        await this.obtenerDocumento();
        if (myParam)
            await this.obtenerHogares();

        console.log(this.hogares)
    }
})