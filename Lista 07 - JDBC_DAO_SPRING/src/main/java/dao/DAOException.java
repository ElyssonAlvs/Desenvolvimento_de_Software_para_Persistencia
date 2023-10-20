package dao;
// Uma classe que trata execeções específicas que podem ocorrer na interação com o BD
public class DAOException extends RuntimeException {

    // Construtor padrão sem argumentos
    public DAOException() {
        super();
    }

    // Construtor que aceita uma mensagem de erro e uma causa (outra exceção)
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor que aceita uma mensagem de erro
    public DAOException(String message) {
        super(message);
    }

    // Construtor que aceita apenas uma causa (outra exceção)
    public DAOException(Throwable cause) {
        super(cause);
    }

}
