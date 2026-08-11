import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Registro(){

  const [registroExitoso, setRegistroExitoso] = useState(false);

  const [userFormData, setUserFormData] = useState({
    email: '',
    password: '',
    nombre: '',
    apellidos: '',
    edad: '',
    alturaCm: '',
    pesoKg: '',
    genero: '',
    nivelActividad: '',
    objetivo: '',
    problemasSalud: ''
  });


  const manejarCambio = (e) => {
    setUserFormData({ ...userFormData, [e.target.name]: e.target.value });
  };

  const validarYEnviar = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/api/usuarios', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(userFormData)
    })
    .then(respuesta => {
      if (!respuesta.ok) {
        throw new Error('El servidor ha devuelto un error');
      }
      return respuesta.json();
    })
    .then(datosDevueltos => {
      console.log("¡Éxito! Java ha respondido:", datosDevueltos);
      setRegistroExitoso(true);
    })
    .catch(error => {
      console.error("Hubo un problema de concexión", error);
      alert("No se pudo conectar con el servidor. Por favor, inténtalo de nuevo más tarde.");
    });
  }

  const inputClass = "w-full px-4 py-3 bg-zinc-950 border border-zinc-800 rounded-xl text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 transition-colors";
  const labelClass = "block text-sm font-medium text-zinc-400 mb-2";
  const sectionTitleClass = "text-lg font-semibold text-indigo-400 border-b border-zinc-800 pb-2 mb-4 mt-8";

  if (registroExitoso) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-zinc-950 p-4 font-sans py-12">
        <div className="max-w-md w-full bg-zinc-900/50 backdrop-blur-xl border border-zinc-800 rounded-2xl p-8 shadow-2xl text-center">
          <div className="w-20 h-20 bg-indigo-500/20 rounded-full flex items-center justify-center mx-auto mb-6 border border-indigo-500/50">
            <span className="text-4xl">✉️</span>
          </div>
          <h2 className="text-2xl font-bold text-white mb-4">¡Verifica tu correo!</h2>
          <p className="text-zinc-400 mb-8">
            Hemos enviado un enlace de confirmación a <span className="text-indigo-400 font-medium">{userFormData.email}</span>. 
            Por favor, haz clic en el enlace para activar tu cuenta y acceder al panel.
          </p>
          <Link to="/login" className="inline-block w-full bg-zinc-800 hover:bg-zinc-700 text-white font-semibold py-3 px-4 rounded-xl transition-colors">
            Volver al inicio de sesión
          </Link>
        </div>
      </div>
    );
  }


  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-950 p-4 font-sans py-12">
      <div className="max-w-2xl w-full bg-zinc-900/50 backdrop-blur-xl border border-zinc-800 rounded-2xl p-8 shadow-2xl">
        
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold text-white mb-2">Crea tu cuenta</h1>
          <p className="text-zinc-400">Regístrate para obtener tu plan físico personalizado</p>
        </div>

        <form onSubmit={validarYEnviar} className="space-y-6">
          
          <h2 className="text-lg font-semibold text-indigo-400 border-b border-zinc-800 pb-2 mb-4">
            Credenciales de acceso
          </h2>
          
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label className={labelClass}>Correo electrónico</label>
              <input type="email" name="email" placeholder="tu@correo.com" value={userFormData.email} onChange={manejarCambio} className={inputClass} required />
            </div>
            <div>
              <label className={labelClass}>Contraseña</label>
              <input type="password" name="password" placeholder="••••••••" value={userFormData.password} onChange={manejarCambio} className={inputClass} required minLength="6" />
            </div>
          </div>

          <h2 className={sectionTitleClass}>
            Perfil Físico
          </h2>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label className={labelClass}>Nombre</label>
              <input type="text" name="nombre" value={userFormData.nombre} onChange={manejarCambio} className={inputClass} required />
            </div>
            <div>
              <label className={labelClass}>Apellidos</label>
              <input type="text" name="apellidos" value={userFormData.apellidos} onChange={manejarCambio} className={inputClass} />
            </div>
            <div>
              <label className={labelClass}>Edad</label>
              <input type="number" name="edad" value={userFormData.edad} onChange={manejarCambio} className={inputClass} required />
            </div>
            <div>
              <label className={labelClass}>Género</label>
              <select name="genero" value={userFormData.genero} onChange={manejarCambio} className={inputClass} required>
                <option value="">Selecciona</option>
                <option value="MASCULINO">Masculino</option>
                <option value="FEMENINO">Femenino</option>
              </select>
            </div>
            <div>
              <label className={labelClass}>Altura (cm)</label>
              <input type="number" name="alturaCm" value={userFormData.alturaCm} onChange={manejarCambio} className={inputClass} required />
            </div>
            <div>
              <label className={labelClass}>Peso (kg)</label>
              <input type="number" name="pesoKg" value={userFormData.pesoKg} onChange={manejarCambio} className={inputClass} required />
            </div>
          </div>

          <div>
            <label className={labelClass}>Nivel de actividad</label>
            <select name="nivelActividad" value={userFormData.nivelActividad} onChange={manejarCambio} className={inputClass} required>
              <option value="">Selecciona tu nivel</option>
              <option value="SEDENTARIO">Sedentario (Poco o ningún ejercicio)</option>
              <option value="LIGERO">Ligero (Ejercicio 1-3 días/semana)</option>
              <option value="MODERADO">Moderado (Ejercicio 3-5 días/semana)</option>
              <option value="INTENSO">Intenso (Ejercicio 6-7 días/semana)</option>
              <option value="ATLETA">Atleta (Ejercicio profesional)</option>
            </select>
          </div>

          <div>
            <label className={labelClass}>Objetivo principal</label>
            <select name="objetivo" value={userFormData.objetivo} onChange={manejarCambio} className={inputClass} required>
              <option value="">¿Qué quieres lograr?</option>
              <option value="BAJAR_PESO">Perder grasa</option>
              <option value="MANTENER">Mantener peso actual</option>
              <option value="AUMENTAR_PESO">Ganar masa muscular</option>
              <option value="DEFINIR_CUERPO">Definición muscular</option>
              <option value="MEJORAR_RENDIMIENTO">Mejorar rendimiento deportivo</option>
            </select>
          </div>

          <div>
            <label className={labelClass}>Problemas de salud (Opcional)</label>
            <input type="text" name="problemasSalud" value={userFormData.problemasSalud} onChange={manejarCambio} className={inputClass} placeholder="Ej: Lesión de rodilla, asma..." />
          </div>

          <button type="submit" className="w-full bg-indigo-600 hover:bg-indigo-500 text-white font-semibold py-4 px-4 rounded-xl transition-all shadow-lg hover:-translate-y-0.5 mt-8">
            Crear cuenta y comenzar
          </button>
        </form>

        <div className="mt-6 text-center">
          <Link to="/login" className="text-zinc-500 hover:text-indigo-400 text-sm transition-colors">
            ← Ya tengo una cuenta, iniciar sesión
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Registro;

