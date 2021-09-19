package controller;

import java.util.concurrent.Semaphore;

public class ThreadFormula1 extends Thread{
	
	private Semaphore semaforoPista;
	private Semaphore semaforoCarro;
	private Semaphore semaforoTempo;
	int idEscuderia;
	int idCarro;
	private static double tempos [] = new double [15];
	private static int nmrCarro [] = new int [15];
	private static int nmrEscuderia [] = new int [15];
	static int indice;
	

	public ThreadFormula1(int idEscuderia , int idCarro , Semaphore semaforoPista , Semaphore semaforoCarro, Semaphore semaforoTempo) {
        this.idCarro = idCarro;
        this.idEscuderia = idEscuderia;
        this.semaforoCarro = semaforoCarro;
        this.semaforoPista = semaforoPista;
        this.semaforoTempo = semaforoTempo;   
    
	}
	
	public void run() {
		
		
		try {
			semaforoPista.acquire();
			semaforoCarro.acquire();
			treino();
			semaforoTempo.acquire();
			Classifica();
			if  (indice == 14) {
				gridLargada();
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforoPista.release();
			semaforoCarro.release();
			semaforoTempo.release();
		}
	}
	
	
	private void treino() {
		int distanciaTotal = 3000;
		int distanciaPercorrida = 0;
		int deslocamento;
		double menorTempo = 1000;
		indice++;
		
		System.out.println("============ ============== ============= ============= =============");
		System.out.println("O Carro " +idCarro+ " da Escuderia " +idEscuderia+ " está na pista");
		System.out.println("============ ============== ============= ============= =============");
		for (int volta = 0; volta < 3; volta++) {
			double tempoInicial = System.nanoTime();
			
			while (distanciaPercorrida < distanciaTotal) {
				deslocamento = (int)((Math.random() * 101) + 200);
				distanciaPercorrida += deslocamento;
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			double tempoFinal = System.nanoTime();
			double tempoTotal = tempoFinal - tempoInicial;
			tempoTotal = tempoTotal / Math.pow(10, 9);
			System.out.println("O carro " + idCarro + " da Escuderia " + idEscuderia + " marcou o tempo " + tempoTotal + " na volta " + volta);
			System.out.println("============ ============== ============= ============= =============");
			if (menorTempo > tempoTotal) {
				menorTempo = tempoTotal;
			}
		}
		System.out.println("A escuderia: #" +idEscuderia+ " fez melhor tempo com o carro " + idCarro + ": " + menorTempo);
		System.out.println("============ ============== ============= ============= =============");
		tempos[indice] = menorTempo;
		nmrCarro[indice] = idCarro;
		nmrEscuderia[indice] = idEscuderia;
		}
		
		private void Classifica() {
			// TODO Auto-generated method stub
			
		}
        
		private void gridLargada() {
			int auxCar;
			int auxEscu;
			double auxClock;
			
			int tam = tempos.length;
			
			for (int i = 0; i < tam; i++) {
				for (int j = i+1; j < tam; j++) {
					if (tempos[i]> tempos[j]) {
						auxCar = nmrCarro[i];
						auxEscu = nmrEscuderia[i];
						auxClock = tempos[i];
						nmrCarro[i] = nmrCarro[j];
						nmrEscuderia[i] = nmrEscuderia[i];
						tempos[i] = tempos[j];
						nmrCarro[j] = auxCar;
						nmrEscuderia[j] = auxEscu;
						tempos[j] = auxClock;
					}
				}
			}
			System.out.println("\n=========== GRID DE LARGADA =============\n");
			for (int i=0 ; i<14 ; i++) {
				System.out.println("#" + (i+1) + "|| Carro " + nmrCarro[i] + " || Escuderia " + nmrEscuderia[i] + " || Tempo: " + tempos[i]);
				System.out.println("============ ============== ============= ============= =============");
			
		}
	}


}
