package dominio.enums;

import config.Config;

public enum TipoDisciplina {
    OBRIGATORIA(Config.getMaxDisciplinasObrigatoriasPorAluno()),
    OPTATIVA(Config.getMaxDisciplinasOptativasPorAluno());

    private int quantidadeMax;
    TipoDisciplina(int qtdMax){
        this.quantidadeMax = qtdMax;
    }

    public int getQuantidadeMax() {
        return quantidadeMax;
    }
}
