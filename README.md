# Nestory

A personal home inventory Android application built with Jetpack Compose.

## The Problem

Ever bought something at the store, only to get home and realize you already had it? Or found an expired product buried in the back of the cabinet? Nestory was built to solve exactly that — helping you keep track of what you have at home so you never forget, overbuy, or let things expire.

## Features

- **Add, edit, and delete items** — track each item with name, quantity, and expiry date
- **Box grouping** — organize items into boxes or groups (e.g. kitchen cabinet, bathroom shelf)
- **Category tagging** — classify items by type such as food, medicine, or household supplies
- **Expiry notifications** — get notified before items are about to expire

## Tech Stack

- **UI** — Jetpack Compose
- **Architecture** — MVVM + Repository Pattern
- **Local Database** — Room
- **Dependency Injection** — Koin
- **Background Tasks** — WorkManager
- **Language** — Kotlin

## Architecture

The project follows Clean Architecture principles, separated into three layers:

```
data/        → Room entities, DAOs, repository implementations
domain/      → Models, repository interfaces
presentation/ → ViewModels, Compose screens
```

## Status

🚧 In development
