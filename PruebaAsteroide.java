/**
 * PruebaAsteroide.java
 * 
 * CLASE SIMPLE PARA PROBAR TU ASTEROIDE
 * 
 * Esta clase te permite ver tu asteroide en acción antes de crear el juego completo.
 * Crea una ventana simple con un asteroide que se mueve y rota.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PruebaAsteroide extends JFrame {
    
    private PanelPrueba panel;
    private Timer timer;
    
    public static void main(String[] args) {
        // Crear la ventana de prueba
        PruebaAsteroide ventana = new PruebaAsteroide();
        ventana.setVisible(true);
        
        System.out.println("¡Ventana de prueba creada!");
        System.out.println("Deberías ver asteroides moviéndose y rotando.");
        System.out.println("Presiona cualquier tecla en la consola y Enter para salir.");
        
        // Esperar input para cerrar
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorar
        }
        System.exit(0);
    }
    
    public PruebaAsteroide() {
        // Configurar ventana
        setTitle("Prueba de Asteroide - Paso 2");
        setSize(ConstantesJuego.ANCHO_VENTANA, ConstantesJuego.ALTO_VENTANA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        
        // Crear panel de prueba
        panel = new PanelPrueba();
        add(panel);
        
        // Crear timer para animación (60 FPS)
        timer = new Timer(ConstantesJuego.RETRASO_TIMER, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.actualizar();
                panel.repaint();
            }
        });
        timer.start();
        
        System.out.println("Timer iniciado a " + ConstantesJuego.FPS + " FPS");
    }
    
    // Panel donde se dibujan los asteroides
    private class PanelPrueba extends JPanel {
        private ArrayList<Asteroide> asteroides;
        private int contadorFrames = 0;
        
        public PanelPrueba() {
            setBackground(new Color(ConstantesJuego.COLOR_FONDO_R, 
                                  ConstantesJuego.COLOR_FONDO_G, 
                                  ConstantesJuego.COLOR_FONDO_B));
            
            // Crear algunos asteroides de prueba
            asteroides = new ArrayList<>();
            crearAsteroidesIniciales();
        }
        
        private void crearAsteroidesIniciales() {
            // Crear 5 asteroides en diferentes posiciones
            for (int i = 0; i < 5; i++) {
                double x = 200 + i * 200; // Distribuir horizontalmente
                double y = 100 + i * 100; // Distribuir verticalmente
                asteroides.add(new Asteroide(x, y));
            }
            
            System.out.println("Creados " + asteroides.size() + " asteroides de prueba");
        }
        
        public void actualizar() {
            contadorFrames++;
            
            // Actualizar todos los asteroides
            for (Asteroide asteroide : asteroides) {
                asteroide.actualizar();
            }
            
            // Cada 3 segundos, mostrar información
            if (contadorFrames % (ConstantesJuego.FPS * 3) == 0) {
                System.out.println("Frame " + contadorFrames + " - Asteroides activos: " + asteroides.size());
                
                // Mostrar info del primer asteroide
                if (!asteroides.isEmpty()) {
                    System.out.println("Asteroide 0: " + asteroides.get(0).toString());
                }
            }
            
            // Crear nuevos asteroides si alguno salió de pantalla
            for (int i = asteroides.size() - 1; i >= 0; i--) {
                Asteroide asteroide = asteroides.get(i);
                if (asteroide.estaFueraDePantalla()) {
                    System.out.println("Asteroide salió de pantalla, creando uno nuevo");
                    asteroides.remove(i);
                    
                    // Crear nuevo asteroide en posición aleatoria
                    double nuevaX = Math.random() * ConstantesJuego.ANCHO_VENTANA;
                    double nuevaY = Math.random() * ConstantesJuego.ALTO_VENTANA;
                    asteroides.add(new Asteroide(nuevaX, nuevaY));
                }
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Limpiar pantalla
            
            // Activar antialiasing para que se vea mejor
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                               RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Dibujar todos los asteroides
            for (Asteroide asteroide : asteroides) {
                asteroide.dibujar(g);
            }
            
            // Dibujar información en pantalla
            dibujarInformacion(g2d);
        }
        
        private void dibujarInformacion(Graphics2D g2d) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            
            String info = "Prueba de Asteroide - Frame: " + contadorFrames + 
                         " | Asteroides: " + asteroides.size() + 
                         " | FPS: " + ConstantesJuego.FPS;
            
            g2d.drawString(info, 10, 25);
            
            // Instrucciones
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            g2d.drawString("Los asteroides se mueven y rotan automáticamente", 10, 50);
            g2d.drawString("Se crean nuevos cuando salen de pantalla", 10, 65);
            g2d.drawString("Observa las formas irregulares y los diferentes tamaños", 10, 80);
        }
    }
}

/*
 * ==================== INSTRUCCIONES PARA USAR ESTA PRUEBA ====================
 * 
 * 1. Asegúrate de tener las clases ConstantesJuego y Asteroide en el mismo directorio
 * 
 * 2. Compila todos los archivos:
 *    javac *.java
 * 
 * 3. Ejecuta la prueba:
 *    java PruebaAsteroide
 * 
 * 4. Deberías ver:
 *    - Una ventana con fondo azul oscuro (espacio)
 *    - 5 asteroides con formas irregulares
 *    - Los asteroides se mueven y rotan
 *    - Información de debug en la pantalla y consola
 *    - Nuevos asteroides aparecen cuando otros salen
 * 
 * ==================== QUÉ OBSERVAR ====================
 * 
 * ✅ Formas irregulares: Cada asteroide tiene una forma única
 * ✅ Colores variados: Diferentes tonos de marrón/gris
 * ✅ Movimiento fluido: Se mueven suavemente a 60 FPS
 * ✅ Rotación: Cada asteroide rota a diferente velocidad
 * ✅ Tamaños diferentes: Algunos son más grandes que otros
 * ✅ Recreación: Aparecen nuevos cuando salen de pantalla
 * 
 * ==================== PROBLEMAS COMUNES ====================
 * 
 * ❌ "No se ve nada": Verifica que las clases estén compiladas correctamente
 * ❌ "Error de compilación": Asegúrate de tener todas las clases en el mismo directorio
 * ❌ "Se ve muy lento": Normal al principio, mejorará con optimización
 * ❌ "Formas muy raras": Es normal, son asteroides irregulares
 * 
 * ==================== SIGUIENTE PASO ====================
 * 
 * Una vez que veas tus asteroides funcionando, estarás listo para:
 * - Paso 3: Mejorar las formas irregulares
 * - Paso 4: Añadir la clase NaveEspacial
 * - Paso 5: Crear el juego completo con colisiones
 */