package org.example;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class APIExterna {
    private static final String url = "https://brasilapi.com.br/api/banks/v1/";

    public static void requisitarUmInstitutoFinanceiro() throws IOException, InterruptedException {
        String id = Utilitarios.escolherID();

        InstitutoFinanceiro instituto = Utilitarios.retornarUmJson(url+id,InstitutoFinanceiro.class);

        Optional<String> testarNulabilidade = Optional.ofNullable(instituto.ispb());

        testarNulabilidade.ifPresentOrElse(
                v->
                {
                    System.out.println("ISPB:"+v+"\n"+
                                    "Nome:"+instituto.name()+"\n"+
                                    "Nome completo:"+instituto.fullName()+"\n"+
                                    "Código:"+instituto.code());
                },
                () -> System.out.println("Não existe instituição financeira com o id "+id));
    }

    private static List<InstitutoFinanceiro> requisitarTodosInstitutosFinanceiros() throws IOException, InterruptedException {
        InstitutoFinanceiro [] todosInstitutosArray = Utilitarios.retornarTodosJsons(url,InstitutoFinanceiro[].class);

        List<InstitutoFinanceiro> todosInstitutos = Arrays.asList(todosInstitutosArray);

        todosInstitutos.forEach(e -> e.exibirDados());

        return todosInstitutos;
    }

    private static List<InstitutoFinanceiro> exibirInstituicoesComCodigoAcimaDeCem() throws IOException, InterruptedException {
        List<InstitutoFinanceiro> todosInstitutos = requisitarTodosInstitutosFinanceiros();

        List<InstitutoFinanceiro> institutosComCodigo = todosInstitutos
                .stream()
                .filter(e ->e.verificarCodigoNulo() && e.code() <= 100)
                .sorted(Comparator.comparing(InstitutoFinanceiro::code))
                .toList();

        System.out.println("*****INSTITUIÇÕES COM CÓDIGO PRESENTE*****");
        institutosComCodigo.forEach(e -> System.out.println(
                "ISPB:"+e.ispb()+"\n"+
                "Nome:"+e.name()+"\n"+
                "Código:"+e.code()+"\n"));

        return institutosComCodigo;
    }
    public static void exibirApenasOsNomesDosInstitutos() throws IOException, InterruptedException {
        List<InstitutoFinanceiro> institutosFinanceirosComCodigo = exibirInstituicoesComCodigoAcimaDeCem();

        String nomesInstitutosFinanceiros = institutosFinanceirosComCodigo
                .stream()
                .map(e ->e.name().toUpperCase())
                .collect(Collectors.joining(";"));

        System.out.println(nomesInstitutosFinanceiros);
    }
}
