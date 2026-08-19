import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

async function leerMensajeError(response) {
  try {
    const cuerpo = await response.json();
    if (cuerpo && typeof cuerpo.mensaje === 'string' && cuerpo.mensaje.trim()) {
      return cuerpo.mensaje;
    }
  } catch {
    // Si no viene JSON, usamos el texto genérico de abajo
  }

  if (response.status === 401) {
    return 'Usuario o contraseña incorrectos';
  }

  return 'Error al iniciar sesión';
}

function Login() {
  const [error, setError] = useState('');
  const [enviando, setEnviando] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    // FormData lee lo que hay escrito (o autocompletado) en el HTML.
    // El estado de React a veces se queda vacío al volver de /dashboard
    // porque el navegador rellena los campos sin avisar a onChange.
    const formData = new FormData(e.currentTarget);
    const emailEnviado = String(formData.get('email') || '').trim();
    const passwordEnviado = String(formData.get('password') || '');

    if (!emailEnviado || !passwordEnviado) {
      setError('Introduce el correo y la contraseña');
      return;
    }

    setEnviando(true);

    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email: emailEnviado, password: passwordEnviado })
      });

      if (response.ok) {
        const usuarioData = await response.json();
        console.log("¡Sesión iniciada!", usuarioData);
        navigate('/dashboard');
      } else {
        const mensajeError = await leerMensajeError(response);
        setError(mensajeError);
      }
    } catch (err) {
      setError('Error de conexión con el servidor');
    } finally {
      setEnviando(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-950 p-4 font-sans">
      <div className="max-w-md w-full bg-zinc-900/50 backdrop-blur-xl border border-zinc-800 rounded-2xl p-8 shadow-2xl">
        
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold text-white tracking-tight mb-2">
            Bienvenido de nuevo
          </h1>
          <p className="text-zinc-400 text-sm">
            Introduce tus credenciales para acceder a tu panel
          </p>
        </div>

        {/* Mostrar mensaje de error si existe */}
        {error && (
          <div className="mb-6 p-3 bg-red-500/10 border border-red-500/50 rounded-xl text-red-500 text-sm text-center">
            {error}
          </div>
        )}

        {/* Conectamos el formulario a nuestra nueva función */}
        <form className="space-y-6" onSubmit={handleSubmit}>
          
          <div>
            <label className="block text-sm font-medium text-zinc-300 mb-2">
              Correo electrónico
            </label>
            <input 
              type="email"
              name="email"
              autoComplete="email"
              placeholder="tu@correo.com"
              required
              className="w-full px-4 py-3 bg-zinc-950 border border-zinc-800 rounded-xl text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 transition-colors duration-200"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-zinc-300 mb-2">
              Contraseña
            </label>
            <input 
              type="password"
              name="password"
              autoComplete="current-password"
              placeholder="••••••••"
              required
              className="w-full px-4 py-3 bg-zinc-950 border border-zinc-800 rounded-xl text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 transition-colors duration-200"
            />
          </div>

          <button 
            type="submit"
            disabled={enviando}
            className="w-full bg-indigo-600 hover:bg-indigo-500 disabled:opacity-60 disabled:cursor-not-allowed text-white font-semibold py-3 px-4 rounded-xl transition-all duration-300 transform hover:-translate-y-0.5 active:translate-y-0 shadow-lg shadow-indigo-600/30"
          >
            {enviando ? 'Entrando...' : 'Iniciar Sesión'}
          </button>
        </form>

        <div className="mt-8 pt-6 border-t border-zinc-800 text-center">
          <p className="text-zinc-400 text-sm">
            ¿No tienes una cuenta física?{' '}
            <Link 
              to="/registro" 
              className="text-indigo-400 hover:text-indigo-300 font-medium transition-colors"
            >
              Regístrate aquí
            </Link>
          </p>
        </div>

      </div>
    </div>
  );
}

export default Login;