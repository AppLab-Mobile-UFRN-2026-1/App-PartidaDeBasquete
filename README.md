# 🏀 Placar de Basquete 

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)

Um aplicativo Android nativo para gerenciar partidas de basquete. Projetado com uma interface imersiva que simula um painel eletrônico de ginásio real, o app oferece controle completo de pontuação, cronômetro interativo e regras de fim de jogo automatizadas.

## 📸 Demonstração


https://github.com/user-attachments/assets/e2c13a1b-b3cf-4023-a71e-56e9ef1f89f0


---

## ✨ Funcionalidades

### 🎨 Design e Interface (UI/UX)
* **Painel Eletrônico Realista:** Placar encapsulado em um *drawable* customizado com cantos arredondados, fundo escuro e tipografia imitando displays de LED.
* **Prevenção de Erros Clínicos:** Botões de controle coloridos (Azul para o Time A, Vermelho para o Time B) para evitar marcações acidentais pelo mesário.
* **Componentes Customizados:** Utilização de `Shape Drawables` para estilizar botões e o fundo do placar, abandonando o design padrão do sistema.

### ⚙️ Lógica e Regras de Negócio
* **Sistema de Pontuação:** Botões individuais para +3 Pontos, +2 Pontos e Tiro Livre (+1) para cada equipe.
* **Cronômetro Interativo (CountDownTimer):** Relógio regressivo de 10 minutos (padrão FIBA). O timer é acionado e pausado ao **tocar diretamente no display do tempo**, simulando a ação rápida de um mesário.
* **Condição de Vitória Automática:** O app monitora a pontuação em tempo real. Se uma equipe atingir **100 pontos**, o cronômetro é pausado imediatamente e um `AlertDialog` bloqueia a tela anunciando o campeão.
* **Reinício Rápido:** Botão de *reset* que zera os pontos e restaura o cronômetro para os 10 minutos iniciais.

---

## 📂 Estrutura do Projeto

```text
PartidaBasquete/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/leonardonadson/partidabasquete/
│   │   │   │   └── MainActivity.kt       # Lógica principal, cronômetro e regras de negócio
│   │   │   ├── res/
│   │   │   │   ├── drawable/             # Estilos visuais e formas
│   │   │   │   │   ├── custom_button.xml # Design arredondado dos botões
│   │   │   │   │   └── fundo_placar.xml  # Design do painel de LED do placar
│   │   │   │   └── layout/               
│   │   │   │       └── activity_main.xml # Interface visual da tela (UI)
│   │   │   └── AndroidManifest.xml       # Configurações globais e permissões do Android
│   └── build.gradle.kts                  # Dependências e configurações de build do módulo
└── build.gradle.kts                      # Configurações de build globais do projeto
```

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Kotlin
* **SDK:** API 34 (Min: 24)
* **Gradle:** Versão 9.3.1
* **Views e Layouts:** XML (LinearLayout, RelativeLayout)
* **Componentes Nativos:** `CountDownTimer`, `AlertDialog`, `Toast`
* **Estilização:** Arquivos de `Drawable` customizados (shapes, strokes, solid colors).
* **IDE:** Android Studio
---


## 💻 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [Git](https://git-scm.com) para clonar o repositório.
* [Android Studio](https://developer.android.com/studio) para rodar e editar o código.

## 🚀 Como executar o projeto

1. Abra o seu terminal e faça o clone deste repositório:
   ```bash
   git clone https://github.com/AppLab-Mobile-UFRN-2026-1/App-PartidaDeBasquete.git
2. Abra o Android Studio.

3. Na tela inicial, clique em Open e selecione a pasta do projeto que você acabou de clonar.

4. Aguarde o Gradle sincronizar todas as dependências. 
5. Conecte o seu celular Android via cabo USB  ou inicie um Emulador pelo Device Manager.

6. Clique no botão verde de Run (▶️) na barra superior ou pressione Shift + F10 para rodar o aplicativo!

---

## 👥 Equipe de Desenvolvimento

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/leonardonadson">
        <img src="https://avatars.githubusercontent.com/leonardonadson" width="100px;" alt="Foto de Leonardo Nadson no GitHub"/>
        <br>
        <sub>
          <b>Leonardo Nadson</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/luan-sampaio">
        <img src="https://avatars.githubusercontent.com/luan-sampaio" width="100px;" alt="Foto de Luan Sampaio no GitHub"/>
        <br>
        <sub>
          <b>Luan Sampaio</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/MarcusAurelius33">
        <img src="https://avatars.githubusercontent.com/MarcusAurelius33" width="100px;" alt="Foto de Marcus Aurelius no GitHub"/>
        <br>
        <sub>
          <b>Marcus Aurelius</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

