import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
  // 1. Estados para guardar lo que el usuario escribe y posibles errores
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  
  // 2. Herramienta para cambiar de página
  const navigate = useNavigate();

  // 3. La función que se ejecuta al pulsar el botón
  const handleSubmit = async (e) => {
    e.preventDefault(); // Evitamos que la página se recargue
    setError(''); // Limpiamos errores anteriores

    try {
      // Llamamos a la puerta del guardia de seguridad en Java
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }) // Metemos los datos en la cesta
      });

      if (response.ok) {
        // ¡ÉXITO! Java nos devuelve los datos del usuario
        const usuarioData = await response.json();
        console.log("¡Sesión iniciada!", usuarioData);
        
        // Redirigimos al Dashboard
        navigate('/dashboard'); 
      } else {
        // FALLO: Credenciales incorrectas o usuario no verificado
        const errorText = await response.text();
        setError(errorText || 'Error al iniciar sesión');
      }
    } catch (err) {
      setError('Error de conexión con el servidor');
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
              placeholder="tu@correo.com"
              value={email} // Conectado a la memoria de React
              onChange={(e) => setEmail(e.target.value)} // Actualiza la memoria al escribir
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
              placeholder="••••••••"
              value={password} // Conectado a la memoria de React
              onChange={(e) => setPassword(e.target.value)} // Actualiza la memoria al escribir
              required
              className="w-full px-4 py-3 bg-zinc-950 border border-zinc-800 rounded-xl text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 transition-colors duration-200"
            />
          </div>

          <button 
            type="submit"
            className="w-full bg-indigo-600 hover:bg-indigo-500 text-white font-semibold py-3 px-4 rounded-xl transition-all duration-300 transform hover:-translate-y-0.5 active:translate-y-0 shadow-lg shadow-indigo-600/30"
          >
            Iniciar Sesión
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