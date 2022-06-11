const idAdmin = localStorage.getItem("idAdmin");
if(!idAdmin){
    window.location.href = "../inicio/home.html";
}


var appPanelAdministracionVue = new Vue({
    el: '#PanelAdministracionVue',
    data: {
        adminsActuales: [],
        caracteristicasActuales: [],
        calidadFotoActual: '',
        tamanioFotoActual: '',

        calidadesFotos: [],
        tamaniosFotos: [],

        adminsAniadidos: [],
        caracteristicasAniadidas: [],

        calidadElegida: '',
        tamanioElegido: '',
        caracteristicaIngresada: '',
        usuarioIngresado: '',
        contraseniaIngresada: ''
    },
    methods:
        {
            actualizarAdmins: function () {
                var response = {
                    adminNuevo: this.usuarioIngresado,
                    contrasenia: this.contraseniaIngresada
                }
                console.log(JSON.stringify(response))
                this.realizarActualizacion(response, "http://localhost:8080/organizacion/panelAdministracion/actualizarAdministradores")
                this.adminsAniadidos.push(this.usuarioIngresado)
            },
            actualizarCaracteristicas: function () {
                var response = {
                    nuevaCaracteristica: this.caracteristicaIngresada
                }
                console.log(JSON.stringify(response))
                this.realizarActualizacion(response, "http://localhost:8080/organizacion/panelAdministracion/actualizarCaracteristicas")
                this.caracteristicasAniadidas.push(this.caracteristicaIngresada)
            },
            actualizarDetalleFotos: function () {
                var response = {
                    calidadFoto: this.calidadElegida,
                    tamanioFoto: this.tamanioElegido
                }
                console.log(JSON.stringify(response))
                this.realizarActualizacion(response, "http://localhost:8080/organizacion/panelAdministracion/actualizarDetalleFotos")
            },
            realizarActualizacion: function (response, input) {
                fetch(input,
                    {
                        method: "POST",
                        headers:
                            {
                                'Content-Type': 'application/json',
                                "Authorization": idAdmin
                            },
                        body: JSON.stringify(response)
                    }).then(response => {
                    if (response.status !== 200)
                        alert("Algo salio mal!!")
                })
            }
        },
    created() {
        fetch('http://localhost:8080/datos/persona/calidadesFoto')
            .then(response => response.json()).then(json => {
            this.calidadesFotos = json.map(unaCalidad => unaCalidad.toUpperCase());
        })
        fetch('http://localhost:8080/datos/persona/tamanioFotos')
            .then(response => response.json()).then(json => {
            this.tamaniosFotos = json.map(unTamanio => unTamanio.toUpperCase());
        })
        fetch("http://localhost:8080/organizacion/panelAdministracion", {
            headers: {
                "Authorization": idAdmin
            }
        }).then(response => response.json()).then(infoPanel => {
            this.adminsActuales = infoPanel.usuariosAdministradores;
            this.caracteristicasActuales = infoPanel.caracteristicas;
            this.calidadFotoActual = infoPanel.calidadFoto;
            this.tamanioFotoActual = infoPanel.tamanioFoto;
            }
        )

    }
})