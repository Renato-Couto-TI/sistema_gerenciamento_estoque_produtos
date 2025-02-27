# Sistema de Gerenciamento de Estoque de Produtos

Projeto individual que desenvolvi recentemente em **Java** para **Gerenciamento de Estoque de Produtos**, com **orientação a objetos** e na arquitetura de projeto **MVC**, utilizando os padrões de projetos **DAO** e **Singleton**, conexão e operações em **banco de dados relacional/SQL**, **interface gráfica**, **backup com gravação em arquivo**, **visualização do backup** diretamente pela console.

## Conceitos de Programação Orientada a Objetos (POO)

Neste projeto, fiz uso intenso de todas as bases da **POO**:
- Encapsulamento
- Herança
- Abstração
- Polimorfismo

Além disso, trabalhei com:
- Relacionamentos entre classes
- Sobrecarga de métodos
- Definição dos construtores
- Classes abstratas
- Interfaces
- Métodos abstratos etc

## Arquitetura de Projeto

O resultado foi um **MVP** (Produto Mínimo Viável), com todos os recursos e funções fundamentais necessários para o gerenciamento eficiente de Produtos em Estoque.

O Sistema foi desenvolvido seguindo o padrão de arquitetura de projeto **MVC** (Model-View-Controller), com subdivisões (nas camadas Model e Controller) em **API**, Onde foram definidas e armazenadas as interfaces e **IMPL**, Onde estão as classes que implementam as interfaces da API.

Essa estrutura facilitou a distribuição objetiva das responsabilidades de cada camada, garantindo:
- Facilidade na manutenção do código
- Baixo acoplamento, já que as classes primárias só têm acesso às classes secundárias por intermédio das interfaces.

## Conexão e Operações no Banco de Dados

Foi criada toda a lógica para a **conexão e operações no banco de dados relacional**, assegurando a persistência dos dados.

Utilizei o padrão de projeto **DAO** (Data Access Object), com o pacote **DAO** (na camada Model) sendo o responsável pela conexão e comunicação com o banco de dados através do SGBD. O pacote DAO também foi dividido em:
- **API**: Onde foram armazenadas as interfaces.
- **IMPL**: Onde estão as implementações das interfaces.

### Padrão Singleton para Conexão

Especificamente para a **conexão com o banco de dados**, na classe de **Conexão** fiz uso do padrão de projeto **Singleton**, com a definição de um atributo estático (`conexaoSingleton`) que funciona como uma instância da própria classe **Conexão**, além de definir um método estático (`getInstance()`) que retorna uma instância de conexão. Isso garante que a classe **Conexão** tenha apenas uma instância de si e seja a responsável por gerenciar sua própria instância.

## Backup de Dados

O Sistema também permite fazer o **backup em arquivo** dos dados dos produtos em estoque e a **visualização do conteúdo do arquivo de backup** diretamente pela console.

## Interface Gráfica

A interface com o usuário é feita através de interfaces gráficas com **Java Swing**.

---

Esse projeto foi uma excelente oportunidade para eu aplicar alguns conhecimentos em desenvolvimento, especialmente **Programação Orientada a Objetos** (POO)!

