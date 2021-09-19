package view;

import java.util.concurrent.Semaphore;

import controller.ThreadFormula1;

public class Largada {

	public static void main(String[] args) {
		int permissaoPista = 5;
//		int permissaoCarro = 1;
		int permissaoTempo = 1;
		Semaphore semaforoPista = new Semaphore(permissaoPista);
		Semaphore[] semaforoCarro = new Semaphore[7];
		Semaphore semaforoTempo = new Semaphore(permissaoTempo);

		int idCarro = 0;
		
		for (int idEscuderia = 0 ; idEscuderia < 7 ; idEscuderia ++) {
			semaforoCarro[idEscuderia] = new Semaphore(1);
			for (int i = 0 ; i<2 ; i++) {
				idCarro++;
				Thread tCarro = new ThreadFormula1((idEscuderia + 1), idCarro, semaforoPista, semaforoCarro[idEscuderia], semaforoTempo);
				tCarro.start();
			}
		}

	}

}