class NaveEspacial {
    // Propiedades físicas
    private double x, y;              // Posición actual
    private double velocidadX;         // Velocidad horizontal
    private double acceleracionX;     // Aceleración horizontal
    private double posicionY;           // Posición Y objetivo (para evasión)
    private double velocidadY;         // Velocidad vertical (para movimiento suave)
    
    // Propiedades visuales
    private int alto, ancho;        // Dimensiones de la nave
    private Poligono forma;            // Forma de la nave (polígono)
    
    // Métodos principales:
    // - actualizar(): Actualiza posición con movimiento acelerado
    // - dibujar(): Dibuja la nave
    // - evadirAsteroide(): Lógica de evasión inteligente
    // - Coliciono(): Verifica colisión con asteroide
}