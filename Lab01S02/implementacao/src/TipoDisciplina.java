public enum TipoDisciplina {
    OBRIGATORIA(4),
    OPTATIVA(2);

    double qtdMax; // quantidade maxima de disciplinas

    TipoDisciplina(double qtdMax){
        this.qtdMax = qtdMax;
    }
    
    public double getQuantidadeMax(){
        return this.qtdMax;
    }
}
