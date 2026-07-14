import java.math.BigDecimal;

public class Pessoa {
    private String nome;
    private String cpf;
    private BigDecimal salario;
    private BigDecimal metaGastos;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public BigDecimal getMetaGastos() {
        return metaGastos;
    }

    public Pessoa(String nome, String cpf, BigDecimal salario, BigDecimal metaGastos) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.metaGastos = metaGastos;
    }

}
