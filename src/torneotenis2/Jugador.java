package torneotenis2;

/**
 *
 * @author gaspy
 */
public class Jugador {
    private String nombre;
    private int puntos=0;
    private int games=0;
    private int sets=0;
    private int probabilidadGanar=0;
    
    
    public Jugador(String nombre, int probabilidadGanar) {
        this.nombre = nombre;
        this.probabilidadGanar = probabilidadGanar;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(){
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
    

    public int getProbabilidadGanar() {
        return probabilidadGanar;
    }

    public void setProbabilidadGanar(int probabilidadGanar) {
        this.probabilidadGanar = probabilidadGanar;
    }

    
}
