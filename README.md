# TP20 - Architecture Microservices avec Spring Cloud

Ce projet est une application microservices développée avec Spring Boot et Spring Cloud, mettant en œuvre une architecture distribuée pour la gestion de clients et de voitures.

## 📋 Table des matières

- [Description](#description)
- [Architecture](#architecture)
- [Technologies utilisées](#technologies-utilisées)
- [Structure du projet](#structure-du-projet)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Démarrage](#démarrage)
- [Endpoints API](#endpoints-api)
- [Captures d'écran](#captures-décran)
- [Auteur](#auteur)

## 🎯 Description

TP20 est une application microservices qui démontre l'utilisation de Spring Cloud pour créer une architecture distribuée. Le système comprend :

- **Service Discovery** : Utilisation d'Eureka pour la découverte automatique des services
- **API Gateway** : Point d'entrée unique pour toutes les requêtes
- **Services métier** : Services dédiés pour la gestion des clients et des voitures
- **Base de données** : MySQL pour la persistance des données

## 🏗️ Architecture

Le projet suit une architecture microservices avec les composants suivants :

```
┌─────────────┐
│   Gateway   │ (Port 8080)
└──────┬──────┘
       │
       ├──────────────┬──────────────┐
       │              │              │
┌──────▼──────┐ ┌─────▼─────┐ ┌─────▼─────┐
│ Eureka      │ │ Car       │ │ Client    │
│ Server      │ │ Service   │ │ Service   │
│ (8761)      │ │           │ │           │
└─────────────┘ └───────────┘ └───────────┘
       ▲              │              │
       └──────────────┴──────────────┘
```

### Composants

1. **Eureka Server** (Port 8761)
   - Service de découverte et d'enregistrement des microservices
   - Dashboard pour visualiser les services enregistrés

2. **API Gateway** (Port 8080)
   - Point d'entrée unique pour toutes les requêtes
   - Routage vers les services appropriés
   - Filtrage et logging des requêtes

3. **Car Service** 
   - Gestion des voitures
   - Endpoints REST pour CRUD des voitures
   - Communication avec le service Client

4. **Client Service**
   - Gestion des clients
   - Endpoints REST pour CRUD des clients
   - Base de données MySQL

## 🛠️ Technologies utilisées

- **Java 17**
- **Spring Boot 4.0.0 / 3.5.9-SNAPSHOT**
- **Spring Cloud 2025.0.0 / 2025.1.0**
- **Spring Cloud Netflix Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **Spring Data JPA** - Accès aux données
- **MySQL** - Base de données relationnelle
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestion des dépendances

## 📁 Structure du projet

```
TP20/
├── eureka-server/          # Service de découverte Eureka
│   ├── src/
│   └── pom.xml
├── gateway/                # API Gateway
│   ├── src/
│   └── pom.xml
├── car-service/            # Service de gestion des voitures
│   ├── src/
│   └── pom.xml
├── Client/                 # Service de gestion des clients
│   ├── src/
│   └── pom.xml
└── README.md
```

## 📋 Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- **JDK 17** ou supérieur
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

## 🚀 Installation

1. **Cloner le repository**
   ```bash
   git clone https://github.com/HaytamNajam26/TP20.git
   cd TP20
   ```

2. **Créer la base de données MySQL**
   ```sql
   CREATE DATABASE client_db;
   CREATE DATABASE car_db;
   ```

3. **Configurer les fichiers `application.yml`**
   - Modifier les paramètres de connexion à la base de données dans chaque service
   - Vérifier les ports et les URLs Eureka

## ⚙️ Configuration

### Eureka Server
- Port : `8761`
- URL : `http://localhost:8761`

### Gateway
- Port : `8080`
- URL Eureka : `http://localhost:8761/eureka`

### Car Service
- Port : (configuré dans `application.yml`)
- URL Eureka : `http://localhost:8761/eureka`

### Client Service
- Port : (configuré dans `application.yml`)
- URL Eureka : `http://localhost:8761/eureka`

## ▶️ Démarrage

**Ordre de démarrage recommandé :**

1. **Démarrer Eureka Server**
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. **Démarrer les services métier** (dans des terminaux séparés)
   ```bash
   # Terminal 2 - Client Service
   cd Client
   mvn spring-boot:run
   
   # Terminal 3 - Car Service
   cd car-service
   mvn spring-boot:run
   ```

3. **Démarrer le Gateway**
   ```bash
   # Terminal 4 - Gateway
   cd gateway
   mvn spring-boot:run
   ```

4. **Vérifier l'enregistrement des services**
   - Accéder à : `http://localhost:8761` pour voir le dashboard Eureka

## 🔌 Endpoints API

### Via Gateway (Port 8080)

#### Client Service
- `GET /api/clients` - Récupérer tous les clients
- `GET /api/clients/{id}` - Récupérer un client par ID
- `POST /api/clients` - Créer un nouveau client

#### Car Service
- `GET /api/car` - Récupérer toutes les voitures
- `GET /api/car/{id}` - Récupérer une voiture par ID
- `POST /api/car` - Créer une nouvelle voiture

### Exemples de requêtes

**Créer un client :**
```bash
curl -X POST http://localhost:8080/api/clients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Dupont",
    "prenom": "Jean",
    "email": "jean.dupont@example.com"
  }'
```

**Récupérer tous les clients :**
```bash
curl http://localhost:8080/api/clients
```

**Créer une voiture :**
```bash
curl -X POST http://localhost:8080/api/car \
  -H "Content-Type: application/json" \
  -d '{
    "marque": "Toyota",
    "modele": "Corolla",
    "annee": 2023
  }'
```

## 📸 Captures d'écran

### Dashboard Eureka
<!-- Ajoutez ici vos captures d'écran du dashboard Eureka -->
![Dashboard Eureka](screenshots/eureka-dashboard.png)

### Test des endpoints
<!-- Ajoutez ici vos captures d'écran des tests d'API -->
![Test API](screenshots/api-test.png)

### Architecture des services
<!-- Ajoutez ici vos captures d'écran de l'architecture -->
![Architecture](screenshots/architecture.png)

> **Note :** Créez un dossier `screenshots/` à la racine du projet et ajoutez-y vos captures d'écran.

## 👤 Auteur

**Haytam Najam**

- GitHub: [@HaytamNajam26](https://github.com/HaytamNajam26)
- Repository: [TP20](https://github.com/HaytamNajam26/TP20)

## 📝 Licence

Ce projet est un projet éducatif.

---

**Note :** Assurez-vous que tous les services sont démarrés et enregistrés dans Eureka avant de tester les endpoints via le Gateway.

