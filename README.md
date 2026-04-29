# 📌 Document Management API

API REST développée avec Spring Boot pour la gestion des demandes (Demande).

---

# 🚀 Stack technique

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 / PostgreSQL
- Mockito & MockMvc (tests)
- JaCoCo (test coverage)
- GitHub Actions (CI/CD)

---

# 🧱 Architecture

- Controller → gestion des endpoints REST
- Service → logique métier
- Repository → accès base de données
- DTO → séparation API / Entity
- Exception → gestion globale des erreurs (@ControllerAdvice)

---

# ⚙️ Fonctionnalités

- ✅ Créer une demande
- ✅ Récupérer toutes les demandes
- ✅ Récupérer une demande par ID
- ✅ Mettre à jour une demande
- ✅ Supprimer une demande

---

# 🧠 Règles métier

- Le `status` est automatiquement défini à `EN_COURS`
- La `fillingDate` est définie à la date de création
- Le `status` ne peut pas être modifié via l'API

---

# 🧪 Tests

- Tests unitaires avec Mockito (service)
- Tests d'intégration avec MockMvc (controller)
- Tests des cas positifs et négatifs (400, 404, etc.)

---

# 📊 Test Coverage

Couverture du code avec JaCoCo.

```bash
mvn clean test