package org.example;

import java.io.IOException;
public class Main {
    static void main() throws IOException, InterruptedException {
        APIExterna.requisitarUmInstitutoFinanceiro();
        APIExterna.exibirApenasOsNomesDosInstitutos();

    }
}
