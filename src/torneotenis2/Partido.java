package torneotenis2;
import java.util.Random;

/**
 *
 * @author gaspy es este!
 */
public class Partido {
    private String nombreTorneo;
    private Jugador jugador1;
    private Jugador jugador2;
    private int cantidadSets;
    public int setsPedidos;
    private int probabilidadJugador1; // probabilidad de ganar del jugador 1 (de 1 a 100)
    private int probabilidadJugador2; // probabilidad de ganar del jugador 2 (de 1 a 100)
    

    public Partido(String nombreTorneo, Jugador jugador1, Jugador jugador2, int cantidadSets,
                   int probabilidadJugador1) {
        this.nombreTorneo = nombreTorneo;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.cantidadSets = cantidadSets;
        this.probabilidadJugador1 = probabilidadJugador1;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public int getCantidadSets() {
        return cantidadSets;
    }

    public void setCantidadSets(int cantidadSets) {
        this.cantidadSets = cantidadSets;
    }
    
    //Coreccion de puntos de 45 a 40
    public void es40(Jugador jugador){
        if (jugador.getPuntos()== 45){
            jugador.setPuntos(40);
        }
    }
   
    //Reiniciamos los puntos del game
    public void reinicioPuntos(Jugador jugador1,Jugador jugador2){
        jugador1.setPuntos(0);
        jugador2.setPuntos(0);
    }
    
    //Reiniciamos los games
    public void reinicioGames(Jugador jugador1,Jugador jugador2){
                jugador1.setGames(0);
                jugador2.setGames(0);
    }
    
    //Resultado parcial del partido
    public void resultadoParcial(Jugador jugador1, Jugador jugador2){
        System.out.println("*****RESULTADO PARCIAL******\n" 
                + jugador1.getNombre() + "  Games: "+jugador1.getGames()+" Sets: "+jugador1.getSets()+
                "\n"+ jugador2.getNombre()+ "  Games: "+jugador2.getGames()+"Sets: "+jugador2.getSets()
                +"\n*******************");
    }

    //Simulamos el punto, comparando la probabilidad del jugador 1 con un numero aleatorio que generamos
    public void simularPunto(Jugador jugador1, Jugador jugador2) {
        int random = (int)(Math.random()*100);
        int probabilidad1 = this.probabilidadJugador1;
        
        if (random <= probabilidad1){
            jugador1.setPuntos(jugador1.getPuntos()+15);
            es40(jugador1);
            System.out.println("Punto para " + jugador1.getNombre() + "!");
        }else{
            jugador2.setPuntos(jugador2.getPuntos()+15);
            es40(jugador2);
            System.out.println("Punto para " + jugador2.getNombre() + "!");  
        }
      }
    
    //Segun la condicion gana el game jugador 1 o 2
    public void ganaGame(Jugador jugador1,Jugador jugador2) {
        if(jugador1.getPuntos()>40){
            jugador1.setGames(jugador1.getGames()+1);
            resultadoParcial(jugador1,jugador2);
            reinicioPuntos(jugador1,jugador2);
        }else{
            jugador2.setGames(jugador2.getGames()+1);
            resultadoParcial(jugador1,jugador2);
            reinicioPuntos(jugador1,jugador2);
            }
      }
   
    //Segun la condicion gana el set jugador 1 o 2
    public void ganaSet(Jugador jugador1,Jugador jugador2){
        if(jugador1.getGames()>jugador2.getGames()){
            System.out.println("GANA EL SET " + jugador1.getNombre());
            jugador1.setSets(jugador1.getSets()+1);
            resultadoParcial(jugador1,jugador2);
                        
        }else{
            System.out.println("GANA EL SET" + jugador2.getNombre());
            jugador2.setSets(jugador2.getSets()+1);
            resultadoParcial(jugador1,jugador2);
        }
    }
    
    //Si van 40-40 van a jugar el deuce
    public void deuce(Jugador jugador1, Jugador jugador2){
        System.out.println("Entramos en DEUCE");
        boolean ventaja1 = false;
        boolean ventaja2 = false;
        boolean deuceWin = false;
        reinicioPuntos(jugador1,jugador2);
        
        int probabilidad1 = this.probabilidadJugador1;
        
        while(!deuceWin){
             int random = (int)(Math.random()*100);
             if (random <= probabilidad1 && ventaja1 ){
                jugador1.setGames(jugador1.getGames()+1);
                System.out.println("Punto para " + jugador1.getNombre() + " gana el Deuce!"
                    + "\n El partido va "+jugador1.getGames()+"-"+jugador2.getGames());
                deuceWin = true;
                break;
            }else if(random <= probabilidad1 && !ventaja1){
                if(!ventaja2){
                    ventaja1 = true;
                    System.out.println("Punto para " + jugador1.getNombre() + " que obtiene la ventaja");
                }else{
                    ventaja2 = false;
                    System.out.println("Punto para " + jugador1.getNombre() + " empata el Deuce");
                }
            }else if(random >= probabilidad1 && ventaja2){
                jugador2.setGames(jugador2.getGames()+1);
                System.out.println("Punto para " + jugador2.getNombre() + " gana el Deuce!"
                    + "\n El partido va "+jugador1.getGames()+"-"+jugador2.getGames());
                deuceWin = true;
                break;
            }else{
                    if(!ventaja1){
                        ventaja2 = true;
                        System.out.println("Punto para " + jugador2.getNombre() + " que obtiene la ventaja");
                    }else{
                        ventaja1 = false;
                        System.out.println("Punto para " + jugador2.getNombre() + " empata el Deuce");
                    }
            }
       }
    }
    
    //Mostramos el resultado al final de cada SET
    public void resultado(Jugador jugador1, Jugador jugador2,int setsPedidos,String[] resultadoArray){
        
        resultadoArray[setsPedidos]=(setsPedidos+1)+" SET "+jugador1.getNombre()+" y "+jugador2.getNombre()+": "
        +jugador1.getGames()+"-"+jugador2.getGames();
        for (int i=0; i <resultadoArray.length; i++){
            System.out.println(resultadoArray[i]);
        }
        
        
    }
    
    //Se cumple todas las condiciones para que un jugador gane el partido.
    public void ganaPartido(Jugador jugador1,Jugador jugador2){
        if (jugador1.getSets()>jugador2.getSets()){
            System.out.println("Felicitaciones " + jugador1.getNombre() + " gana el torneo "+this.getNombreTorneo());
        }else{
            System.out.println("Felicitaciones " + jugador2.getNombre() + " gana el torneo "+this.getNombreTorneo());
        }
    }
    
    //Segun el contador sea par o impar saca jugador1 o jugador2
    public void saque(Jugador jugador1, Jugador jugador2){
        int contGames=1;
        if(contGames%2==0){
            System.out.println("Saca: "+jugador2.getNombre());
            contGames++;
        }else{
            System.out.println("Saca: "+jugador1.getNombre());
            contGames++;
        }
        
    }

    // Si los jugadores llegan a tener 6-6 games, juegan tieBreak
    public void tieBreak(Jugador jugador1, Jugador jugador2){
        boolean tieBreakWin = false;
        int cont1=0;
        int cont2=0;
        System.out.println("Entramos en TIE BREAK!");
        int probabilidad1 = this.probabilidadJugador1;
        
        while(!tieBreakWin){
            int random = (int)(Math.random()*100);
            if (random <= probabilidad1){
                cont1++;
                System.out.println("Punto para: "+jugador1.getNombre()+" Tie break va: "
                        +cont1+"-"+cont2);
            }else{
                cont2++;
                System.out.println("Punto para: "+jugador2.getNombre()+" Tie break va: "
                        +cont1+"-"+cont2);
            }
            if( cont1>=7  && (cont2-cont1)>1){
                
                System.out.println("Gana el Tie Break "+jugador1.getNombre());
                tieBreakWin = true;
                jugador1.setSets(jugador1.getSets()+1);
                break;
            
            }else if(cont2>=7 && (cont2-cont1)>1){
                
                System.out.println("Gana el Tie Break "+jugador2.getNombre());
                tieBreakWin = true;
                jugador2.setSets(jugador2.getSets()+1);
                break;
            }

        }
    }
    
    //Simulacion del Partido
    public void jugarPartido(Jugador jugador1, Jugador jugador2){
        boolean flag = false;
        int maxSet = 0;
        setsPedidos=-1;
        int contGames=1;
        
        //Si es al mejor de 5 el ganador debera ganar 3 sets, en el caso de mejor de 3 deberan ser 2
        System.out.println("La cantidad de sets maximos "+this.getCantidadSets());
        String[] resultadoArray = new String[this.getCantidadSets()];
        if(this.getCantidadSets()==5){
            maxSet=3;
        }else{
            maxSet=2;
        }
        
        
        while(!flag){
            
            // Quien saca ?
            if(contGames%2==0){
                System.out.println("Saca: "+jugador2.getNombre());
                contGames++;
            }else{
                System.out.println("Saca: "+jugador1.getNombre());
                contGames++;
            }
            
            simularPunto(jugador1, jugador2);
            
            //Condicion para ir a deuce
            if(jugador1.getPuntos()==40 && jugador2.getPuntos()==40){
                deuce(jugador1,jugador2);
            }
            //Condicion para ganar game
            if(jugador1.getPuntos()>40 || jugador2.getPuntos()>40){
                ganaGame(jugador1, jugador2);
            }
            //Condicion para jugador 1 ganar por 6
            if(jugador1.getGames()==6 && jugador2.getGames()<5){
                ganaSet(jugador1,jugador2);
                setsPedidos++;
                resultado(jugador1,jugador2,setsPedidos,resultadoArray);
                reinicioGames(jugador1,jugador2);
            //Condicion para jugador 2 ganar por 6
            }else if(jugador2.getGames()==6 && jugador1.getGames()<5){
                ganaSet(jugador1,jugador2);   
                setsPedidos++;
                resultado(jugador1,jugador2,setsPedidos,resultadoArray);
                reinicioGames(jugador1,jugador2);
            //Condicion para jugador 1 ganar por 7
            }else if(jugador1.getGames()==7 && jugador2.getGames()==5){
                ganaSet(jugador1,jugador2);
                setsPedidos++;
                resultado(jugador1,jugador2,setsPedidos,resultadoArray);
                reinicioGames(jugador1,jugador2);
            //Condicion para jugador 2 ganar por 7
            }else if(jugador2.getGames()==7 && jugador1.getGames()==5){
                ganaSet(jugador1,jugador2);
                setsPedidos++;
                resultado(jugador1,jugador2,setsPedidos,resultadoArray);
                reinicioGames(jugador1,jugador2);
            //Condicion para ir a tie break
            }else if(jugador2.getGames()==6 && jugador1.getGames()==6){
                tieBreak(jugador1,jugador2);
                setsPedidos++;
                resultado(jugador1,jugador2,setsPedidos,resultadoArray);
                reinicioGames(jugador1,jugador2);
            }
            //Condicion para ganar partido
            if(jugador1.getSets()==maxSet || jugador2.getSets()==maxSet){
                ganaPartido(jugador1,jugador2);
                flag = true;
                break;
            }
            System.out.println("El game va " + jugador1.getPuntos() + " - "+ jugador2.getPuntos());
            
        }
         
        System.out.println("El partido ha terminado señor....");
        reinicioGames(jugador1,jugador2);
        jugador1.setSets(0);
        jugador2.setSets(0);
        setsPedidos=-1;
        contGames=1;
        
    }

}
