
# Système de Prise de Rendez-Vous

## Description

Le **Système de Prise de Rendez-Vous** est une application web permettant aux utilisateurs de réserver des créneaux horaires pour des consultations. L'administrateur du système peut gérer les créneaux disponibles, consulter la liste des rendez-vous pris et effectuer des actions telles que l'annulation d'un rendez-vous ou le marquage de l'absence d'un patient. 

Ce projet utilise **Spring Boot** pour la gestion des services backend, avec une interface frontale basée sur **Thymeleaf**, **HTML**, **CSS**, et **JavaScript** (via jQuery). Le système comprend des fonctionnalités d'authentification pour l'administrateur et une interface utilisateur intuitive pour la réservation et la gestion des créneaux horaires.

## Fonctionnalités

- **Réservation de créneaux horaires** : Les utilisateurs peuvent visualiser les créneaux horaires disponibles et réserver un rendez-vous.
- **Annulation de rendez-vous** : Les administrateurs peuvent annuler un rendez-vous ou marquer un patient comme absent.
- **Affichage des consultations** : Une vue dédiée permet de consulter la liste des rendez-vous pris et de gérer les actions associées.
- **Gestion des statuts** : Les consultations peuvent être marquées comme "Confirmées", "Annulées" ou "Patient Absent".
- **Confirmation des rendez-vous** : Une page de confirmation s'affiche après la soumission d'un formulaire de prise de rendez-vous.
- **Interactivité sans rechargement** : Le système utilise AJAX pour soumettre des formulaires sans rechargement de page.

## Technologies utilisées

- **Backend** : Spring Boot (Java)
- **Frontend** : Thymeleaf, HTML, CSS, JavaScript (jQuery)
- **Base de données** : H2 (ou toute autre base de données relationnelle compatible avec Spring JPA)
- **Styles CSS** : Bootstrap 4 pour le responsive design et la gestion de la mise en page.
- **AJAX** : Gestion asynchrone des formulaires et des actions de gestion des consultations.

## Installation

### Prérequis

- **Java JDK 11+**
- **Maven** (pour la gestion des dépendances)
- **IDE** compatible avec Java (ex : IntelliJ IDEA, Eclipse)
- **Base de données** (H2 par défaut, mais configurable avec MySQL, PostgreSQL, etc.)

### Étapes d'installation

1. **Cloner le dépôt** :

   ```bash
   git clone https://github.com/votre-utilisateur/systeme-rendez-vous.git
   ```

2. **Naviguer dans le répertoire du projet** :

   ```bash
   cd systeme-rendez-vous
   ```

3. **Configurer la base de données** :

   - Le projet utilise par défaut une base de données H2 en mémoire. Pour configurer une autre base de données (par exemple MySQL ou PostgreSQL), modifiez le fichier `application.properties` dans le répertoire `src/main/resources` :

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/votre_db
     spring.datasource.username=votre_utilisateur
     spring.datasource.password=votre_mot_de_passe
     spring.jpa.hibernate.ddl-auto=update
     ```

4. **Construire le projet avec Maven** :

   ```bash
   mvn clean install
   ```

5. **Exécuter l'application** :

   ```bash
   mvn spring-boot:run
   ```

6. **Accéder à l'application** :

   Ouvrez votre navigateur et rendez-vous à l'adresse suivante :

   ```
   http://localhost:8080
   ```

## Utilisation

### Page d'accueil ("/")

La page d'accueil affiche une vue générale du système avec des informations sur l'application. Les utilisateurs peuvent naviguer vers la page de réservation pour prendre un rendez-vous.

### Réservation d'un rendez-vous ("/rendezvous")

Les utilisateurs peuvent sélectionner un créneau horaire disponible pour un rendez-vous, remplir un formulaire avec leurs informations personnelles (nom, prénom, email, etc.) et soumettre le formulaire.

### Confirmation du rendez-vous

Après la soumission d'un rendez-vous, une page de confirmation est affichée à l'utilisateur avec les détails du rendez-vous sélectionné.

### Gestion des rendez-vous ("/consultations")

Les administrateurs peuvent accéder à une liste de toutes les consultations via la route `/consultations`. Ils peuvent effectuer des actions comme :
- Annuler un rendez-vous
- Marquer un patient comme absent

Les actions sont exécutées en utilisant des appels AJAX, permettant à l'interface de rester réactive sans rechargement de la page.

## API Backend

### Récupérer les créneaux disponibles

- **GET** `/getAllCreneauxParDate?date=YYYY-MM-DD`
- **Description** : Récupère tous les créneaux horaires disponibles pour une date donnée.
- **Exemple de réponse** :
  ```json
  [
      {
          "id": 1,
          "date": "2023-10-12",
          "heureDebut": "09:00",
          "heureFin": "09:30",
          "estPris": false
      }
  ]
  ```

### Annuler un rendez-vous

- **POST** `/Annulation`
- **Description** : Annule le rendez-vous correspondant à l'ID du créneau.
- **Paramètres** : 
  - `id` : ID du créneau horaire

### Marquer un patient absent

- **POST** `/MarquerAbsent`
- **Description** : Marque un patient comme absent pour un créneau donné.
- **Paramètres** : 
  - `id` : ID du créneau horaire

## Améliorations futures

Quelques améliorations possibles pour l'application incluent :
- Ajout d'un système de notifications par email pour les confirmations et les rappels de rendez-vous.
- Gestion plus avancée des utilisateurs avec rôles (administrateurs vs utilisateurs normaux).
- Amélioration du tableau de bord pour les administrateurs avec des graphiques statistiques sur les consultations.

## Contribuer

Si vous souhaitez contribuer à ce projet, veuillez soumettre une pull request avec une description claire des modifications. Assurez-vous que le code est testé avant de proposer des changements.

## License

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.
