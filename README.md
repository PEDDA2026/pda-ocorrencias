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

Dependências:

```
android ─┐
         ├─► shared:ui ─► shared:presentation ─► shared:client ─► shared:core
desktop ─┘                                                          ▲
server ─────────────────────────────────────────────────────────────┘
```

Pacote base: `pt.ua.estga.ocorrencias`.

## Requisitos

- JDK 17+ (o Gradle descarrega sozinho o JDK 21 para o daemon).
- Android SDK com a plataforma 37; o caminho vai em `local.properties` (`sdk.dir=...`, não vai para o git).

## Correr

- Android: `./gradlew :android:assembleDebug`
- Desktop: `./gradlew :desktop:run` (com hot reload: `./gradlew :desktop:hotRun --auto`)
- Servidor: `./gradlew :server:run`

## Testes

- Partilhado (desktop/JVM): `./gradlew :shared:ui:jvmTest`
- Partilhado (Android, no host): `./gradlew :shared:ui:testAndroidHostTest`
- Servidor: `./gradlew :server:test`
- Tudo: `./gradlew build`
