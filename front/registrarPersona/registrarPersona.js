const usuarioActual = localStorage.getItem("usuario");

let RegistrarPersona = new Vue({
    el: '#RegistrarPersonaVue',
    data: {
        nombre: '',
        apellido: '',
        fechaDeNacimiento: '',
        numeroDocumento: '',
        tipoDocumentoSeleccionado: '',
        longitud: '',
        latitud: '',
        localidad: '',
        calle: '',
        altura: '',
        radioHogares: '',
        telefono: '',
        email: '',
        whatsappPref: false,
        smsPref: false,
        emailPref: false,
        tipoAnimalSeleccionado: '',
        sexoAnimalSeleccionado: '',
        tamanioAnimalSeleccionado: '',
        nuevoContacto: {
            nombre: '',
            apellido: '',
            telefono: '',
            email: '',
            whatsappPref: false,
            smsPref: false,
            emailPref: false,
        },
        contactosAniadidos: [],
        tiposDocumento: [],
        tiposAnimal: [],
        sexosAnimal: [],
        tamaniosAnimal: []
    },
    methods: {
        async consultarDireccion()
        {
            console.log(this.localidad);
            console.log(this.calle);
            console.log(this.altura);

            var url = new URL('https://apis.datos.gob.ar/georef/api/direcciones')

            var params = {direccion: this.calle + " al " + this.altura,localidad:this.localidad}
            url.search = new URLSearchParams(params).toString();
            console.log(url);

            await fetch(url).then(response => response.json()).then(datos => {
                console.log(datos);
                this.latitud = datos.direcciones[0].ubicacion.lat;
                this.longitud = datos.direcciones[0].ubicacion.lon;
                console.log(this.latitud);
                console.log(this.longitud);
            })
        },
        enviarDatos: function () {
            let usuario = '';
            this.consultarDireccion();
            if (usuarioActual) {
                usuario = usuarioActual;
            } else {
                usuario = '';
                localStorage.setItem("tipoDocumento", this.tipoDocumentoSeleccionado);
                localStorage.setItem("numeroDocumento", this.numeroDocumento);
            }
            let mediosDeComunicacionPersonal = [];
            if (this.whatsappPref) {
                mediosDeComunicacionPersonal.push('WhatsApp');
            }
            if (this.smsPref) {
                mediosDeComunicacionPersonal.push('SMS');
            }
            if (this.emailPref) {
                mediosDeComunicacionPersonal.push('Email');
            }

            console.log(this.contactosAniadidos);
            const solicitud = {
                nombreUsuario: usuario,
                contactoPersonal: {
                    nombre: this.nombre,
                    apellido: this.apellido,
                    telefono: this.telefono,
                    email: this.email,
                    mediosComunicacion: mediosDeComunicacionPersonal
                },
                fechaNacimiento: this.fechaDeNacimiento,
                documento: {
                    tipo: this.tipoDocumentoSeleccionado,
                    numero: this.numeroDocumento
                },
                latitud: this.latitud,
                longitud: this.longitud,
                unosContactos: this.contactosAniadidos,
                radioHogares: this.radioHogares,
                preferencia: {
                    tipoAnimal: this.tipoAnimalSeleccionado,
                    sexoAnimal: this.sexoAnimalSeleccionado,
                    tamanioAnimal: this.tamanioAnimalSeleccionado
                }
            }
            console.log(solicitud);
            fetch("http://localhost:8080/usuario/registroPersona", {
                method: "POST",
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: JSON.stringify(solicitud)}).then(response => {
                if(response.status === 200){
                    localStorage.removeItem('usuario');
                    location.href = "../inicio/home.html";
                } else {
                    alert("error desconocido");
                }
            })


        },
        actualizarContactos: function () {
            let mediosDeComunicacionContacto = [];
            if(this.nuevoContacto.whatsappPref){
                mediosDeComunicacionContacto.push('WhatsApp');
            }
            if(this.nuevoContacto.smsPref){
                mediosDeComunicacionContacto.push('SMS');
            }
            if(this.nuevoContacto.emailPref){
                mediosDeComunicacionContacto.push('Email');
            }

            let nuevoContacto = {
                nombre: this.nuevoContacto.nombre,
                apellido: this.nuevoContacto.apellido,
                telefono: this.nuevoContacto.telefono,
                email: this.nuevoContacto.email,
                mediosComunicacion: mediosDeComunicacionContacto
            }
            this.contactosAniadidos.push(nuevoContacto);
            this.nuevoContacto = {
                nombre: '',
                apellido: '',
                telefono: '',
                email: '',
                whatsappPref: false,
                smsPref: false,
                emailPref: false,
            }
        }
    },
    created() {
        fetch('http://localhost:8080/datos/persona/tipoDocumento')
            .then(response => response.json()).then(json => {
            this.tiposDocumento = json;
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
    }
})
