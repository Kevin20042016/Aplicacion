import { Link } from 'react-router-dom';

function Dashboard() {
  return (
    // Fondo general de la app
    <div className="min-h-screen bg-zinc-950 font-sans text-zinc-100 p-4 md:p-8">
      
      {/* Contenedor principal centrado con un ancho máximo */}
      <div className="max-w-6xl mx-auto space-y-8">
        
        {/* Cabecera del Dashboard */}
        <header className="flex flex-col md:flex-row md:items-center justify-between gap-4 pb-6 border-b border-zinc-800">
          <div>
            <h1 className="text-3xl font-bold text-white">¡Hola, Kevin! 👋</h1>
            <p className="text-zinc-400 mt-1">Aquí tienes el resumen de tu plan para hoy.</p>
          </div>
          <Link to="/login" className="px-4 py-2 bg-zinc-900 border border-zinc-800 hover:bg-zinc-800 rounded-lg text-sm transition-colors">
            Cerrar Sesión
          </Link>
        </header>

        {/* Grid de métricas principales (Tarjetas superiores) */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          
          {/* Tarjeta 1 */}
          <div className="bg-zinc-900/50 border border-zinc-800 p-6 rounded-2xl backdrop-blur-sm">
            <h3 className="text-zinc-400 text-sm font-medium mb-2">Objetivo Diario</h3>
            <div className="flex items-end gap-2">
              <span className="text-4xl font-bold text-indigo-400">2,450</span>
              <span className="text-zinc-500 mb-1">kcal</span>
            </div>
          </div>

          {/* Tarjeta 2 */}
          <div className="bg-zinc-900/50 border border-zinc-800 p-6 rounded-2xl backdrop-blur-sm">
            <h3 className="text-zinc-400 text-sm font-medium mb-2">Peso Actual</h3>
            <div className="flex items-end gap-2">
              <span className="text-4xl font-bold text-white">81.2</span>
              <span className="text-zinc-500 mb-1">kg</span>
            </div>
          </div>

          {/* Tarjeta 3 */}
          <div className="bg-zinc-900/50 border border-zinc-800 p-6 rounded-2xl backdrop-blur-sm">
            <h3 className="text-zinc-400 text-sm font-medium mb-2">Macros</h3>
            <div className="w-full bg-zinc-800 rounded-full h-2.5 mt-4">
              {/* Barra de progreso de ejemplo (Proteína) */}
              <div className="bg-indigo-500 h-2.5 rounded-full w-[45%]"></div>
            </div>
            <p className="text-xs text-zinc-500 mt-2">Proteína: 45% / Carbs: 35% / Grasas: 20%</p>
          </div>

        </div>

        {/* Grid para contenido más grande (Gráficos y Rutinas) */}
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          
          {/* Sección de Rutina */}
          <div className="bg-zinc-900/50 border border-zinc-800 p-6 rounded-2xl">
            <h2 className="text-xl font-semibold mb-4">Rutina de Hoy</h2>
            <div className="space-y-4">
              {/* Ejercicios de ejemplo */}
              <div className="flex justify-between items-center p-4 bg-zinc-950 rounded-xl border border-zinc-800">
                <div>
                  <h4 className="font-medium">Press de Banca</h4>
                  <p className="text-sm text-zinc-500">4 series x 10 repeticiones</p>
                </div>
                <div className="w-8 h-8 rounded-full border-2 border-indigo-500 flex items-center justify-center">
                  <span className="text-indigo-500 text-xs">✓</span>
                </div>
              </div>
              <div className="flex justify-between items-center p-4 bg-zinc-950 rounded-xl border border-zinc-800">
                <div>
                  <h4 className="font-medium">Sentadillas</h4>
                  <p className="text-sm text-zinc-500">4 series x 12 repeticiones</p>
                </div>
                <div className="w-8 h-8 rounded-full border-2 border-zinc-700"></div>
              </div>
            </div>
          </div>

          {/* Sección de Progreso (Gráfico en el futuro) */}
          <div className="bg-zinc-900/50 border border-zinc-800 p-6 rounded-2xl flex flex-col justify-center items-center text-center min-h-[300px]">
            <div className="w-16 h-16 bg-zinc-800 rounded-full flex items-center justify-center mb-4">
              <span className="text-2xl">📊</span>
            </div>
            <h3 className="text-lg font-medium text-white mb-2">Evolución de Peso</h3>
            <p className="text-zinc-500 text-sm max-w-xs">
              Próximamente conectaremos esta sección para mostrar tus gráficos de progreso semanales.
            </p>
          </div>

        </div>

      </div>
    </div>
  );
}

export default Dashboard;