import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gasto {
    private String pessoa;
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

    public Gasto(String pessoa, BigDecimal valor, String finalidade, String forma, LocalDate dia) {
        this.pessoa = pessoa;
        this.valor = valor;
        this.finalidade = finalidade;
        this.forma = forma;
        this.dia = dia;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
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

        return "Responsável: " + this.pessoa +
                " | Valor: R$ " + this.valor +
                " | Finalidade: " + this.finalidade +
                " | Forma de pagamento: " + this.forma +
                " | Data: " + this.dia.format(formatador);
    }
}
