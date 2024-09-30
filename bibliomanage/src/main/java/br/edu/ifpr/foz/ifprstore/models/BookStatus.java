package br.edu.ifpr.foz.ifprstore.models;

public enum BookStatus {
    DISPONIVEL(1), EMPRESTADO(2), INDISPONIVEL(3);

    private final int codigo;

    BookStatus(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static BookStatus fromCodigo(int codigo) {
        for (BookStatus status : BookStatus.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }
}
