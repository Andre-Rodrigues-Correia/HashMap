import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.security.auth.login.AccountException;


public class App {

    
    // static HashMap<Integer, String> produtos = new HashMap<Integer, String>();
    public static void main(String[] args) throws Exception {
        HashMap<Integer, String> produtos = new HashMap<Integer, String>();
        HashMap<Integer, Double> precos = new HashMap<Integer, Double>();
        Scanner ler = new Scanner(System.in);

        menu(produtos, precos, ler);

        ler.close();
    }

    public static void menu(HashMap<Integer, String> produtos, HashMap<Integer, Double> precos, Scanner ler)throws IOException, InterruptedException{

        int opcao_desejada, flag;

        //produtos.put(1, "teste");
        //precos.put(1, 199.00);

        flag = 0;

        do {

            LimparTela();

            if(flag == 1){
                System.out.println("OPÇÃO INVALIDA!");
            }

        System.out.print("\nARC Store\n");
        System.out.println("DIGITE 1 PARA LISTAR PRODUTOS");
        System.out.println("DIGITE 2 PARA ADICIONAR PRODUTOS");
        System.out.println("DIGITE 3 PARA MODIFICAR PRODUTOS");
        System.out.println("DIGITE 4 PARA REMOVER PRODUTOS");
        System.out.println("DIGITE 0 PARA SAIR DA ARC STORE");
        System.out.print("\nDIGITE AQUI: ");
        opcao_desejada = ler.nextInt();
        ler.nextLine();
        flag = 1;
        
            
        } while (opcao_desejada < 0 || opcao_desejada > 4);

        switch (opcao_desejada) {

            case 1:
                relatorio(produtos, precos);
                acao(produtos, precos, ler);
                break;
            case 2:
                acrescentarProduto(produtos, precos, ler);
                break;
            case 3:
                alterarProduto(produtos, precos, ler);
                break;
            case 4:
                removerProdutos(produtos, precos, ler);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
                break;
        }

    }

    public static void relatorio(HashMap<Integer, String> produtos, HashMap<Integer, Double> precos) throws IOException, InterruptedException{

        LimparTela();

        System.out.println("RELATORIO DE PRODUTOS\n");

        System.out.printf("%6s | %-10s | %-6s\n", "código", "produto", "preço");

        for (int chave : produtos.keySet()) {
            System.out.format("%06d | %-10s | %6.2f\n",chave, produtos.get(chave), precos.get(chave));
        }
    }

    public static void acrescentarProduto(HashMap<Integer, String> produtos, HashMap<Integer, Double> precos, Scanner ler) throws IOException, InterruptedException{
        
        Integer valor;
        if(produtos.size() == 0){
            valor = 1;
        }else{
            valor = Collections.max(produtos.keySet())+ 1;
        }
        System.out.print("\nDIGITE O NOME DO PRODUTO A SER ACRESCENTADO: ");
        String nomeProdurto = ler.nextLine();
        System.out.print("\nDIGITE O PREÇO DO PRODUTO A SER ACRESCENTADO: ");
        Double preco = ler.nextDouble();
        ler.nextLine();

        System.out.print("\nDIGITE S/N PARA CONFIRMAR ALTERAÇÃO: ");
        String s_n = ler.nextLine().trim();

        LimparTela();

        if(s_n.toUpperCase().equals("S")){
            System.out.println("PRODUTO INSERIDO COM SUCESSO!");
            produtos.put(valor, nomeProdurto);
            precos.put(valor, preco);
        }else{
            System.out.println("PRODUTO NÃO INSERIDO!");
        }

        acao(produtos, precos, ler);
    }
    public static void alterarProduto(HashMap<Integer, String> produtos, HashMap<Integer, Double> precos, Scanner ler) throws IOException, InterruptedException{
        LimparTela();

        String nomeProduto;

        System.out.print("\nDIGITE A CHAVE DO PRODUTO A SER ALTERADO: ");
        int valor = ler.nextInt();
        ler.nextLine();

        if(produtos.containsKey(valor)){
            LimparTela();
            System.out.format("DADOS ORIGINAIS\nNOME: %s | PREÇO: %.2f\n", produtos.get(valor),precos.get(valor));

            System.out.print("\nDIGITE O NOVO NOME DO PRODUTO: ");
            nomeProduto = ler.nextLine();
            nomeProduto = nomeProduto.isEmpty() ? produtos.get(valor) : nomeProduto;

            System.out.print("\nDIGITE O NOVO PREÇO DO PRODUTO: ");
            String novoPrecoStr = ler.nextLine();

            Double novoPreco = novoPrecoStr.isEmpty() ? precos.get(valor) : Double.parseDouble(novoPrecoStr.replace(",", "."));

            System.out.print("\nDIGITE S/N PARA CONFIRMAR ALTERAÇÃO: ");
            String s_n = ler.nextLine().trim();

            LimparTela();

            if(s_n.toUpperCase().equals("S")){
                LimparTela();
                produtos.put(valor, nomeProduto);
                precos.put(valor, novoPreco);
                System.out.println("PRODUTO ALTERADO COM SUCESSO!");
            }else{
                System.out.println("PRODUTO NÃO ALTERADO!");
            }
        }else{
            System.out.println("PRODUTO NÃO ENCONTRADO!");
        }
            
        acao(produtos, precos,ler);
    }

    public static void removerProdutos(HashMap< Integer, String> produtos, HashMap<Integer, Double> precos, Scanner ler) throws IOException, InterruptedException{
        
        
        relatorio(produtos, precos);

        System.out.print("\nDIGITE A CHAVE DO PRODUTO A SER ALTERADO: ");
        Integer valor = ler.nextInt();
        ler.nextLine();

        if(produtos.containsKey(valor)){
            LimparTela();
            System.out.format("DADOS ORIGINAIS\nNOME: %s | PREÇO: %.2f\n", produtos.get(valor),precos.get(valor));    
            System.out.print("\nDIGITE S/N PARA CONFIRMAR REMOÇÃO: ");
            String s_n = ler.nextLine().trim();

            LimparTela();

            if(s_n.toUpperCase().equals("S")){
                LimparTela();
                produtos.remove(valor);
                precos.remove(valor);
                System.out.println("PRODUTO REMOVIDO COM SUCESSO!");
            }else{
                System.out.println("PRODUTO NÃO REMOVIDO!");
            }
        }else{
            System.out.println("PRODUTO NÃO ENCONTRADO");
        }
        acao(produtos, precos, ler);
    }

    public static void acao(HashMap<Integer, String> produtos, HashMap<Integer, Double> precos, Scanner ler)throws IOException, InterruptedException{
        int opcao_desejada;
        System.out.print("\n\nDIGITE 1 PARA VOLTAR AO MENU\nDIGITE 0 PARA SAIR DO SISTEMA\nDIGITE AQUI: ");
        opcao_desejada = ler.nextInt();
        do{
            if(opcao_desejada == 1){
                menu(produtos, precos,ler);
            }
            if(opcao_desejada == 0){
                System.exit(0);
            }else{
                System.out.print("\nOPÇÃO INVÁLIDA!\nDIGITE NOVEMNTE: ");
            }
        }while(opcao_desejada < 0 || opcao_desejada > 1);

        LimparTela();

    }

    public static void LimparTela() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}