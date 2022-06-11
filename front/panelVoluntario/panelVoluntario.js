const idVoluntario = localStorage.getItem("idVoluntario");
if (!idVoluntario) {
    window.location.href = "../inicio/home.html";
}


var appPanelVoluntarioVue = new Vue({
    el: '#PanelVoluntarioVue',
    data: {
        publicacionesMascotaEncontrada: [],
        publicacionesEnAdopcion: [],

    },
    methods:
        {
            aprobar(idPublicacion) {
                const solicitud = {id: idPublicacion}
                fetch("http://localhost:8080/organizacion/publicaciones/aceptar",
                    {
                        method: "POST",
                        headers:
                            {
                                'Content-Type': 'application/json',
                                "Authorization": idVoluntario
                            },
                        body: JSON.stringify(solicitud)
                    }).then(response => {
                    if (response.status === 200){
                        this.publicacionesMascotaEncontrada = this.publicacionesMascotaEncontrada.filter(publicacion => publicacion.id !== idPublicacion);
                        this.publicacionesEnAdopcion = this.publicacionesEnAdopcion.filter(publicacion => publicacion.id !== idPublicacion);
                    }
                    else{
                        alert("Algo salio mal!!")
                    }
                })
            }
        },
    created() {
        fetch("http://localhost:8080/organizacion/panelVoluntario", {
            headers: {
                "Authorization": idVoluntario
            }
        }).then(response => response.json()).then(infoPanel => {
            this.publicacionesMascotaEncontrada = infoPanel.publicacionesMascotaEncontrada;
            this.publicacionesEnAdopcion = infoPanel.publicacionesMascotaEnAdopcion;
        })
    }
})