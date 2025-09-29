import java.awt.*;
import java.util.Random;


// Métodos principales:
    // - actualizar(): Actualiza posición y rotación
    // - dibujar(): Dibuja el asteroide
    // - estaFueraDePantalla()): Verifica si salió de pantalla
    // - generarForma()): Crea forma irregular aleatoria


public class Asteroide {
    // Propiedades físicas
    private double x, y;             
    private double velocidadX, velocidadY; 
    private double aguloRotacion;     
    private double velocidadRotacion;     
    
    // Propiedades visuales
    private int tamano;                 
    private Polygon forma;              
    private Color color;              
    
    

    private static Random random = new random();

    
    public Asteroide(double x, double y) {
        this.x = x;
        this.y = y;
        inicializarPropiedadesAleatorias();
        generarForma();
        System.out.println("Asteroide creado en (" + x + ", " + y + ") con tamaño " + tamaño);
    }

    private void inicializarPropiedadesAleatoria(){
        int rangoTamaño = ConstantesJuego.TAMAÑO_MAXIMO_ASTEROIDE - ConstantesJuego.TAMAÑO_MINIMO_ASTEROIDE;
        this.tamaño = ConstantesJuego.TAMAÑO_MINIMO_ASTEROIDE + random.nextInt(rangoTamaño + 1);
        
        //-----------------------velocidad-----------------------
        double rangoVelocidad = ConstantesJuego.VELOCIDAD_MAXIMA_ASTEROIDE - ConstantesJuego.VELOCIDAD_MINIMA_ASTEROIDE;

        // Velocidad X (siempre hacia la izquierda para simular movimiento del espacio)
        this.velocidadX = -(ConstantesJuego.VELOCIDAD_MINIMA_ASTEROIDE + (random.nextDouble() * rangoVelocidad));
        
        // Velocidad Y (puede ser hacia arriba o abajo)
        this.velocidadY = (random.nextDouble() - 0.5) * 2.0 * ConstantesJuego.VELOCIDAD_MAXIMA_ASTEROIDE;

        // Rotación aleatoria
        double rangoRotacion = ConstantesJuego.ROTACION_MAXIMA_ASTEROIDE - ConstantesJuego.ROTACION_MINIMA_ASTEROIDE;
        this.velocidadRotacion = ConstantesJuego.ROTACION_MINIMA_ASTEROIDE + (random.nextDouble() * rangoRotacion);
        this.anguloRotacion = random.nextDouble() * 2 * Math.PI;
        
        int variacion = 30; // Variación en el color
        int r = Math.max(0, Math.min(255, ConstantesJuego.COLOR_ASTEROIDE_R + random.nextInt(variacion) - variacion/2));
        int g = Math.max(0, Math.min(255, ConstantesJuego.COLOR_ASTEROIDE_G + random.nextInt(variacion) - variacion/2));
        int b = Math.max(0, Math.min(255, ConstantesJuego.COLOR_ASTEROIDE_B + random.nextInt(variacion) - variacion/2));
        this.color = new Color(r, g, b);
    }

    private void generarForma(){

        this.forma = new Polygon();

        int numVertices = ConstantesJuego.MIN_VERTICES_ASTEROIDE + random.nextInt(ConstantesJuego.MAX_VERTICES_ASTEROIDE - ConstantesJuego.MIN_VERTICES_ASTEROIDE);

        for( int i = 0; i < numVertices; i++){

            double angulo = (2 * Math.PI * i) / numVertices;
            
            // Distancia desde el centro (con variación aleatoria para irregularidad)
            double distanciaBase = tamaño * 0.8; // Radio base
            double variacion = tamaño * ConstantesJuego.IRREGULARIDAD_ASTEROIDE;
            double distancia = distanciaBase + (random.nextDouble() - 0.5) * variacion;
            
            
            int puntoX = (int)(Math.cos(angulo) * distancia);
            int puntoY = (int)(Math.sin(angulo) * distancia);
            
            
            forma.addPoint(puntoX, puntoY);
        }
    }
        // Actualizar posición y rotacion
    public void actualizar() {
        
        x += velocidadX;
        y += velocidadY;
        
        
        anguloRotacion += velocidadRotacion;
    
        if (anguloRotacion > 2 * Math.PI) {
            anguloRotacion -= 2 * Math.PI;
        } else if (anguloRotacion < 0) {
            anguloRotacion += 2 * Math.PI;
        }
    }


    public void dibujar( Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        // Guardar el estado original del Graphics
        AffineTransform transformOriginal = g2d.getTransform();
        
        g2d.translate(x, y);
        g2d.rotate(anguloRotacion);
        g2d.setColor(color);
        g2d.fillPolygon(forma);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(forma);
        
        // Restaurar el estado original
        g2d.setTransform(transformOriginal);
        
        // OPCIONAL: Dibujar información de debug (quítalo después)
        dibujarInfoDebug(g2d);
    }


    /**
     * Dibuja información de debug (solo para aprendizaje)
     */
    private void dibujarInfoDebug(Graphics2D g2d) {
        // Solo mostrar info si está cerca del centro de la pantalla
        if (x > 100 && x < ConstantesJuego.ANCHO_VENTANA - 200 && 
            y > 100 && y < ConstantesJuego.ALTO_VENTANA - 100) {
            
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            
            String info = String.format("T:%d V:(%.1f,%.1f)", tamaño, velocidadX, velocidadY);
            g2d.drawString(info, (int)x - tamaño, (int)y - tamaño - 5);
        }
    }
    
    //     Verifica si el asteroide está fuera de la pantallatrue si está completamente fuera, false si aún es visible
     
    public boolean estaFueraDePantalla() {
        
        int margen = tamaño + ConstantesJuego.MARGEN_APARICION_ASTEROIDE;
        
        return (x < -margen || 
                x > ConstantesJuego.ANCHO_VENTANA + margen ||
                y < -margen || 
                y > ConstantesJuego.ALTO_VENTANA + margen);
    }
    
   
    
    public double getX() { 
        return x; 
    }
    public double getY() { 
        return y;
    }
    public int getTamaño() { 
        return tamaño; 
    }
    public Polygon getForma() { 
        return forma; 
    }
    
    
 
    public void setPosicion(double nuevaX, double nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
    }
    
  
    public void setVelocidad(double nuevaVelX, double nuevaVelY) {
        this.velocidadX = nuevaVelX;
        this.velocidadY = nuevaVelY;
    }
    
    // ==================== MÉTODO PARA DEBUGGING ====================
   
    @Override
    public String toString() {
        return String.format("Asteroide[pos:(%.1f,%.1f), vel:(%.2f,%.2f), tamaño:%d]", 
                           x, y, velocidadX, velocidadY, tamaño);
    }
}