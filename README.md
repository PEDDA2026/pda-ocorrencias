# Ocorrências — PDA 2026/27, Grupo 2

Plataforma de reporte de ocorrências na via pública: um servidor e dois clientes
(Android e desktop) feitos em Kotlin Multiplatform, que partilham o código em `shared/`.

## Estrutura

```
server/              Servidor Ktor; acede à BD da UA por stored procedures
android/             Cliente Android (visitante, cidadão, técnico)
desktop/             Cliente desktop (gestor de entidade, gestor da plataforma)
shared/              Código multiplataforma usado pelos dois clientes
  core/              Domínio: modelos, estados, máquina de estados (também usado pelo servidor)
  client/            Cliente da API (Ktor client): uma operação por rota, sessão e erros
  presentation/      ViewModels em Kotlin puro (estado + ações), sem Compose nem Android
  ui/                Interface em Compose Multiplatform: tema, componentes e ecrãs
```

```
android ─┐
         ├─► shared:ui ─► shared:presentation ─► shared:client ─► shared:core
desktop ─┘                                                          ▲
server ─────────────────────────────────────────────────────────────┘
```

Pacote base: `pt.ua.estga.ocorrencias`.

## Preparar o ambiente (uma vez)

### 1. Instalar

- **Android Studio** (recomendado) com o plugin **Kotlin Multiplatform**
  (*Settings → Plugins → Marketplace → "Kotlin Multiplatform"*).
  Em alternativa, IntelliJ IDEA (licença de estudante com o email da UA) com o mesmo plugin.
- **JDK 17 ou mais recente.** O Android Studio já traz um; o Gradle descarrega sozinho o JDK 21 de que precisa.
- No **SDK Manager** do Android Studio (*Settings → Languages & Frameworks → Android SDK*), instalar a
  plataforma **Android API 37**.

### 2. Clonar

```bash
git clone git@github.com:PEDDA2026/pda-ocorrencias.git
cd pda-ocorrencias
```

(Se não tiverem chave SSH no GitHub, usem `https://github.com/PEDDA2026/pda-ocorrencias.git`.)

### 3. Dizer ao Gradle onde está o Android SDK

O caminho do SDK é diferente em cada computador, por isso vai num ficheiro **local** que não entra no git.
Copiem o modelo e editem a linha `sdk.dir`:

```bash
cp local.properties.temp local.properties        # Linux / macOS
copy local.properties.temp local.properties      # Windows (cmd)
```

Caminhos habituais (o Android Studio mostra o vosso em *Settings → Android SDK → Android SDK Location*):

| Sistema | `sdk.dir=` |
|---|---|
| Windows | `C\:\\Users\\<nome>\\AppData\\Local\\Android\\Sdk` |
| macOS | `/Users/<nome>/Library/Android/sdk` |
| Linux | `/home/<nome>/Android/Sdk` |

Atenção:
- no Windows as barras têm de ir **duplicadas** (`\\`) e os dois pontos escapados (`C\:`);
- o caminho tem de ser **completo** (não funciona `~`);
- **nunca** façam commit do `local.properties` (já está no `.gitignore`). Se mudarem o modelo, alterem o `local.properties.temp`.

Se abrirem o projeto no Android Studio sem `local.properties`, ele normalmente cria-o sozinho com o SDK dele;
nesse caso este passo não é preciso.

### 4. Abrir o projeto

No Android Studio: *File → Open* → pasta do repositório → esperar pelo *Gradle Sync*.
As configurações para correr `android`, `desktop` e `server` aparecem na barra de cima.

## Correr

Pelo IDE, ou pela linha de comandos (no Windows trocar `./gradlew` por `gradlew.bat`):

| O quê | Comando |
|---|---|
| App Android (APK de debug) | `./gradlew :android:assembleDebug` |
| Instalar no emulador/telemóvel ligado | `./gradlew :android:installDebug` |
| App desktop | `./gradlew :desktop:run` |
| App desktop com hot reload | `./gradlew :desktop:hotRun --auto` |
| Servidor | `./gradlew :server:run` (fica em `http://localhost:8080`) |

## Testes

| O quê | Comando |
|---|---|
| Partilhado (JVM) | `./gradlew :shared:ui:jvmTest` |
| Partilhado (Android, no computador) | `./gradlew :shared:ui:testAndroidHostTest` |
| Servidor | `./gradlew :server:test` |
| Tudo (compila e testa) | `./gradlew build` |

## Problemas comuns

| Erro | Solução |
|---|---|
| `SDK location not found` | Falta o `local.properties` (passo 3) ou o caminho está errado. |
| `Failed to find target with hash string 'android-37'` | Instalar a plataforma Android API 37 no SDK Manager. |
| `./gradlew: Permission denied` (Linux/macOS) | `chmod +x gradlew` |
| Gradle Sync muito lento na primeira vez | Normal: está a descarregar dependências e o JDK 21. Das vezes seguintes usa a cache. |
| O PC fica sem memória | Fechar o emulador quando não é preciso; `./gradlew --stop` termina os processos do Gradle que ficam em segundo plano. |
