import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gasto {
    private int id_pessoa;
    private BigDecimal valor;
    private String finalidade;
    private String forma;
    private LocalDate dia;


    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public Gasto(int id_pessoa, BigDecimal valor, String finalidade, String forma, LocalDate dia) {
        this.id_pessoa = id_pessoa;
        this.valor = valor;
        this.finalidade = finalidade;
        this.forma = forma;
        this.dia = dia;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Responsável: " + this.id_pessoa +
                " | Valor: R$ " + this.valor +
                " | Finalidade: " + this.finalidade +
                " | Forma de pagamento: " + this.forma +
                " | Data: " + this.dia.format(formatador);
    }
}
