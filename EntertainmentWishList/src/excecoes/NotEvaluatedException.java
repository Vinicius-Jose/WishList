package excecoes;

import entity.Entretenimento;

public class NotEvaluatedException extends Exception {
		public NotEvaluatedException(Entretenimento a) {
			super(a.getNomeOriginal() + " ainda n�o foi avalidado");
		}
}
