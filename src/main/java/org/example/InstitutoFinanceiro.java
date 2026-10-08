package org.example;

public record InstitutoFinanceiro(String ispb, String name,Integer code,String fullName){

    public void exibirDados(){
        if (this.code == null){
            System.out.println(
                    "ISPB:"+this.ispb+"\n"+
                    "Nome:"+this.name+"\n"+
                    "Nome completo:"+this.fullName+"\n"+
                    "Código:S/N\n");
        }else{
            System.out.println(
                    "ISPB:"+this.ispb+"\n"+
                    "Nome:"+this.name+"\n"+
                    "Nome completo:"+this.fullName+"\n"+
                    "Código:"+this.code+"\n");
        }
    }

    public Boolean verificarCodigoNulo(){
        if (this.code == null){
            return false;
        }else{
            return true;
        }
    }
}
