// José Henrique Polizeli Sambatti / Lorenzo Henrique Dias de Oliveira
import java.util.Random;
import java.util.Scanner;

public class iF3 {
    final static Scanner LER = new Scanner(System.in);
    final static Random RAN = new Random();

    public static void main(String[] args) {
        final int COLUNAS = 8;
        final int LINHAS = 6;
        final int frequenciaPitstop = 7;
        int rodada = 0;
        int vida = 0;
        int y = 0; // coluna do carro
        int vazioAnterior = 0;
        int pneu = 0;
        int carroEscolhido = 0;
        String entradaCurva = null;
        char lado = ' ';
        char continuar = ' ';
        int virar = 0;
        int pista[][] = new int[LINHAS][COLUNAS];
        int x = pista.length - 1; // linha do carro
        final String bannerAsciInformativos[] = { "\n  ███  ███████████  ████████ \n" + // titulo do jogo
                " ░░░  ░░███░░░░░░█ ███░░░░███\n" + //
                " ████  ░███   █ ░ ░░░    ░███\n" + //
                "░░███  ░███████      ██████░ \n" + //
                " ░███  ░███░░░█     ░░░░░░███\n" + //
                " ░███  ░███  ░     ███   ░███\n" + //
                " █████ █████      ░░████████ \n" + //
                "░░░░░ ░░░░░        ░░░░░░░░  \n" + //
                "                             \n" + //
                "                             \n" + //
                "                             ",
                "\n ███████████     ███████       ███████    ██████   ██████ ███\n" + //
                        "░░███░░░░░███  ███░░░░░███   ███░░░░░███ ░░██████ ██████ ░███\n" + //
                        " ░███    ░███ ███     ░░███ ███     ░░███ ░███░█████░███ ░███\n" + //
                        " ░██████████ ░███      ░███░███      ░███ ░███░░███ ░███ ░███\n" + //
                        " ░███░░░░░███░███      ░███░███      ░███ ░███ ░░░  ░███ ░███\n" + //
                        " ░███    ░███░░███     ███ ░░███     ███  ░███      ░███ ░░░ \n" + //
                        " ███████████  ░░░███████░   ░░░███████░   █████     █████ ███\n" + //
                        "░░░░░░░░░░░     ░░░░░░░       ░░░░░░░    ░░░░░     ░░░░░ ░░░ \n" + //
                        "                                                             \n" + //
                        "                                                             \n" + //
                        "                                                             " };
        final String visaoSelecaoCarros[] = { "  ____________________\n" + //
                " |                    |\n" + //
                " |____________________|\n" + //
                " /                    \\\n" + //
                "/______________________\\\n" + //
                "|_____            _____|\n" + //
                "|____ \\__________/ ____|\n" + //
                "|\\ \\ |OO________OO| / /|\n" + //
                "|/_/_|            |_\\_\\|" + "\n    ____  ___    __  ___\n" + //
                "   / __ \\/   |  /  |/  /\n" + //
                "  / /_/ / /| | / /|_/ / \n" + //
                " / _, _/ ___ |/ /  / /  \n" + //
                "/_/ |_/_/  |_/_/  /_/   \n" + //
                "                        ",
                "  ______________________\n" + //
                        "  \\____________________/\n" + //
                        " ______||___/ \\___||_____\n" + //
                        "|    |\\            /|    |                 \n" + //
                        "|____| |OO______OO| |____|" + "\n    _ ______                           __         _____\n" + //
                        "   (_) ____/___  _________ ___  __  __/ /___ _   |__  /\n" + //
                        "  / / /_  / __ \\/ ___/ __ `__ \\/ / / / / __ `/    /_ < \n" + //
                        " / / __/ / /_/ / /  / / / / / / /_/ / / /_/ /   ___/ / \n" + //
                        "/_/_/    \\____/_/  /_/ /_/ /_/\\__,_/_/\\__,_/   /____/  \n" + //
                        "                                                       ",
                "    ____________________\n" + //
                        "   //                  \\\\              \n" + //
                        "  //                    \\\\\n" + //
                        " //______________________\\\\\n" + //
                        "| 43                    43 |\n" + //
                        "|__________________________|\n" + //
                        "\\_OO____________________OO_/\n" + //
                        " |_____|            |_____|" + "\n    ____  _____   ______  __________ \n" + //
                        "   / __ \\/  _/ | / / __ \\/ ____/ __ \\\n" + //
                        "  / / / // //  |/ / / / / /   / / / /\n" + //
                        " / /_/ // // /|  / /_/ / /___/ /_/ / \n" + //
                        "/_____/___/_/ |_/\\____/\\____/\\____/  \n" + //
                        "                                     " };
        final int CARROS[][] = { { 1, 30, 10, 3 }, { 3, 17, 30, 1 }, { 2, 23, 20, 2 } }; // 0 é curva 1 é durabilidade
                                                                                         // do pneu 2 é multiplicador de
                                                                                         // distancia 3 é vida
        final String SPRITES[][] = { // linha é o sprite e coluna é qual linha do sprite
                { "         ",
                        "  _____  ",
                        " | ___ | ",
                        " | ___ | ", "¨|/___\\|¨",
                        " |     | ",
                        " |     | ",
                        " |]   [| ",
                        " |_____| ",
                },
                { "         ", "         ", " _ |-| _ ", "|_| | |_|", "   /^\\   ", "  | v |  ", " _|200|_ ",
                        "|_|\\ /|_|", "   /_\\   " },
                { "         ", "  _____  ",
                        " /     \\ ",
                        "|| ___ ||",
                        "¬|/___\\|¬",
                        " |     | ",
                        " |     | ",
                        "||=====||",
                        "  \\___/  " },
                { "         ", "         ", "         ", "         ", "         ", "         ", "         ",
                        "         ", "         " },
                { "---------", "---------", "---------", "---------", "---------", "---------", "---------",
                        "---------", "---------" },
                { " _______ ",
                        "/       \\",
                        "|   /|  |",
                        "|  /_|__|",
                        "|_______|",
                        "|PITSTOP|",
                        "| _   _ |",
                        "||_| |_||",
                        "\\_______/" } };
        imprimirAcontecimentos(bannerAsciInformativos[0]); // titulo do jogo
        do {
            rodada = 0;
            y = 0;
            carroEscolhido = lerCarroEscolhido(CARROS, visaoSelecaoCarros);
            encherPista(pista); // coloca como vazio em tudo
            pneu = CARROS[carroEscolhido][1]; // posicao do pneu do carro
            vida = CARROS[carroEscolhido][3];
            pista[x][y] = carroEscolhido; // posiciona do carro
            while (vida != 0) { // diferente de caixa
                rodada++;
                imprimirPista(pista, rodada, CARROS[carroEscolhido][0], SPRITES, pneu, CARROS[carroEscolhido][2], vida);
                entradaCurva = lerEntradaCurva(CARROS[carroEscolhido][0]);
                lado = entradaCurva.charAt(0);
                if (lado != 'F') {
                    virar = entradaCurva.length();
                    y = atualizarCarroY(y, pneu, CARROS[carroEscolhido][0], pista[x].length - 1,
                            lado, virar); // atualiza a coluna do carro
                    pneu -= (virar * (virar + 1)) >> 1; // desgaste pneu
                }
                rolarPista(pista); // avanca a pista
                limparLinha(pista[0]); // faz a pista[0] ficar vazia
                if (rodada % 2 == 0) {
                    vazioAnterior = verificarVazioAnterior(pista[2]); // onde o carro vai ficar
                    gerarObstaculos(pista[0], vazioAnterior, CARROS[carroEscolhido][0]);
                    if (rodada % (frequenciaPitstop << 1) == 0) {
                        pista[0][pista[0].length - 1] = 5; // = pitstop
                    }
                } else if (rodada % frequenciaPitstop == 0) {
                    pista[0][0] = 5; // = pitstop
                }
                if (pista[x][y] != 4) { // diferente de caixa
                    if (pista[x][y] == 5) { // = pitstop
                        pneu = CARROS[carroEscolhido][1]; // reseta pneu
                        imprimirAcontecimentos("PITSTOP!");
                    }
                    pista[x][y] = carroEscolhido; // posiciona carro
                } else {
                    vida--;
                    imprimirAcontecimentos("BONK!");
                    encherPista(pista);
                    pista[x][y] = carroEscolhido;
                }
            }
            imprimirAcontecimentos(bannerAsciInformativos[1]); // boom! perdeu o jogo
            continuar = lerChar("Continuar?(s/n)");
        } while (continuar == 'S');
    }

    public static int lerCarroEscolhido(int[][] valoresCarros, String[] spritesCarros) {
        int carroEscolhido = 0;
        char confirmacao = ' ';
        do {
            carroEscolhido = lerIntLimitado(valoresCarros.length, "Qual carro:", 1);
            carroEscolhido--;
            System.out.println(spritesCarros[carroEscolhido] + "\n\nMaximo de curva: "
                    + valoresCarros[carroEscolhido][0] + "\nPneu: " + valoresCarros[carroEscolhido][1]
                    + "\nMultiplicador: " + valoresCarros[carroEscolhido][2] + "\nVidas: "
                    + valoresCarros[carroEscolhido][3] + "\n");
            confirmacao = lerChar("Confirmar:(s/n)");
        } while (confirmacao != 'S');
        return carroEscolhido;
    }

    public static int verificarVazioAnterior(int[] linhaPista) {
        int vazioAnterior = 0;
        int continuar = 0;
        for (int i = 0; i < linhaPista.length; i++) { // percorre a pista nas colunas
            if (linhaPista[i] == 3) { // se igual a vazio
                vazioAnterior = i;
                continuar = RAN.nextInt(2); // aleatoridade para qual vazio que vai escolher
                if (continuar == 1) {
                    break;
                }
            }
        }
        return vazioAnterior;
    }

    public static void limparLinha(int[] linhaPista) {
        for (int i = 0; i < linhaPista.length; i++) {
            linhaPista[i] = 3; // igual vazio
        }
    }

    public static void encherPista(int[][] pista) {
        for (int i = 0; i < pista.length; i++) {
            for (int j = 0; j < pista[i].length; j++) {
                pista[i][j] = 3; // igual a vazio
            }
        }
    }

    public static void imprimirAcontecimentos(String acontecimento) { // imprime qualquer coisa
        System.out.println(acontecimento);
    }

    public static void imprimirPista(int[][] pista, int rodada, int mC, String[][] SPRITES, int pneu, int multiplicador,
            int vida) {
        String linha = null;
        for (int i = 0; i < pista.length; i++) {
            for (int k = 0; k < SPRITES[0].length; k++) {
                linha = "|";
                for (int j = 0; j < pista[i].length; j++) {
                    linha += SPRITES[pista[i][j]][k]; // adiciona as linhas dos sprites
                }
                System.out.println(linha + "|"); // imprime a linha de sprites
            }
        }
        System.out.println("Rodada: " + rodada); // informacoes
        System.out.println("Distancia: " + multiplicador * rodada);
        System.out.println("Vida: " + vida);
        System.out.println("Pneu: " + pneu);
        System.out.println("Maximo de Curva: " + mC);
    }

    public static void rolarPista(int pista[][]) {
        for (int i = pista.length - 2; i >= 0; i--) {
            pista[i + 1] = pista[i]; // transfere a linha da pista de tras para a linha da pista na frente
        }
        pista[0] = new int[pista[0].length]; // gera nova linha
    }

    public static void gerarObstaculos(int[] linhaPista, int vazioAnterior, int mC) {
        int qtdCaixas = 0;
        int minC = vazioAnterior - (mC * 2); // minimo de alcance
        int maxC = vazioAnterior + (mC * 2); // maximo de alcance
        int pCy = 0;
        int vazio = 0;
        if (minC < 0) { // verifica limites
            minC = 0;
        }
        if (maxC > linhaPista.length - 1) {
            maxC = linhaPista.length - 1;
        }
        vazio = RAN.nextInt((maxC - minC) + 1) + minC; // gera posicao para ser vazio dentro do alcance
        qtdCaixas = RAN.nextInt(linhaPista.length - 4) + 5; // minimo de caixas = 5
        while (qtdCaixas > 0) {
            while (true) {
                pCy = RAN.nextInt(linhaPista.length); // posicao caixa nova
                if (linhaPista[pCy] == 3) { // se igual a vazio
                    linhaPista[pCy] = 4; // igual a caixa
                    break;
                }
            }
            qtdCaixas--;
        }
        linhaPista[vazio] = 3; // igual a vazio
    }

    public static int atualizarCarroY(int y, int pneu, int mC, int limiteDireita, char lado, int virar) {
        int chance = RAN.nextInt(100);
        if (pneu <= 0) { // verifica se vai ter chance de perder aderencia
            if (chance < pneu * -2) { // 2 % para cada um abaixo
                if (chance % 2 != 0) { // aleatoriadade de +1 ou -1
                    virar++;
                    imprimirAcontecimentos("OVERSTEER!");
                } else {
                    virar--;
                    imprimirAcontecimentos("UNDERSTEER!");
                }
            }
        }
        if (lado == 'E') { // esquerda
            y -= virar;
        } else { // direita
            y += virar;
        }
        if (y < 0) { // limites
            y = 0;
        } else if (y > limiteDireita) {
            y = limiteDireita;
        }
        return y;
    }

    public static int lerIntLimitado(int limite, String pergunta, int limiteInferior) {
        int n = 0;
        do {
            System.out.println("(" + limiteInferior + "-" + limite + ") " + pergunta);
            n = LER.nextInt();
        } while (n < limiteInferior || n > limite);
        return n;
    }

    public static String lerEntradaCurva(int mC) {
        String entrada = null;
        do {
            System.out.println("(e-f-d)Lado e Quanto:");
            entrada = LER.next();
            entrada = entrada.toUpperCase();
        } while (entrada.isEmpty() || entrada.length() > mC
                || entrada.charAt(0) != 'E' && entrada.charAt(0) != 'F' && entrada.charAt(0) != 'D');
        return entrada;
    }

    public static char lerChar(String pergunta) {
        char n = ' ';
        System.out.println(pergunta);
        n = LER.next().charAt(0);
        n = Character.toUpperCase(n);
        return n;
    }
}
