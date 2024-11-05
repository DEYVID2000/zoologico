enum class Reino {
    ANIMALIA, PLANTAE, FUNGI, PROTISTA
}

enum class Dieta {
    HERBIVORO, CARNIVORO, ONIVORO
}

enum class TipoGaiola {
    SAVANA, AQUARIO, DESERTO, FLORESTA, POLO
}

data class Localizacao(val setor: String, val gaiolaId: String)

data class Animal(
    val apelido: String,
    val reino: Reino,
    val filo: String,
    val classe: String,
    val ordem: String,
    val familia: String,
    val genero: String,
    val especie: String,
    val origem: String,
    val dieta: Dieta,
    val tipoGaiola: TipoGaiola,
    val localizacao: Localizacao
) {
    override fun toString(): String {
        return """
            Apelido: $apelido
            Taxonomia: $reino > $filo > $classe > $ordem > $familia > $genero > $especie
            Origem: $origem
            Dieta: $dieta
            Tipo da Gaiola: $tipoGaiola
            Localização no Zoo: Setor ${localizacao.setor}, Gaiola ${localizacao.gaiolaId}
        """.trimIndent()
    }
}

class Zoo {
    private val animais = mutableListOf<Animal>()

    fun cadastrarAnimal(animal: Animal) {
        animais.add(animal)
        println("Animal '${animal.apelido}' cadastrado com sucesso!")
    }

    fun listarAnimais() {
        if (animais.isEmpty()) {
            println("Nenhum animal cadastrado no zoológico.")
        } else {
            println("Animais no Zoológico:")
            animais.forEach { println(it) }
        }
    }
}

fun mostrarMenu(): Int {
    println("\n=== Menu do Zoológico ===")
    println("1. Cadastrar Animal")
    println("2. Listar Animais")
    println("3. Sair")
    print("Escolha uma opção: ")
    return readLine()?.toIntOrNull() ?: 0
}

fun cadastrarAnimal(zoo: Zoo) {
    print("Apelido do Animal: ")
    val apelido = readLine() ?: ""

    print("Reino (Animalia, Plantae, Fungi, Protista): ")
    val reino = try {
        Reino.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Reino inválida! Definindo como ANIMALIA.")
        Reino.ANIMALIA
    }
    print("Filo (Chordata, Arthropoda, Mollusca, Nematoda): ")
    val filo = readLine() ?: ""

    print("Classe (Mammalia, Aves, Reptilia, Amphibia, Actinopterygii): ")
    val classe = readLine() ?: ""

    print("Ordem (Carnivora, Primates, Passeriformes, Squamata, Anura): ")
    val ordem = readLine() ?: ""

    print("Família (Felidae, Canidae, Hominidae, Psittacidae, Ranidae): ")
    val familia = readLine() ?: ""

    print("Gênero: ")
    val genero = readLine() ?: ""

    print("Espécie: ")
    val especie = readLine() ?: ""

    print("Origem: ")
    val origem = readLine() ?: ""

    print("Dieta (HERBIVORO, CARNIVORO, ONIVORO): ")
    val dieta = try {
        Dieta.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Dieta inválida! Definindo como HERBIVORO.")
        Dieta.HERBIVORO
    }

    print("Tipo de Gaiola (SAVANA, AQUARIO, DESERTO, FLORESTA, POLO): ")
    val tipoGaiola = try {
        TipoGaiola.valueOf(readLine()?.uppercase() ?: "")
    } catch (e: IllegalArgumentException) {
        println("Tipo de gaiola inválido! Definindo como SAVANA.")
        TipoGaiola.SAVANA
    }

    print("Setor da Sela: ")
    val setor = readLine() ?: ""

    print("ID da Gaiola: ")
    val gaiolaId = readLine() ?: ""

    val localizacao = Localizacao(setor, gaiolaId)
    val animal = Animal(apelido, reino, filo, classe, ordem, familia, genero, especie, origem, dieta, tipoGaiola, localizacao)

    zoo.cadastrarAnimal(animal)
}

fun main() {
    val zoo = Zoo()
    while (true) {
        when (mostrarMenu()) {
            1 -> cadastrarAnimal(zoo)
            2 -> zoo.listarAnimais()
            3 -> {
                println("Encerrando o programa...")
                return
            }
            else -> println("Opção inválida! Tente novamente.")
        }
    }
}