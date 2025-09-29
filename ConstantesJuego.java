class ConstantesJuego{
    // Dimensiones de pantalla
    public static final int ANCHO_VENTANA = 1280;
    public static final int ALTO_VENTANA = 720;
    
    // Propiedades de la nave
    public static final int NAVE_ANCHO = 30;
    public static final int NAVE_ALTO = 20;
    public static final double VELOCIDAD_MAX_NAVE = 5.0;
    public static final double ACELERACION_NAVE = 0.1;
    public static final double VELOCIDAD_INICIAL_NAVE = 1.0; 
    
    // Propiedades de asteroides
    public static final int TAM_MIN_ASTEROIDE = 20;
    public static final int TAM_MAX_ASTEROIDE = 60;

    public static final double VELO_MAX_ASTEROIDE = 1.0;
    public static final double VELO_MAX_ASTEROIDE = 4.0;
    public static final double ROTACION_MINIMA_ASTEROIDE = -0.08;  // Radianes por frame
    public static final double ROTACION_MAXIMA_ASTEROIDE = 0.08;   // Radianes por frame

    public static final int MIN_VERTICES_ASTEROIDE = 6;    // Mínimo de vértices para forma irregular
    public static final int MAX_VERTICES_ASTEROIDE = 12;   // Máximo de vértices para forma irregular
    public static final double IRREGULARIDAD_ASTEROIDE = 0.4; // Qué tan irregular es la forma (0.0 a 1.0)
    
    
    
    // Configuración de juego
    public static final int FPS = 60;
    public static final int TIMER_DELAY = 1000 / FPS;

    public static final int POSICION_INICIAL_NAVE_X = 50;              // Nave empieza cerca del borde izquierdo
    public static final int POSICION_INICIAL_NAVE_Y = ALTO_VENTANA / 2; // Nave empieza en el centro vertical
    
     public static final int ZONA_META_X = ANCHO_VENTANA - 100;


     // ----------------COLORES DEL JUEGO 

    public static final int COLOR_ASTEROIDE_R = 139;  // Rojo
    public static final int COLOR_ASTEROIDE_G = 69;   // Verde  
    public static final int COLOR_ASTEROIDE_B = 19;   // Azul (Color café/marrón)
    
    // Color de la nave
    public static final int COLOR_NAVE_R = 200;       // Plateado/gris claro
    public static final int COLOR_NAVE_G = 200;
    public static final int COLOR_NAVE_B = 255;       // Con un toque azul
    
    // Color del fondo (espacio)
    public static final int COLOR_FONDO_R = 10;       // Azul muy oscuro
    public static final int COLOR_FONDO_G = 10;
    public static final int COLOR_FONDO_B = 30;

    // CONFIGURACIÓN DE ASTEROIDES 
    
    // Zonas de aparición de asteroides
    public static final int MARGEN_APARICION_ASTEROIDE = 100;   // Píxeles fuera de pantalla donde aparecen
    
    // Probabilidades y comportamientos
    public static final double PROBABILIDAD_DIRECCION_DIAGONAL = 0.3; // 30% van en diagonal
    

    

    public static double calcularProgreso(double posicionNaveX) {
        // Progreso = (posición actual - posición inicial) / distancia total * 100
        double distanciaRecorrida = posicionNaveX - POSICION_INICIAL_NAVE_X;
        double distanciaTotal = ZONA_META_X - POSICION_INICIAL_NAVE_X;
        
        // Math.max asegura que no sea negativo
        // Math.min asegura que no sea mayor a 100
        return Math.max(0.0, Math.min(100.0, (distanciaRecorrida / distanciaTotal) * 100.0));
    }


    
     
    public static boolean naveHaGanado(double posicionNaveX) {
        return posicionNaveX >= ZONA_META_X;
    }
    
    
    public static double obtenerAleatorioEnRango(double minimo, double maximo) {
        return minimo + (Math.random() * (maximo - minimo));
    }
    
   
    
     
    public static double gradosARadianes(double grados) {
        return grados * Math.PI / 180.0;
    }
    
    
    
    public static double radianesAGrados(double radianes) {
        return radianes * 180.0 / Math.PI;
    }
}

